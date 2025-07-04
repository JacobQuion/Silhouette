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

public class Arm extends SubsystemBase {
    private final TalonFX armMotor = new TalonFX(Constants.ARM_MOTOR_ID);

    private double setpoint;

    public static Arm getInstance() {
        return instance;
    }

    private static Arm instance = new Arm();

    public Arm() {
        System.out.println("====================Arm Subsystem Online====================");

        //HotRefreshArmConfig
        // SmartDashboard.putNumber("Arm kG", 0.0);
        // SmartDashboard.putNumber("Arm kP", 0.0);
        // SmartDashboard.putNumber("Arm kI", 0.0);
        // SmartDashboard.putNumber("Arm kD", 0.0);
        // SmartDashboard.putNumber("Arm kVelo", 0.0);
        // SmartDashboard.putNumber("Arm kAccel", 0.0);

        //====================Arm Subsystem====================
        var armMotorConfigs = new TalonFXConfiguration();

        //Brake Mode
        armMotorConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        //General Configurations
        var generalSlotConfigs = armMotorConfigs.Slot0;
        generalSlotConfigs.kG = Constants.ARM_kG;
        generalSlotConfigs.kP = Constants.ARM_kP;
        generalSlotConfigs.kI = Constants.ARM_kI;
        generalSlotConfigs.kD = Constants.ARM_kD;

        //Motion Magic
        var motionMagicConfigs = armMotorConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicCruiseVelocity = Constants.ARM_kVelo;
        motionMagicConfigs.MotionMagicAcceleration = Constants.ARM_kAccel;

        //Current limits
        var limitConfigs = armMotorConfigs.CurrentLimits;
        limitConfigs.StatorCurrentLimit = Constants.ARM_CURRENT_LIMIT;
        limitConfigs.StatorCurrentLimitEnable = true;

        //Applies Configs
        armMotor.getConfigurator().apply(armMotorConfigs);
    }

    @Override
    public void periodic() {
        logArmData();

    }

    //====================Arm Methods====================
    public void setArmMotorSpeed(double speed) {
        armMotor.set(speed);
    }

    public void logArmData() {
        SmartDashboard.putNumber("Arm Motor Position", getArmMotorEncoder());
        SmartDashboard.putNumber("Arm Motor Velocity", getArmMotorVelocity());
        SmartDashboard.putNumber("Arm Desired Setpoint", setpoint);
        SmartDashboard.putBoolean("Elevator In Tolerance?", isArmInTolerance());
    }

    public void zeroArm() {
        armMotor.setPosition(Constants.ABSOLUTE_ZERO);
    }

    public double getArmMotorEncoder() {
        return armMotor.getPosition().getValueAsDouble();
    }

    public double getArmMotorVelocity() {
        return armMotor.getVelocity().getValueAsDouble();
    }
 
    public void setArmSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public void goToArmSetpoint() {
        final MotionMagicVoltage m_request = new MotionMagicVoltage(Constants.ABSOLUTE_ZERO).withEnableFOC(true);
        armMotor.setControl(m_request.withPosition(this.setpoint));
        armMotor.setControl(m_request.withPosition(this.setpoint));
    }

    public boolean isArmInTolerance() {
        if (Math.abs(setpoint - getArmMotorEncoder()) < Constants.ARM_SETPOINT_TOLERANCE) {
            return true;
        } else {
            return false;
        }
    }

    public void HotRefreshArmConfig() {
        //General Configurations
        var generalSlotConfigs = new Slot0Configs();
        generalSlotConfigs.kG = SmartDashboard.getNumber("Arm kG", 0.0);
        generalSlotConfigs.kP = SmartDashboard.getNumber("Arm kP", 0.0);
        generalSlotConfigs.kI = SmartDashboard.getNumber("Arm kI", 0.0);
        generalSlotConfigs.kD = SmartDashboard.getNumber("Arm kD", 0.0);

        //Motion Magic
        var motionMagicConfigs = new MotionMagicConfigs();
        motionMagicConfigs.MotionMagicCruiseVelocity = SmartDashboard.getNumber("Arm kVelo", 0.0);
        motionMagicConfigs.MotionMagicAcceleration = SmartDashboard.getNumber("Arm kAccel", 0.0);

        //Applies Configs
        armMotor.getConfigurator().apply(generalSlotConfigs);
        armMotor.getConfigurator().apply(motionMagicConfigs);

        System.out.println("HotRefreshArmConfig Complete");
    }
}