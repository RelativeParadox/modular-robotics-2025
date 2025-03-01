package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;

public final class Constants {

    public static class DriveConstants
    {
        //Left Set of Motors
        public static final int leftLeaderID = 1;
        public static final int leftFollowerID = 2;

        //Right Set of Motors
        public static final int rightLeaderID = 3;
        public static final int rightFollowerID = 4;

        //Stall Limit
        public static final int motorStallLimit = 20;
		public static final MotorType motorType = MotorType.kBrushed;
    }

	public static class RollerConstants
	{
		//Roller Motor ID
		public static final int rollerMotorID = 6;
		public static final MotorType rollerMotorType = MotorType.kBrushless;
	}

    // enum DriverControl {

    // }

    // enum OperatorControl {

    // }
}
