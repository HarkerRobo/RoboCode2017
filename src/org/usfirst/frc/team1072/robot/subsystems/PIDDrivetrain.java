/**
 * 
 */
package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.RobotMap.PID;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Encoders;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Talons;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * @author joelmanning
 *
 */
public class PIDDrivetrain extends Drivetrain {
	
	public static final double MAX_SPEED = 30000;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.usfirst.frc.team1072.robot.subsystems.Drivetrain#initializeSides()
	 */
	@Override
	protected void initializeSides() {
		left = new PIDTrainSide(Talons.FL, Talons.BL, Encoders.LA, Encoders.LB,
				true, true, false);
		right = new PIDTrainSide(Talons.FR, Talons.BR, Encoders.RA,
				Encoders.RB, false, false, true);
	}
	
	class PIDTrainSide extends TrainSide implements PIDSource, PIDOutput {
		
		private PIDController pid;
		
		/**
		 * @param frontChannel
		 * @param backChannel
		 * @param encoderA
		 * @param encoderB
		 * @param frontReversed
		 * @param backReversed
		 * @param encoderReversed
		 */
		public PIDTrainSide(int frontChannel, int backChannel, int encoderA,
				int encoderB, boolean frontReversed, boolean backReversed,
				boolean encoderReversed) {
			super(frontChannel, backChannel, encoderA, encoderB, frontReversed,
					backReversed, encoderReversed);
			pid = new PIDController(PID.Wheels.P, PID.Wheels.I, PID.Wheels.D, this, this);
			pid.setInputRange(-40000, 40000);
			pid.setOutputRange(-1, 1);
		}
		
		/**
		 * @param frontChannel
		 * @param backChannel
		 * @param encoderA
		 * @param encoderB
		 */
		public PIDTrainSide(int frontChannel, int backChannel, int encoderA,
				int encoderB) {
			this(frontChannel, backChannel, encoderA, encoderB, false, false, false);
		}
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.usfirst.frc.team1072.robot.subsystems.TrainSide#drive(double)
		 */
		@Override
		public void drive(double speed) {
			// TODO Auto-generated method stub
			super.drive(speed);
		}

		/* (non-Javadoc)
		 * @see edu.wpi.first.wpilibj.PIDOutput#pidWrite(double)
		 */
		@Override
		public void pidWrite(double output) {
			rawDrive(output);
		}

		/* (non-Javadoc)
		 * @see edu.wpi.first.wpilibj.PIDSource#setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType)
		 */
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			encoder.setPIDSourceType(pidSource);
		}

		/* (non-Javadoc)
		 * @see edu.wpi.first.wpilibj.PIDSource#getPIDSourceType()
		 */
		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see edu.wpi.first.wpilibj.PIDSource#pidGet()
		 */
		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
