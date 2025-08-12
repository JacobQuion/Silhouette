package frc.robot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Turret;
import frc.robot.commands.ArmFollowerCmd;
import frc.robot.commands.ArmJogCmd;
import frc.robot.commands.ArmLoopCmd;
import frc.robot.commands.ArmSetpointCmd;
import frc.robot.commands.ArmZeroOverrideCmd;
import frc.robot.commands.TurretFollowerCmd;
import frc.robot.commands.TurretJogCmd;
import frc.robot.commands.TurretLoopCmd;
import frc.robot.commands.TurretZeroOverrideCmd;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.TurretSetpointCmd;

public class RobotContainer {
  private final CommandXboxController operatorController = new CommandXboxController(Constants.OPERATOR_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
        //====================Arm Subsystem====================
        operatorController.leftTrigger().whileTrue(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_SETPOINT_B));
        operatorController.leftTrigger().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));

        operatorController.leftBumper().whileTrue(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_SETPOINT_C));
        operatorController.leftBumper().onFalse(new ArmSetpointCmd(Arm.getInstance(), Constants.ARM_ZERO_SETPOINT));

        //====================Turret Subsystem====================
        operatorController.rightTrigger().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_SETPOINT_A));
        operatorController.rightTrigger().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));

        operatorController.rightBumper().whileTrue(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_SETPOINT_E));
        operatorController.rightBumper().onFalse(new TurretSetpointCmd(Turret.getInstance(), Constants.TURRET_ZERO_SETPOINT));
  
        //====================Data Collection====================
        operatorController.a().onTrue(new ArmLoopCmd(Arm.getInstance(), Turret.getInstance()));
        operatorController.b().onTrue(new TurretLoopCmd(Arm.getInstance(), Turret.getInstance()));
        operatorController.x().onTrue(new ArmFollowerCmd(Arm.getInstance(), Turret.getInstance()));
        operatorController.y().onTrue(new TurretFollowerCmd(Arm.getInstance(), Turret.getInstance()));
  }
}
