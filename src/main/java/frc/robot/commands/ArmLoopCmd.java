package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Turret;

public class ArmLoopCmd extends Command {
    private final Arm arm;
    private final Turret turret;   
    private CommandState commandState;
    private double stepStartTime;

    private enum CommandState {
        ZERO_ROUTINE,
        LEVEL_1,
        LEVEL_2,
    }

    public ArmLoopCmd(Arm arm, Turret turret) {
        this.arm = arm;
        this.turret = turret;
        this.commandState = CommandState.ZERO_ROUTINE;
        this.stepStartTime = 0.0;

        addRequirements(arm);
        addRequirements(turret);
    }

    @Override
    public void initialize() {
        System.out.println("ArmLoopCmd Online");
        stepStartTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        SmartDashboard.putString("ArmLoopCmd State", commandState.toString());

        switch (commandState) {
            case ZERO_ROUTINE:
                arm.sendArmSetpoint(Constants.ARM_ZERO_SETPOINT);
                turret.sendTurretSetpoint(Constants.TURRET_ZERO_SETPOINT);
                stepStartTime = Timer.getFPGATimestamp();
                commandState = CommandState.LEVEL_1;
                break;

            case LEVEL_1:
                if (Timer.getFPGATimestamp() - stepStartTime >= 1.0) {
                    arm.sendArmSetpoint(Constants.ARM_SETPOINT_C);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_2;
                }
                break;          

            case LEVEL_2:
                if (Timer.getFPGATimestamp() - stepStartTime >= 1.0) {
                    arm.sendArmSetpoint(Constants.ARM_ZERO_SETPOINT);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_1;
                }
                break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        commandState = CommandState.ZERO_ROUTINE;
        System.out.println("ArmLoopCmd Offline");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}