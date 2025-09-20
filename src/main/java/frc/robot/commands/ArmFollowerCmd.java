package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Turret;

public class ArmFollowerCmd extends Command {
    private final Arm arm;
    private final Turret turret;   
    private CommandState commandState;
    private double stepStartTime;

    private enum CommandState {
        ZERO_ROUTINE,
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        LEVEL_5,
        LEVEL_6,
        LEVEL_7,
        LEVEL_8,
        LEVEL_9,
        RESET_LEVEL
    }

    public ArmFollowerCmd(Arm arm, Turret turret) {
        this.arm = arm;
        this.turret = turret;
        this.commandState = CommandState.ZERO_ROUTINE;
        this.stepStartTime = 0.0;

        addRequirements(arm);
        addRequirements(turret);
    }

    @Override
    public void initialize() {
        System.out.println("ArmFollowerCmd Online");
        stepStartTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        SmartDashboard.putString("ArmFollowerCmd State", commandState.toString());

        switch (commandState) {
            case ZERO_ROUTINE:
                arm.sendArmSetpoint(Constants.ARM_ZERO_SETPOINT);
                turret.sendTurretSetpoint(Constants.TURRET_ZERO_SETPOINT);
                stepStartTime = Timer.getFPGATimestamp();
                commandState = CommandState.LEVEL_1;
                break;

            case LEVEL_1:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    turret.sendTurretSetpoint(Constants.TURRET_SETPOINT_A);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_2;
                }
                break;          

            case LEVEL_2:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    arm.sendArmSetpoint(Constants.ARM_SETPOINT_D);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_3;
                }
                break;

            case LEVEL_3:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    arm.sendArmSetpoint(Constants.ARM_SETPOINT_LOW);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_4;
                }
                break;

            case LEVEL_4:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    turret.sendTurretSetpoint(Constants.TURRET_ZERO_SETPOINT);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_5;
                }
                break;

            case LEVEL_5:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    arm.sendArmSetpoint(Constants.ARM_SETPOINT_D);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_6;
                }
                break;

            case LEVEL_6:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    arm.sendArmSetpoint(Constants.ARM_SETPOINT_LOW);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_7;
                }
                break;

            case LEVEL_7:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    turret.sendTurretSetpoint(Constants.TURRET_SETPOINT_E);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_8;
                }
                break;

            case LEVEL_8:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    arm.sendArmSetpoint(Constants.ARM_SETPOINT_D);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.LEVEL_9;
                }
                break;

            case LEVEL_9:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    arm.sendArmSetpoint(Constants.ARM_SETPOINT_LOW);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.RESET_LEVEL;
                }
                break;

            case RESET_LEVEL:
                if (Timer.getFPGATimestamp() - stepStartTime >= 2.0) {
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.ZERO_ROUTINE;
                }
                break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        commandState = CommandState.ZERO_ROUTINE;
        System.out.println("ArmFollowerCmd Offline");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}