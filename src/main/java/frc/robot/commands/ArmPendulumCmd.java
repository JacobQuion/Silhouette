package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class ArmPendulumCmd extends Command {
    private final Arm arm;

    private enum CommandState {
        STEP_1,
        STEP_2,
        STEP_3
    }

    private CommandState commandState;
    private double stepStartTime;

    public ArmPendulumCmd(Arm arm) {
        this.arm = arm;
        this.commandState = CommandState.STEP_1;
        this.stepStartTime = 0.0;

        addRequirements(arm);
    }

    @Override
    public void initialize() {
        System.out.println("ArmPendulumCmd Online");
        stepStartTime = Timer.getFPGATimestamp(); // start timing immediately
    }

    @Override
    public void execute() {
        SmartDashboard.putString("ArmPendulumCmd State", commandState.toString());

        switch (commandState) {
            case STEP_1:
                // Move turret immediately, then start timer
                arm.sendArmSetpoint(Constants.ARM_PENDULUM_SETPOINT_1);
                stepStartTime = Timer.getFPGATimestamp();
                commandState = CommandState.STEP_2;
                break;

            case STEP_2:
                // Wait 1 second, then move arm
                if (Timer.getFPGATimestamp() - stepStartTime >= 1.0) {
                    arm.sendArmSetpoint(Constants.ARM_PENDULUM_SETPOINT_2);
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.STEP_3;
                }
                break;

            case STEP_3:
                // Wait 1 second, then loop back
                if (Timer.getFPGATimestamp() - stepStartTime >= 1.0) {
                    stepStartTime = Timer.getFPGATimestamp();
                    commandState = CommandState.STEP_1;
                }
                break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        commandState = CommandState.STEP_1;
        System.out.println("ArmPendulumCmd Offline");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
