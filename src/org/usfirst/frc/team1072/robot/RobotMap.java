package org.usfirst.frc.team1072.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static double P;
	public static double I;
	public static double D;
	public static final int XBOX = 0;
	public class Robot {
		public static final int GYRO = 0;
		public class Drive {
			public static final int FR = 0;
			public static final int FL = 0;
			public static final int BR = 0;
			public static final int BL = 0;
		}
	}
}
