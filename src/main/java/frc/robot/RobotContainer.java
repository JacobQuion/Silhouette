package frc.robot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Turret;

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
        operatorController.povUp().whileTrue(new TurretJogCmd(Turret.getInstance(), () -> -operatorController.getRightY() * Constants.JOYSTICK_JOG_SPEED_MULTIPLIER));
        operatorController.leftBumper().onTrue(new InstantCommand(Turret.getInstance()::HotRefreshTurretConfig));
        operatorController.povDown().onTrue(new TurretZeroOverrideCmd(Turret.getInstance()));

        operatorController.y().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_TRIAL_SETPOINT));
        operatorController.y().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.ABSOLUTE_ZERO));
  }
}
