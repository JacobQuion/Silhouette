package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Turret;

public class ProxyCmd extends Command {
    private final Turret turret;
    private final Arm arm;

    private enum CommandState {
        STAGE_TRAJ,
        SEQ_1,
        SEQ_2,
        SEQ_3,
        SEQ_4,
        SEQ_5,
        SEQ_6,
        SEQ_7,
        SEQ_8,
        EXIT
    }

    private CommandState commandState;

    public ProxyCmd(Turret turret, Arm arm) {
        commandState = CommandState.STAGE_TRAJ;
        this.turret = Turret.getInstance();
        this.arm = Arm.getInstance();

        addRequirements(turret);
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        System.out.println("ProxyCmd Online");
    }

    @Override
    public void execute() {
        SmartDashboard.putString("ProxyCmd State", commandState.toString());

        switch (commandState) {
            case STAGE_TRAJ:
                turret.sendTurretSetpoint(Constants.TURRET_TRAJ_2_IMP);
                commandState = CommandState.SEQ_1;
                break;

            case SEQ_1:
                if (turret.isTurretInTolerance()) {
                    arm.sendArmSetpoint(Constants.ARM_TRAJ_2_IMP);
                    commandState = CommandState.SEQ_2;
                }
                break;

            case SEQ_2:
                if (arm.isArmInTolerance()) {
                    arm.sendArmSetpoint(Constants.ARM_ZERO_SETPOINT);
                    commandState = CommandState.SEQ_3;
                }
                break;

            case SEQ_3:
                if (arm.isArmInTolerance()) {
                    turret.sendTurretSetpoint(Constants.TURRET_TRAJ_4_IMP);
                    commandState = CommandState.SEQ_4;
                }
                break;

            case SEQ_4:
                if (turret.isTurretInTolerance()) {
                    arm.sendArmSetpoint(Constants.ARM_TRAJ_2_IMP);
                    commandState = CommandState.SEQ_5;
                }
                break;

            case SEQ_5:
                if (arm.isArmInTolerance()) {
                    arm.sendArmSetpoint(Constants.ARM_ZERO_SETPOINT);
                    commandState = CommandState.SEQ_6;
                }
                break;

            case SEQ_6:
                if (arm.isArmInTolerance()) {
                    turret.sendTurretSetpoint(Constants.TURRET_TRAJ_6_IMP);
                    commandState = CommandState.SEQ_7;
                }

            case SEQ_7:
                if (turret.isTurretInTolerance()) {
                    arm.sendArmSetpoint(Constants.ARM_TRAJ_2_IMP);
                    commandState = CommandState.SEQ_8;
                }
                break;

            case SEQ_8:
                if (arm.isArmInTolerance()) {
                    arm.sendArmSetpoint(Constants.ARM_ZERO_SETPOINT);
                    commandState = CommandState.EXIT;
                }
                break;

            case EXIT:
                turret.sendTurretSetpoint(Constants.ABSOLUTE_ZERO);
                break;
        } 
    }

    @Override
    public void end(boolean interrupted) {
        commandState = CommandState.STAGE_TRAJ;
        System.out.println("ProxyCmd Offline");
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