package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class ArmSetpointCmd extends Command {
    private final Arm arm;
    private final double armSetpoint;

    public ArmSetpointCmd(Arm arm, double armSetpoint) {
        this.arm = Arm.getInstance();
        this.armSetpoint = armSetpoint;

        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setArmSetpoint(armSetpoint);
        System.out.println("ArmSetpointCmd Online");
    }

    @Override
    public void execute() {
        arm.goToArmSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("ArmSetpointCmd Offline");
    }

    @Override
    public boolean isFinished() {
        if (arm.isArmInTolerance() == true) {
            return true;
        } else {
            return false;
        }
    }
}