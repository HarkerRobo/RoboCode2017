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
			public static final double P = 0.006;
			public static final double I = 0.00001;
			public static final double D = 0;
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
				public static final int LA = 8;
				public static final int LB = 9;
				public static final int RA = 6;
				public static final int RB = 7;
			}
		}
		public static class Winches {
			public static final int A = 5;
			public static final int B = 6;
		}
	}
	public class Gears {
		public static final int SHIFTER_F = 1;
		public static final int SHIFTER_R = 6;
		public static final int PUSHER_F = 2;
		public static final int PUSHER_R = 5;
		public static final int CLOSER_F = 4;
		public static final int CLOSER_R = 3;
	}
	public class Distances {
		public static final double robotHeight = 7.125 ; // half robot height
		public static final double robotWidth = 15; // half robot width
		public static final double hexToField = 162; // center of hexagon to edge of field
		public static final double boilerDiag = 36.75; // half of the boiler diagonal
		public static final double widthOfField  = 289.24;
		public static final double triangleHeight = hexToField - boilerDiag - robotWidth; // long part of triangle
		public static final double triangleWidth = 1.732*triangleHeight; // short part of triangle
		public static final double initDistance = widthOfField - robotHeight - triangleWidth; // initial drive distance
		public static final double perpGearDistance = (101/6.0);
		public static final double normalCurrent = 20; //normal value for talon SRX current
		public static final double spikeCurrent = 60; //min value for current spike
		public static final double velocityToGetCurrentSpike = 40;
	}
}
