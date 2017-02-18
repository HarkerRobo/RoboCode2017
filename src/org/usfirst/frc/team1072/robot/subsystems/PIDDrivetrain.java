/**
 * 
 */
package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap.PID;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Encoders;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Talons;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDInterface;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author joelmanning
 *
 */
public class PIDDrivetrain extends Drivetrain {
	
	public void enable(){
		((PIDTrainSide) left).enable();
		((PIDTrainSide) right).enable();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.usfirst.frc.team1072.robot.subsystems.Drivetrain#initializeSides()
	 */
	@Override
	protected void initializeSides() {
		left = new PIDTrainSide(Talons.FL, Talons.BL, Encoders.LA, Encoders.LB,
				false, false, false);
		right = new PIDTrainSide(Talons.FR, Talons.BR, Encoders.RA,
				Encoders.RB, true, true, true);
	}
	
	public void disable(){
		((PIDTrainSide) left).disable();
		((PIDTrainSide) right).disable();
	}
	
	public class PIDTrainSide extends TrainSide implements PIDSource, PIDOutput {
		
		private PIDController pid;
		private double prevSpeed;
		
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
			prevSpeed = 0;
			pid = new PIDController(PID.Wheels.P, PID.Wheels.I, PID.Wheels.D, this, this);
			pid.setInputRange(-MAX_SPEED, MAX_SPEED);
			pid.setOutputRange(-1, 1);
		}
		
		/**
		 * 
		 */
		public void disable() {
			pid.disable();
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
			pid.setSetpoint(speed * MAX_SPEED);
		}

		/* (non-Javadoc)
		 * @see edu.wpi.first.wpilibj.PIDOutput#pidWrite(double)
		 */
		@Override
		public void pidWrite(double output) {
			prevSpeed += output;
			rawDrive(prevSpeed);
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
			return encoder.getPIDSourceType();
		}

		/* (non-Javadoc)
		 * @see edu.wpi.first.wpilibj.PIDSource#pidGet()
		 */
		@Override
		public double pidGet() {
			return getRate();
		}

		/**
		 * 
		 * @see edu.wpi.first.wpilibj.PIDController#enable()
		 */
		public void enable() {
			pid.enable();
		}
		
		@Override
		public void toSmartDashboard(String name) {
			super.toSmartDashboard(name);
			/*double _P = SmartDashboard.getNumber(name + "P Constant", pid.getP());
			double _I = SmartDashboard.getNumber(name + "I Constant", pid.getI());
			double _D = SmartDashboard.getNumber(name + "D Constant", pid.getD());
			pid.setPID(_P, _I, _D);*/
			SmartDashboard.putData(name + "PID", pid);
		}
	}
}
