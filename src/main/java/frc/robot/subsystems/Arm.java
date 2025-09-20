package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Arm extends SubsystemBase {
    private final TalonFX armMotor = new TalonFX(Constants.ARM_MOTOR_ID, "rio");

    private double setpoint;

    public static Arm getInstance() {
        return instance;
    }

    private static Arm instance = new Arm();

    public Arm() {
        System.out.println("====================Arm Subsystem Online====================");

        armMotor.setPosition(Constants.ABSOLUTE_ZERO);

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
        armMotorConfigs.Feedback.SensorToMechanismRatio = Constants.ARM_SENSOR_TO_MECHANISM_RATIO; 
        armMotorConfigs.Feedback.RotorToSensorRatio = Constants.ROTOR_TO_SENSOR_RATIO;

        //General Configurations
        var generalSlotConfigs = armMotorConfigs.Slot0;
        generalSlotConfigs.kG = Constants.ARM_kG;
        generalSlotConfigs.kP = Constants.ARM_kP;
        generalSlotConfigs.kI = Constants.ARM_kI;
        generalSlotConfigs.kD = Constants.ARM_kD;
        generalSlotConfigs.GravityType = GravityTypeValue.Arm_Cosine;

        // //Motion Magic
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
        SmartDashboard.putNumber("Arm Motor Position", getArmMotorPositionInDegrees());
        SmartDashboard.putNumber("Arm Motor Velocity", getArmMotorVelocityInDPS());
        SmartDashboard.putNumber("Arm Desired Setpoint", setpoint);
        SmartDashboard.putBoolean("Arm In Tolerance?", isArmInTolerance());
    }

    public void zeroArm() {
        armMotor.setPosition(Constants.ABSOLUTE_ZERO);
    }

    public double getArmMotorPositionInDegrees() {
        return armMotor.getPosition().getValueAsDouble() * Constants.ROTATIONS_TO_DEGREES_MULTIPLIER;
    }

    public double getArmMotorVelocityInDPS() {
        return armMotor.getVelocity().getValueAsDouble() * Constants.ROTATIONS_TO_DEGREES_MULTIPLIER;
    }
 
    public void setArmSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public void goToArmSetpoint() {
        final MotionMagicVoltage m_request = new MotionMagicVoltage(Constants.ABSOLUTE_ZERO).withEnableFOC(false);
        armMotor.setControl(m_request.withPosition(-this.setpoint * Constants.DEGREES_TO_ROTATIONS_MULTIPLIER));
    }

    public void sendArmSetpoint(double setpoint) {
        this.setpoint = setpoint;

        MotionMagicVoltage m_request = new MotionMagicVoltage(Constants.ABSOLUTE_ZERO).withEnableFOC(true);
        armMotor.setControl(m_request.withPosition(-setpoint * Constants.DEGREES_TO_ROTATIONS_MULTIPLIER));
    }

    public boolean isArmInTolerance() {
        if (Math.abs(setpoint + getArmMotorPositionInDegrees()) < Constants.ARM_SETPOINT_TOLERANCE) {
            return true;
        } else {
            return false;
        }
    }

    // public void HotRefreshArmConfig() {
    //     //General Configurations
    //     var generalSlotConfigs = new Slot0Configs();
    //     generalSlotConfigs.kG = SmartDashboard.getNumber("Arm kG", 0.0);
    //     generalSlotConfigs.kP = SmartDashboard.getNumber("Arm kP", 0.0);
    //     generalSlotConfigs.kI = SmartDashboard.getNumber("Arm kI", 0.0);
    //     generalSlotConfigs.kD = SmartDashboard.getNumber("Arm kD", 0.0);

    //     //Motion Magic
    //     var motionMagicConfigs = new MotionMagicConfigs();
    //     motionMagicConfigs.MotionMagicCruiseVelocity = SmartDashboard.getNumber("Arm kVelo", 0.0);
    //     motionMagicConfigs.MotionMagicAcceleration = SmartDashboard.getNumber("Arm kAccel", 0.0);

    //     //Applies Configs
    //     armMotor.getConfigurator().apply(generalSlotConfigs);
    //     armMotor.getConfigurator().apply(motionMagicConfigs);

    //     System.out.println("HotRefreshArmConfig Complete");
    // }
}