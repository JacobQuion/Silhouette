package frc.robot;

public class Constants {
    public static final double ABSOLUTE_ZERO = 0.0;
    
    //====================Controller====================
    public static final int OPERATOR_CONTROLLER_PORT = 0;
    public static final double JOYSTICK_JOG_SPEED_MULTIPLIER = 0.1;
    public static final double ROTATIONS_TO_DEGREES_MULTIPLIER = 360.0;
    public static final double DEGREES_TO_ROTATIONS_MULTIPLIER = 1.0 / 360.0;

    //====================Arm====================
    public static final int ARM_MOTOR_ID = 10;
    public static final double ARM_kP = 10.0; //10.0
    public static final double ARM_kI = 0.0; //0.0
    public static final double ARM_kD = 0.0; //0.0
    public static final double ARM_kG = -0.4; //-0.4
    public static final double ARM_kVelo = 1.0; //0.5, 1.0, 2.0
    public static final double ARM_kAccel = 2.0; //1.0, 2.0, 4.0
    public static final double ARM_CURRENT_LIMIT = 80.0;
    public static final double ARM_SETPOINT_TOLERANCE = 5.0;
 
    public static final double ARM_ZERO_SETPOINT = 85.0;
    public static final double ARM_SETPOINT_A = 125.0;
    public static final double ARM_SETPOINT_B = 150.0;
    public static final double ARM_SETPOINT_C = 200.0;
    public static final double ARM_SETPOINT_D = 225.0;

    //====================Turret====================
    public static final int TURRET_MOTOR_ID = 20;
    public static final double TURRET_kP = 10.0; //10.0
    public static final double TURRET_kI = 0.0; //0.0
    public static final double TURRET_kD = 0.0; //0.0
    public static final double TURRET_kG = 0.0; //0.175
    public static final double TURRET_kVelo = 1.0; //0.5, 1.0, 2.0
    public static final double TURRET_kAccel = 2.0; //1.0, 2.0, 4.0
    public static final double TURRET_CURRENT_LIMIT = 80.0;
    public static final double TURRET_SETPOINT_TOLERANCE = 5.0;

    public static final double TURRET_ZERO_SETPOINT = 0.0;
    public static final double TURRET_SETPOINT_A = -180.0;
    public static final double TURRET_SETPOINT_B = -90.0;
    public static final double TURRET_SETPOINT_C = -0.0;
    public static final double TURRET_SETPOINT_D = 90.0;
    public static final double TURRET_SETPOINT_E = 180.0;
}