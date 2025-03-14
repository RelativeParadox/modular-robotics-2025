package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.sequencer.Program;

public final class Constants {

    public static class DriveConstants
    {
        //Left Set of Motors
        public static final int leftLeaderID = 2;
        public static final int leftFollowerID = 3;

        //Right Set of Motors
        public static final int rightLeaderID = 4;
        public static final int rightFollowerID = 5;

        //Stall Limit
        public static final int motorStallLimit = 20;
		public static final MotorType motorType = MotorType.kBrushless;

        public static final double deadzone = 0.15;
    }

	public static class RollerConstants
	{
		//Roller Motor ID
		public static final int rollerMotorID = 6;
		public static final MotorType rollerMotorType = MotorType.kBrushless;
	}

    public static final Program test_program = new Program(
        new Vector3[]{
            new Vector3(3.0, 0, 0),
            new Vector3(0, 0, 0)
        },
        new int[]{
           0,
           1
        },
        new int[][]{
            {},
            {}
        }
    );


    // enum DriverControl {

    // }

    // enum OperatorControl {

    // }
}
