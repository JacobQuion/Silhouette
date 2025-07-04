package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class ArmZeroOverrideCmd extends Command {
    private final Arm arm;

    public ArmZeroOverrideCmd(Arm arm) {
        this.arm = Arm.getInstance(); 
        addRequirements(arm); 
    }

    @Override
    public void initialize() {
        System.out.println("ArmZeroOverrideCmd Online");
    }

    @Override
    public void execute() {
        arm.zeroArm();
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("ArmZeroOverrideCmd Offline");
    }

    @Override
    public boolean isFinished() {
        if (arm.getArmMotorEncoder() == Constants.ABSOLUTE_ZERO) {
            return true;
        } else {
            return false;
        }
    }
}