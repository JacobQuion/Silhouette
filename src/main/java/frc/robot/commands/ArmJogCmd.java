package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;
import frc.robot.Constants;
import java.util.function.DoubleSupplier;

public class ArmJogCmd extends Command {
    private final Arm arm;
    private final DoubleSupplier speed; 

    public ArmJogCmd(Arm arm, DoubleSupplier speed) {
        this.arm = Arm.getInstance();
        this.speed = speed;

        addRequirements(arm);
    }

    @Override
    public void initialize() {
        System.out.println("ArmJogCmd Online");
    }

    @Override
    public void execute() {
        double motorSpeed = speed.getAsDouble();
        arm.setArmMotorSpeed(motorSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        arm.setArmMotorSpeed(Constants.ABSOLUTE_ZERO);
        System.out.println("ArmJogCmd Offline");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}