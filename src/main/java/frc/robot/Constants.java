package frc.robot;

public class Constants {
    //====================Misc Constants====================
    public static final double ABSOLUTE_ZERO = 0.0;
    public static final int OPERATOR_CONTROLLER_PORT = 0;
    public static final double JOYSTICK_JOG_SPEED_MULTIPLIER = 0.1;
    public static final double ARM_SENSOR_TO_MECHANISM_RATIO = 12.0 / 1.0;
    public static final double TURRET_SENSOR_TO_MECHANISM_RATIO = 4.0 / 1.0;
    public static final double ROTOR_TO_SENSOR_RATIO = 1.0 / 1.0;
    public static final double ROTATIONS_TO_DEGREES_MULTIPLIER = 360.0;
    public static final double DEGREES_TO_ROTATIONS_MULTIPLIER = 1.0 / 360.0;

    //====================Arm====================
    public static final int ARM_MOTOR_ID = 10;
    public static final double ARM_kP = 8.5; //10.0
    public static final double ARM_kI = 0.0; //0.0
    public static final double ARM_kD = 0.0; //0.0
    public static final double ARM_kG = -0.4; //-0.4
    public static final double ARM_kVelo = 0.25; //0.175, 0.25, 0.5
    public static final double ARM_kAccel = 0.5; //0.35, 0.5, 1.0
    public static final double ARM_CURRENT_LIMIT = 80.0;
    public static final double ARM_SETPOINT_TOLERANCE = 5.0;

    public static final double ARM_STAGE_DISABLE_SETPOINT = 7.75; //10.0
    public static final double ARM_SETPOINT_LOW = 20.5; //50.0
    public static final double ARM_ZERO_SETPOINT = 33.75; //135.0 = Parallel
    public static final double ARM_SETPOINT_A = 37.5; //150.0
    public static final double ARM_SETPOINT_B = 50.0; //200.0
    public static final double ARM_SETPOINT_C = 68.75; //275.0
    public static final double ARM_SETPOINT_D = 100; //325.0

    //====================Turret====================
    public static final int TURRET_MOTOR_ID = 20;
    public static final double TURRET_kP = 10.0; //25.0
    public static final double TURRET_kI = 0.0; //0.0
    public static final double TURRET_kD = 0.0; //0.0
    public static final double TURRET_kG = 0.0; //0.175
    public static final double TURRET_kVelo = 0.25; //0.175, 0.25, 0.5
    public static final double TURRET_kAccel = 0.5; //0.35, 0.5, 1.0
    public static final double TURRET_CURRENT_LIMIT = 80.0;
    public static final double TURRET_SETPOINT_TOLERANCE = 5.0;

    public static final double TURRET_ZERO_SETPOINT = 0.0;
    public static final double TURRET_SETPOINT_A = -75.0; //-300.0
    public static final double TURRET_SETPOINT_B = -22.5; //-90.0
    public static final double TURRET_SETPOINT_C = 0.0; //0.0
    public static final double TURRET_SETPOINT_D = 22.5; //90.0
    public static final double TURRET_SETPOINT_E = 75.0; //300.0
}