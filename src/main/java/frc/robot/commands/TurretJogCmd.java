package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;
import frc.robot.Constants;
import java.util.function.DoubleSupplier;

public class TurretJogCmd extends Command {
    private final Turret turret;
    private final DoubleSupplier speed; 

    public TurretJogCmd(Turret turret, DoubleSupplier speed) {
        this.turret = Turret.getInstance();
        this.speed = speed;

        addRequirements(turret);
    }

    @Override
    public void initialize() {
        System.out.println("TurretJogCmd Online");
    }

    @Override
    public void execute() {
        double motorSpeed = speed.getAsDouble();
        turret.setTurretMotorSpeed(motorSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        turret.setTurretMotorSpeed(Constants.ABSOLUTE_ZERO);
        System.out.println("TurretJogCmd Offline");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}