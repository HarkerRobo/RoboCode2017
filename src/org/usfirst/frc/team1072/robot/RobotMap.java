package org.usfirst.frc.team1072.robot;

import edu.wpi.first.wpilibj.Encoder;

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
	public static class PID {
		public static class Wheels {
			public static final double P = 1;
			public static final double I = 1;
			public static final double D = 1;
		}
		public static class MoveDist {
			public static final double P = 1;
			public static final double I = 1;
			public static final double D = 1;
		}
		public static class TurnAngle {
			public static final double P = 1;
			public static final double I = 1;
			public static final double D = 1;
		}
	}
	public static final int XBOX = 0;
	public static class Robot {
		public static final int GYRO = 0;
		public static class Drive {
			public static class Talons {
				public static final int FR = 4;
				public static final int FL = 1;
				public static final int BR = 2;
				public static final int BL = 3;
			}
			public static class Encoders {
				public static final int LA = 0;
				public static final int LB = 1;
				public static final int RA = 2;
				public static final int RB = 3;
			}
		}
		public static class Winches {
			public static int port = 5;
		}
	}
	public class Gears {
		public static final int SHIFT = 1;
		public static final int CLOSE = 2;
		public static final int PUSH = 5; // 5 extends push, 2 retracts
	}
}
