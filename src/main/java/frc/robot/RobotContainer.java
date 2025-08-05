package frc.robot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Turret;
import frc.robot.commands.ArmJogCmd;
import frc.robot.commands.ArmSetpointCmd;
import frc.robot.commands.ArmTrajectoryCmd;
import frc.robot.commands.ArmZeroOverrideCmd;
import frc.robot.commands.SwingCmd;
import frc.robot.commands.TurretJogCmd;
import frc.robot.commands.TurretZeroOverrideCmd;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.TurretSetpointCmd;
import frc.robot.commands.TurretTrajectoryCmd;

public class RobotContainer {
  private final CommandXboxController operatorController = new CommandXboxController(Constants.OPERATOR_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
        //====================Arm Controller Bindings (Triggers + Bumpers)====================
        //operatorController.leftTrigger().whileTrue(new ArmJogCmd(Arm.getInstance(), () -> -operatorController.getRightY() * Constants.JOYSTICK_JOG_SPEED_MULTIPLIER));
        //operatorController.leftBumper().onTrue(new InstantCommand(Arm.getInstance()::HotRefreshArmConfig));
        //operatorController.povLeft().onTrue(new ArmZeroOverrideCmd(Arm.getInstance()));

        operatorController.leftTrigger().whileTrue(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_SETPOINT_1));
        operatorController.leftTrigger().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));

        operatorController.leftBumper().whileTrue(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_SETPOINT_2));
        operatorController.leftBumper().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));

        operatorController.rightBumper().whileTrue(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_SETPOINT_3));
        operatorController.rightBumper().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));

        operatorController.rightTrigger().whileTrue(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_SETPOINT_4));
        operatorController.rightTrigger().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));

        //====================Turret Controller Bindings (ABXY, Start, Back)====================
        //operatorController.rightTrigger().whileTrue(new TurretJogCmd(Turret.getInstance(), () -> operatorController.getRightX() * Constants.JOYSTICK_JOG_SPEED_MULTIPLIER));
        //operatorController.rightBumper().onTrue(new InstantCommand(Turret.getInstance()::HotRefreshTurretConfig));
        //operatorController.povRight().onTrue(new TurretZeroOverrideCmd(Turret.getInstance()));

        operatorController.a().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_SETPOINT_1));
        operatorController.a().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        operatorController.b().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_SETPOINT_2));
        operatorController.b().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        operatorController.x().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_SETPOINT_3));
        operatorController.x().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        operatorController.y().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_SETPOINT_4));
        operatorController.y().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        // operatorController.start().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_FULL_SPIN_SETPOINT_1));
        // operatorController.start().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));
        
        operatorController.start().onTrue(new SwingCmd(Arm.getInstance(), Constants.ARM_SETPOINT_1));
        operatorController.start().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));
        operatorController.start().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        operatorController.back().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_FULL_SPIN_SETPOINT_2));
        operatorController.back().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        //====================Trajectory Controller Bindings====================
        operatorController.povUp().whileTrue(new ArmTrajectoryCmd(Arm.getInstance(), 
        Constants.ARM_TRAJ_1_IMP, Constants.ARM_TRAJ_2_IMP, Constants.ARM_TRAJ_3_IMP,
        Constants.ARM_TRAJ_4_IMP, Constants.ARM_TRAJ_5_IMP, Constants.ARM_TRAJ_6_IMP));

        operatorController.povUp().onFalse(new ArmTrajectoryCmd(Arm.getInstance(), 
        Constants.ARM_TRAJ_6_IMP, Constants.ARM_TRAJ_5_IMP, Constants.ARM_TRAJ_4_IMP,
        Constants.ARM_TRAJ_3_IMP, Constants.ARM_TRAJ_2_IMP, Constants.ARM_TRAJ_1_IMP));

        operatorController.povDown().whileTrue(new TurretTrajectoryCmd(Turret.getInstance(), 
        Constants.TURRET_TRAJ_1_IMP, Constants.TURRET_TRAJ_2_IMP, Constants.TURRET_TRAJ_3_IMP,
        Constants.TURRET_TRAJ_4_IMP, Constants.TURRET_TRAJ_5_IMP, Constants.TURRET_TRAJ_6_IMP));

        operatorController.povDown().onFalse(new TurretTrajectoryCmd(Turret.getInstance(), 
        Constants.TURRET_TRAJ_6_IMP, Constants.TURRET_TRAJ_5_IMP, Constants.TURRET_TRAJ_4_IMP,
        Constants.TURRET_TRAJ_3_IMP, Constants.TURRET_TRAJ_2_IMP, Constants.TURRET_TRAJ_1_IMP));
  }
}
