package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;

public class TurretSetpointCmd extends Command {
    private final Turret turret;
    private final double turretSetoint;

    public TurretSetpointCmd(Turret turret, double turretSetoint) {
        this.turret = Turret.getInstance();
        this.turretSetoint = turretSetoint;

        addRequirements(turret);
    }

    @Override
    public void initialize() {
        turret.setTurretSetpoint(turretSetoint);
        System.out.println("TurretSetpointCmd Online");
    }

    @Override
    public void execute() {
        turret.goToTurretSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("TurretSetpointCmd Offline");
    }

    @Override
    public boolean isFinished() {
        if (turret.isTurretInTolerance() == true) {
            return true;
        } else {
            return false;
        }
    }
}