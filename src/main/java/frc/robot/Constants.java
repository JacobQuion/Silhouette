package frc.robot;
import static edu.wpi.first.units.Units.*;

public class Constants {
    public static final double ABSOLUTE_ZERO = 0.0;
    
    //====================Controller====================
    public static final int OPERATOR_CONTROLLER_PORT = 0;
    public static final double JOYSTICK_JOG_SPEED_MULTIPLIER = 0.035;

    //====================Arm====================
    public static final int ARM_MOTOR_ID = 10;

    public static final double ARM_kP = 25.0; //20.0
    public static final double ARM_kI = 0.0; //0.05
    public static final double ARM_kD = 0.0; //0.7
    public static final double ARM_kG = 0.0325; //0.325
    public static final double ARM_kVelo = 1.0; //1.0
    public static final double ARM_kAccel = 2.0; //2.0
    public static final double ARM_CURRENT_LIMIT = 40.0;
    public static final double ARM_SETPOINT_TOLERANCE = 0.025;
 
    public static final double ARM_ZERO_SETPOINT = 0.06; //0.0
    public static final double ARM_INTERMEDIATE_SETPOINT = 0.12;//0.06
    public static final double ARM_TIP_UP_SETPOINT = 0.2; //0.12

    //====================Turret====================
    public static final int TURRET_MOTOR_ID = 20;

    public static final double TURRET_kP = 30.5; //5.5
    public static final double TURRET_kI = 0.0; //0.0
    public static final double TURRET_kD = 0.0; //0.5
    public static final double TURRET_kG = 0.075; //0.075
    public static final double TURRET_kVelo = 1.0; //1.0
    public static final double TURRET_kAccel = 2.0; //2.0
    public static final double TURRET_CURRENT_LIMIT = 40.0;
    public static final double TURRET_SETPOINT_TOLERANCE = 0.05;

    public static final double TURRET_ZERO_SETPOINT = 0.0;
    public static final double TURRET_RIGHT_INTERMEDIATE_SETPOINT = 0.5;
    public static final double TURRET_LEFT_INTERMEDIATE_SETPOINT = -0.5; //-0.1
}