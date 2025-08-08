package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Turret extends SubsystemBase {
    private final TalonFX turretMotor = new TalonFX(Constants.TURRET_MOTOR_ID, "rio");

    private double setpoint;

    public static Turret getInstance() {
        return instance;
    }

    private static Turret instance = new Turret();

    public Turret() {
        System.out.println("====================Turret Subsystem Online====================");

        turretMotor.setPosition(Constants.ABSOLUTE_ZERO);

        //HotRefreshTurretConfig
        SmartDashboard.putNumber("Turret kG", 0.0);
        SmartDashboard.putNumber("Turret kP", 0.0);
        SmartDashboard.putNumber("Turret kI", 0.0);
        SmartDashboard.putNumber("Turret kD", 0.0);
        SmartDashboard.putNumber("Turret kVelo", 0.0);
        SmartDashboard.putNumber("Turret kAccel", 0.0);

        //====================Turret Subsystem====================
        var turretMotorConfigs = new TalonFXConfiguration();

        //Brake Mode
        turretMotorConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        //General Configurations
        var generalSlotConfigs = turretMotorConfigs.Slot0;
        generalSlotConfigs.kG = Constants.TURRET_kG;
        generalSlotConfigs.kP = Constants.TURRET_kP;
        generalSlotConfigs.kI = Constants.TURRET_kI;
        generalSlotConfigs.kD = Constants.TURRET_kD;

        //Motion Magic
        var motionMagicConfigs = turretMotorConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicCruiseVelocity = Constants.TURRET_kVelo;
        motionMagicConfigs.MotionMagicAcceleration = Constants.TURRET_kAccel;

        //Current limits
        var limitConfigs = turretMotorConfigs.CurrentLimits;
        limitConfigs.StatorCurrentLimit = Constants.TURRET_CURRENT_LIMIT;
        limitConfigs.StatorCurrentLimitEnable = true;

        //Applies Configs
        turretMotor.getConfigurator().apply(turretMotorConfigs);
    }

    @Override
    public void periodic() {
        logTurretData();
    }

    //====================Turret Methods====================
    public void setTurretMotorSpeed(double speed) {
        turretMotor.set(speed);
    }

    public void logTurretData() {
        SmartDashboard.putNumber("Turret Motor Position", getTurretMotorPositionInDegrees());
        SmartDashboard.putNumber("Turret Motor Velocity", getTurretMotorVelocityInDPS());
        SmartDashboard.putNumber("Turret Desired Setpoint", setpoint);
        SmartDashboard.putBoolean("Turret In Tolerance?", isTurretInTolerance());
    }

    public void zeroTurret() {
        turretMotor.setPosition(Constants.ABSOLUTE_ZERO);
    }

    public double getTurretMotorPositionInDegrees() {
        return turretMotor.getPosition().getValueAsDouble() * Constants.ROTATIONS_TO_DEGREES_MULTIPLIER;
    }

    public double getTurretMotorVelocityInDPS() {
        return turretMotor.getVelocity().getValueAsDouble() * Constants.ROTATIONS_TO_DEGREES_MULTIPLIER;
    }
 
    public void setTurretSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public void goToTurretSetpoint() {
        final MotionMagicVoltage m_request = new MotionMagicVoltage(Constants.ABSOLUTE_ZERO).withEnableFOC(false);
        turretMotor.setControl(m_request.withPosition(this.setpoint * Constants.DEGREES_TO_ROTATIONS_MULTIPLIER));
    }

    public void sendTurretSetpoint(double setpoint) {
        this.setpoint = setpoint;

        MotionMagicVoltage m_request = new MotionMagicVoltage(Constants.ABSOLUTE_ZERO).withEnableFOC(true);
        turretMotor.setControl(m_request.withPosition(setpoint * Constants.DEGREES_TO_ROTATIONS_MULTIPLIER));
    }

    public boolean isTurretInTolerance() {
        if (Math.abs(setpoint - getTurretMotorPositionInDegrees()) < Constants.TURRET_SETPOINT_TOLERANCE) {
            return true;
        } else {
            return false;
        }
    }

    public void HotRefreshTurretConfig() {
        //General Configurations
        var generalSlotConfigs = new Slot0Configs();
        generalSlotConfigs.kG = SmartDashboard.getNumber("Turret kG", 0.0);
        generalSlotConfigs.kP = SmartDashboard.getNumber("Turret kP", 0.0);
        generalSlotConfigs.kI = SmartDashboard.getNumber("Turret kI", 0.0);
        generalSlotConfigs.kD = SmartDashboard.getNumber("Turret kD", 0.0);

        //Motion Magic
        var motionMagicConfigs = new MotionMagicConfigs();
        motionMagicConfigs.MotionMagicCruiseVelocity = SmartDashboard.getNumber("Turret kVelo", 0.0);
        motionMagicConfigs.MotionMagicAcceleration = SmartDashboard.getNumber("Turret kAccel", 0.0);

        //Applies Configs
        turretMotor.getConfigurator().apply(generalSlotConfigs);
        turretMotor.getConfigurator().apply(motionMagicConfigs);

        System.out.println("HotRefreshTurretConfig Complete");
    }
}