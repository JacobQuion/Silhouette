package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;

public class TurretPendulumCmd extends Command {
    private final Turret turret;

    private enum CommandState {
        STEP_1,
        STEP_2,
        STEP_3
    }

    private CommandState commandState;
    private double stepStartTime;

    public TurretPendulumCmd(Turret turret) {
        this.turret = turret;
        this.commandState = CommandState.STEP_1;
        this.stepStartTime = 0.0;

        addRequirements(turret);
    }

    @Override
    public void initialize() {
        System.out.println("TurretPendulumCmd Online");
        stepStartTime = Timer.getFPGATimestamp(); // start timing immediately
    }

    @Override
    public void execute() {
        SmartDashboard.putString("TurretPendulumCmd State", commandState.toString());

        switch (commandState) {
            case STEP_1:
                // Move turret immediately, then start timer
                turret.sendTurretSetpoint(Constants.TURRET_PENDULUM_SETPOINT_1);
                stepStartTime = Timer.getFPGATimestamp();
                commandState = CommandState.STEP_2;
                break;

            case STEP_2:
                // Wait 1 second, then move arm
                if (Timer.getFPGATimestamp() - stepStartTime >= 1.0) {
                    turret.sendTurretSetpoint(Constants.TURRET_PENDULUM_SETPOINT_2);
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
        System.out.println("TurretPendulumCmd Offline");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
