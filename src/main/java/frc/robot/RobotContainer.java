package frc.robot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Turret;
import frc.robot.commands.ArmJogCmd;
import frc.robot.commands.ArmSetpointCmd;
import frc.robot.commands.ArmZeroOverrideCmd;
import frc.robot.commands.TurretJogCmd;
import frc.robot.commands.TurretZeroOverrideCmd;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.TurretSetpointCmd;

public class RobotContainer {
  private final CommandXboxController operatorController = new CommandXboxController(Constants.OPERATOR_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
        //====================Arm Controller Bindings====================
        operatorController.leftTrigger().whileTrue(new ArmJogCmd(Arm.getInstance(), () -> -operatorController.getRightY() * Constants.JOYSTICK_JOG_SPEED_MULTIPLIER));
        operatorController.leftBumper().onTrue(new InstantCommand(Arm.getInstance()::HotRefreshArmConfig));
        operatorController.povLeft().onTrue(new ArmZeroOverrideCmd(Arm.getInstance()));

        operatorController.y().whileTrue(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_TRIAL_SETPOINT));
        operatorController.y().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ABSOLUTE_ZERO));

        //====================Turret Controller Bindings====================
        operatorController.rightTrigger().whileTrue(new TurretJogCmd(Turret.getInstance(), () -> -operatorController.getRightX() * Constants.JOYSTICK_JOG_SPEED_MULTIPLIER));
        operatorController.rightBumper().onTrue(new InstantCommand(Turret.getInstance()::HotRefreshTurretConfig));
        operatorController.povRight().onTrue(new TurretZeroOverrideCmd(Turret.getInstance()));

        operatorController.a().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_TRIAL_SETPOINT));
        operatorController.a().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.ABSOLUTE_ZERO));
  }
}
