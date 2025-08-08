package frc.robot;
import static edu.wpi.first.units.Units.*;

public class Constants {
    public static final double ABSOLUTE_ZERO = 0.0;
    
    //====================Controller====================
    public static final int OPERATOR_CONTROLLER_PORT = 0;
    public static final double JOYSTICK_JOG_SPEED_MULTIPLIER = 0.1;
    public static final double ROTATIONS_TO_DEGREES_MULTIPLIER = 360.0;
    public static final double DEGREES_TO_ROTATIONS_MULTIPLIER = 1.0 / 360.0;

    //====================Data Collection====================
    public static final double ARM_PENDULUM_SETPOINT_1 = 0.0;
    public static final double ARM_PENDULUM_SETPOINT_2 = 45.0;
    public static final double TURRET_PENDULUM_SETPOINT_1 = 0.0;
    public static final double TURRET_PENDULUM_SETPOINT_2 = 90.0;
    //====================Arm====================
    public static final int ARM_MOTOR_ID = 10;

    public static final double ARM_kP = 15.0; //10.0
    public static final double ARM_kI = 0.0; //0.0
    public static final double ARM_kD = 0.01; //0.0
    public static final double ARM_kG = 0.3; //0.3
    public static final double ARM_kVelo = 2.0; //0.5
    public static final double ARM_kAccel = 4.0; //1.0
    public static final double ARM_CURRENT_LIMIT = 80.0;
    public static final double ARM_SETPOINT_TOLERANCE = 5.0;
 
    public static final double ARM_ZERO_SETPOINT = 10.0; //27.0
    public static final double ARM_SETPOINT_1 = 50.4; //0.14
    public static final double ARM_SETPOINT_2 = 30.0; //0.19
    public static final double ARM_SETPOINT_3 = 70.0; //0.25
    public static final double ARM_SETPOINT_4 = 90.0; //108

    public static final double ARM_TRAJ_1_IMP = 0.0; 
    public static final double ARM_TRAJ_2_IMP = 36.0; 
    public static final double ARM_TRAJ_3_IMP = 54.0; 
    public static final double ARM_TRAJ_4_IMP = 72.0; 
    public static final double ARM_TRAJ_5_IMP = 90.0; 
    public static final double ARM_TRAJ_6_IMP = 108.0; 

    //====================Turret====================
    public static final int TURRET_MOTOR_ID = 20;

    public static final double TURRET_kP = 30.5; //30.5
    public static final double TURRET_kI = 0.0; //0.0
    public static final double TURRET_kD = 0.0; //0.0
    public static final double TURRET_kG = 0.075; //0.075
    public static final double TURRET_kVelo = 0.5; //1.0
    public static final double TURRET_kAccel = 1.0; //2.0
    public static final double TURRET_CURRENT_LIMIT = 40.0;
    public static final double TURRET_SETPOINT_TOLERANCE = 5.0;

    public static final double TURRET_ZERO_SETPOINT = 0.0; //0.0
    public static final double TURRET_SETPOINT_1 = 90.0; //0.25
    public static final double TURRET_SETPOINT_2 = 180.0; //0.5
    public static final double TURRET_SETPOINT_3 = -90.0; //-0.25
    public static final double TURRET_SETPOINT_4 = -180.0; //-0.5
    public static final double TURRET_FULL_SPIN_SETPOINT_1 = -360.0; //-1.0
    public static final double TURRET_FULL_SPIN_SETPOINT_2 = 360.0; //1.0

    public static final double TURRET_TRAJ_1_IMP = 0.0; 
    public static final double TURRET_TRAJ_2_IMP = 15.0; 
    public static final double TURRET_TRAJ_3_IMP = 30.0; 
    public static final double TURRET_TRAJ_4_IMP = 45.0; 
    public static final double TURRET_TRAJ_5_IMP = 60.0; 
    public static final double TURRET_TRAJ_6_IMP = 90.0; 
}