package frc.robot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Turret;
import frc.robot.commands.ArmJogCmd;
import frc.robot.commands.ArmSetpointCmd;
import frc.robot.commands.ArmTrajectoryCmd;
import frc.robot.commands.ArmZeroOverrideCmd;
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
        //====================Arm Controller Bindings====================
        operatorController.leftTrigger().whileTrue(new ArmJogCmd(Arm.getInstance(), () -> -operatorController.getRightY() * Constants.JOYSTICK_JOG_SPEED_MULTIPLIER));
        //operatorController.leftBumper().onTrue(new InstantCommand(Arm.getInstance()::HotRefreshArmConfig));
        operatorController.povLeft().onTrue(new ArmZeroOverrideCmd(Arm.getInstance()));

        operatorController.a().whileTrue(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_INTERMEDIATE_SETPOINT));
        operatorController.a().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));

        operatorController.x().whileTrue(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_TIP_UP_SETPOINT));
        operatorController.x().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));

        //====================Turret Controller Bindings====================
        operatorController.rightTrigger().whileTrue(new TurretJogCmd(Turret.getInstance(), () -> operatorController.getRightX() * Constants.JOYSTICK_JOG_SPEED_MULTIPLIER));
        //operatorController.rightBumper().onTrue(new InstantCommand(Turret.getInstance()::HotRefreshTurretConfig));
        operatorController.povRight().onTrue(new TurretZeroOverrideCmd(Turret.getInstance()));

        operatorController.b().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_RIGHT_INTERMEDIATE_SETPOINT));
        operatorController.b().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        operatorController.y().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_LEFT_INTERMEDIATE_SETPOINT));
        operatorController.y().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        //====================Trajectory Controller Bindings====================
        operatorController.povUp().whileTrue(new ArmTrajectoryCmd(Arm.getInstance(), 0.0, 0.4, 0.8, 0.12, 0.16, 0.20));
        operatorController.povUp().onFalse(new ArmTrajectoryCmd(Arm.getInstance(), 0.20, 0.16, 0.12, 0.8, 0.4, 0.0));

        operatorController.povDown().whileTrue(new TurretTrajectoryCmd(Turret.getInstance(), 0.0, 0.4, 0.8, 0.12, 0.16, 0.20));
        operatorController.povDown().onFalse(new TurretTrajectoryCmd(Turret.getInstance(), 0.20, 0.16, 0.12, 0.8, 0.4, 0.0));
  }
}
