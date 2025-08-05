package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;

public class TurretTrajectoryCmd extends Command {
    private final Turret turret;
    private final double turretStageSetpoint;
    private final double turretIntermediate1Setpoint;
    private final double turretIntermediate2Setpoint;
    private final double turretIntermediate3Setpoint;
    private final double turretIntermediate4Setpoint;
    private final double turretExitSetpoint;

    private enum CommandState {
        STAGE_TRAJ,
        IMP_1,
        IMP_2,
        IMP_3,
        IMP_4,
        EXIT
    }

    private CommandState commandState;

    public TurretTrajectoryCmd(Turret turret, double turretStageSetpoint, double turretIntermediate1Setpoint, 
            double turretIntermediate2Setpoint, double turretIntermediate3Setpoint, double turretIntermediate4Setpoint, double turretExitSetpoint) {
        commandState = CommandState.STAGE_TRAJ;
        this.turret = Turret.getInstance();

        this.turretStageSetpoint = turretStageSetpoint;
        this.turretIntermediate1Setpoint = turretIntermediate1Setpoint;
        this.turretIntermediate2Setpoint = turretIntermediate2Setpoint;
        this.turretIntermediate3Setpoint = turretIntermediate3Setpoint;
        this.turretIntermediate4Setpoint = turretIntermediate4Setpoint;
        this.turretExitSetpoint = turretExitSetpoint;

        addRequirements(turret);
    }

    @Override
    public void initialize() {
        System.out.println("TurretTrajectoryCmd Online");
    }

    @Override
    public void execute() {
        SmartDashboard.putString("TurretTrajectoryCmdState", commandState.toString());

        switch (commandState) {
            case STAGE_TRAJ:
                turret.sendTurretSetpoint(turretStageSetpoint);
                commandState = CommandState.IMP_1;
                break;

            case IMP_1:
                if (turret.isTurretInTolerance()) {
                    turret.sendTurretSetpoint(turretIntermediate1Setpoint);
                    commandState = CommandState.IMP_2;
                }
                break;

            case IMP_2:
                if (turret.isTurretInTolerance()) {
                    turret.sendTurretSetpoint(turretIntermediate2Setpoint);
                    commandState = CommandState.IMP_3;
                }
                break;

            case IMP_3:
                if (turret.isTurretInTolerance()) {
                    turret.sendTurretSetpoint(turretIntermediate3Setpoint);
                    commandState = CommandState.IMP_4;
                }
                break;

            case IMP_4:
                if (turret.isTurretInTolerance()) {
                    turret.sendTurretSetpoint(turretIntermediate4Setpoint);
                    commandState = CommandState.EXIT;
                }
                break;

            case EXIT:
                turret.sendTurretSetpoint(turretExitSetpoint);
                break;
        } 
    }

    @Override
    public void end(boolean interrupted) {
        commandState = CommandState.STAGE_TRAJ;
        System.out.println("TurretTrajectoryCmd Offline");
    }

    @Override
    public boolean isFinished() {
        if (commandState == CommandState.EXIT) {
            commandState = CommandState.STAGE_TRAJ;
            return true;
        } else {
            return false;
        }
    }
}