package frc.robot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Turret;
import frc.robot.commands.ArmJogCmd;
import frc.robot.commands.ArmPendulumCmd;
import frc.robot.commands.ArmSetpointCmd;
import frc.robot.commands.ArmTrajectoryCmd;
import frc.robot.commands.ArmZeroOverrideCmd;
import frc.robot.commands.ComboCmd;
import frc.robot.commands.ProxyCmd;
import frc.robot.commands.TurretJogCmd;
import frc.robot.commands.TurretPendulumCmd;
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
        //operatorController.back().onTrue(new ComboCmd(Turret.getInstance(), Arm.getInstance()));

        //====================Arm Controller Bindings (Triggers + Bumpers)====================
        operatorController.leftBumper().onTrue(new InstantCommand(Arm.getInstance()::HotRefreshArmConfig));

        operatorController.leftTrigger().whileTrue(new ArmSetpointCmd(Arm.getInstance(), 30.0));
        operatorController.leftTrigger().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));

        operatorController.leftStick().onTrue(new ArmPendulumCmd(Arm.getInstance()));

        //====================Turret Controller Bindings (ABXY, Start, Back)====================
        //operatorController.rightBumper().onTrue(new InstantCommand(Turret.getInstance()::HotRefreshTurretConfig));

        operatorController.rightTrigger().whileTrue(new TurretSetpointCmd(Turret.getInstance(), 90.0));
        operatorController.rightTrigger().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        operatorController.b().whileTrue(new TurretSetpointCmd(Turret.getInstance(), -90.0));
        operatorController.b().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        operatorController.rightStick().onTrue(new TurretPendulumCmd(Turret.getInstance()));
  }
}
