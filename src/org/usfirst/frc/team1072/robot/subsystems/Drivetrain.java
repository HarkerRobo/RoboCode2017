package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.Controls;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Encoders;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Talons;
import org.usfirst.frc.team1072.robot.commands.ArcadeDriveCommand;
import org.usfirst.frc.team1072.robot.commands.DriveStaticCommand;
import org.usfirst.frc.team1072.robot.commands.ManualDriveCommand;
import org.usfirst.frc.team1072.robot.commands.TankDriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	public static final double MAX_SPEED = 190;
	
	public enum DriveControl {
		TANK, ARCADE
	}
	
	protected TrainSide left;
	protected TrainSide right;
	
	public Drivetrain() {
		initializeSides();
	}
	
	protected void initializeSides() {
		left = new TrainSide(Talons.FL, Talons.BL, Encoders.LA, Encoders.LB, false, false, false);
		right = new TrainSide(Talons.FR, Talons.BR, Encoders.RA, Encoders.RB, true, true, true);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ManualDriveCommand());
	}
	
	/**
	 * @return the left
	 */
	public TrainSide getLeft() {
		return left;
	}
	
	/**
	 * @return the right
	 */
	public TrainSide getRight() {
		return right;
	}
	
	public void drive(double leftSpeed, double rightSpeed){
		left.drive(leftSpeed);
		right.drive(rightSpeed);
		SmartDashboard.putNumber("Left Input", leftSpeed);
		SmartDashboard.putNumber("Right Input", rightSpeed);
	}
	
	public void toSmartDashboard(){
		left.toSmartDashboard("Left");
		right.toSmartDashboard("Right");
	}
	
	public class TrainSide {
		
		protected Encoder encoder;
		protected CANTalon front;
		protected CANTalon back;
		private boolean frontReversed;
		private boolean backReversed;
		private boolean encoderReversed;
		private double coefficient;
		
		public TrainSide(int frontChannel, int backChannel, int encoderA,
				int encoderB) {
			this(frontChannel, backChannel, encoderA, encoderB, false, false, false);
		}
		
		/**
		 * @param string
		 */
		public void toSmartDashboard(String name) {
			SmartDashboard.putNumber(name + " Speed", getRate());
			SmartDashboard.putNumber(name + " Distance", getDistance());
			SmartDashboard.putNumber(name + " Front Current", front.getOutputCurrent());
			SmartDashboard.putNumber(name + " Back Current", back.getOutputCurrent());
		}

		public TrainSide(int frontChannel, int backChannel, int encoderA,
				int encoderB, boolean frontReversed, boolean backReversed, boolean encoderReversed) {
			encoder = new Encoder(encoderA, encoderB);
			encoder.setReverseDirection(encoderReversed);
			encoder.setDistancePerPulse(0.110087234303968548662280932146080546444);
			initializeTalons(frontChannel, backChannel);
			this.frontReversed = frontReversed;
			this.backReversed = backReversed;
			this.encoderReversed = encoderReversed;
			this.coefficient = Controls.coefficient;
		}
		
		protected void initializeTalons(int frontChannel, int backChannel) {
			front = new CANTalon(frontChannel);
			back = new CANTalon(backChannel);
		}
		
		public void drive(double speed) {
			rawDrive(speedAdjustments(speed));
		}
		
		public double speedAdjustments(double speed) {
			speed = Math.max(Math.min(speed, 1), -1);
			speed *= coefficient;
			return speed;
		}
		
		protected void rawDrive(double speed) {
			front.set(frontReversed ? -speed : speed);
			back.set(backReversed ? -speed : speed);
		}
		
		/**
		 * 
		 * @see edu.wpi.first.wpilibj.Encoder#reset()
		 */
		public void reset() {
			encoder.reset();
		}
		
		/**
		 * @return
		 * @see edu.wpi.first.wpilibj.Encoder#getDistance()
		 */
		public double getDistance() {
			return encoder.getDistance();
		}
		
		/**
		 * @return
		 * @see edu.wpi.first.wpilibj.Encoder#getRate()
		 */
		public double getRate() {
			return encoder.getRate();
		}
		
		/**
		 * @return the frontReversed
		 */
		public boolean isFrontReversed() {
			return frontReversed;
		}
		
		/**
		 * @return the backReversed
		 */
		public boolean isBackReversed() {
			return backReversed;
		}
		
		/**
		 * @return the coefficient
		 */
		public double getCoefficient() {
			return coefficient;
		}
		
		/**
		 * @param frontReversed
		 *            the frontReversed to set
		 */
		public void setFrontReversed(boolean frontReversed) {
			this.frontReversed = frontReversed;
		}
		
		/**
		 * @param backReversed
		 *            the backReversed to set
		 */
		public void setBackReversed(boolean backReversed) {
			this.backReversed = backReversed;
		}
		
		/**
		 * @return the encoderReversed
		 */
		public boolean isEncoderReversed() {
			return encoderReversed;
		}
		
		/**
		 * @param encoderReversed the encoderReversed to set
		 */
		public void setEncoderReversed(boolean encoderReversed) {
			this.encoderReversed = encoderReversed;
			encoder.setReverseDirection(encoderReversed);
		}
		
		/**
		 * @param coefficient
		 *            the coefficient to set
		 */
		public void setCoefficient(double coefficient) {
			this.coefficient = coefficient;
		}
		
		public double getAverageOutputCurrent(){
			return (front.getOutputCurrent() + back.getOutputCurrent())/2;
		}
	}
}