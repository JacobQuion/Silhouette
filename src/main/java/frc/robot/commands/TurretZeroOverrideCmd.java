package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;

public class TurretZeroOverrideCmd extends Command {
    private final Turret turret;

    public TurretZeroOverrideCmd(Turret turret) {
        this.turret = Turret.getInstance(); 
        addRequirements(turret); 
    }

    @Override
    public void initialize() {
        System.out.println("TurretZeroOverrideCmd Online");
    }

    @Override
    public void execute() {
        turret.zeroTurret();
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("TurretZeroOverrideCmd Offline");
    }

    @Override
    public boolean isFinished() {
        if (turret.getTurretMotorEncoder() == Constants.ABSOLUTE_ZERO) {
            return true;
        } else {
            return false;
        }
    }
}