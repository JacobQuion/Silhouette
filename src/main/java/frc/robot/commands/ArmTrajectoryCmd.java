package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class ArmTrajectoryCmd extends Command {
    private final Arm arm;
    private final double armStageSetpoint;
    private final double armIntermediate1Setpoint;
    private final double armIntermediate2Setpoint;
    private final double armIntermediate3Setpoint;
    private final double armIntermediate4Setpoint;
    private final double armExitSetpoint;

    private enum CommandState {
        STAGE_TRAJECTORY,
        INTERMEDIATE_1,
        INTERMEDIATE_2,
        INTERMEDIATE_3,
        INTERMEDIATE_4,
        EXIT
    }

    private CommandState commandState;

    public ArmTrajectoryCmd(Arm arm, double armStageSetpoint, double armIntermediate1Setpoint, 
            double armIntermediate2Setpoint, double armIntermediate3Setpoint, double armIntermediate4Setpoint, double armExitSetpoint) {
        commandState = CommandState.STAGE_TRAJECTORY;
        this.arm = Arm.getInstance();

        this.armStageSetpoint = armStageSetpoint;
        this.armIntermediate1Setpoint = armIntermediate1Setpoint;
        this.armIntermediate2Setpoint = armIntermediate2Setpoint;
        this.armIntermediate3Setpoint = armIntermediate3Setpoint;
        this.armIntermediate4Setpoint = armIntermediate4Setpoint;
        this.armExitSetpoint = armExitSetpoint;

        addRequirements(arm);
    }

    @Override
    public void initialize() {
        System.out.println("ArmTrajectoryCmd Online");
    }

    @Override
    public void execute() {
        SmartDashboard.putString("ArmTrajectoryCmdState", commandState.toString());

        switch (commandState) {
            case STAGE_TRAJECTORY:
                arm.sendArmSetpoint(armStageSetpoint);
                commandState = CommandState.INTERMEDIATE_1;
                break;

            case INTERMEDIATE_1:
                if (arm.isArmInTolerance()) {
                    arm.sendArmSetpoint(armIntermediate1Setpoint);
                    commandState = CommandState.INTERMEDIATE_2;
                }
                break;

            case INTERMEDIATE_2:
                if (arm.isArmInTolerance()) {
                    arm.sendArmSetpoint(armIntermediate2Setpoint);
                    commandState = CommandState.INTERMEDIATE_3;
                }
                break;

            case INTERMEDIATE_3:
                if (arm.isArmInTolerance()) {
                    arm.sendArmSetpoint(armIntermediate3Setpoint);
                    commandState = CommandState.INTERMEDIATE_4;
                }
                break;

            case INTERMEDIATE_4:
                if (arm.isArmInTolerance()) {
                    arm.sendArmSetpoint(armIntermediate4Setpoint);
                    commandState = CommandState.EXIT;
                }
                break;

            case EXIT:
                arm.sendArmSetpoint(armExitSetpoint);
                break;
        } 
    }

    @Override
    public void end(boolean interrupted) {
        commandState = CommandState.STAGE_TRAJECTORY;
        System.out.println("ArmTrajectoryCmd Offline");
    }

    @Override
    public boolean isFinished() {
        if (commandState == CommandState.EXIT) {
            commandState = CommandState.STAGE_TRAJECTORY;
            return true;
        } else {
            return false;
        }
    }
}