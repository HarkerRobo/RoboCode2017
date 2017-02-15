package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.Controls;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Encoders;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Talons;
import org.usfirst.frc.team1072.robot.commands.ArcadeDriveCommand;
import org.usfirst.frc.team1072.robot.commands.TankDriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	public enum DriveControl {
		TANK(){

			@Override
			public void initialize(Drivetrain train) {
				train.setDefaultCommand(new TankDriveCommand());
			}
			
		}, ARCADE(){

			@Override
			public void initialize(Drivetrain train) {
				train.setDefaultCommand(new ArcadeDriveCommand());
			}
			
		};
		
		public abstract void initialize(Drivetrain train);
	}
	
	protected TrainSide left;
	protected TrainSide right;
	
	public Drivetrain() {
		initializeSides();
	}
	
	protected void initializeSides() {
		left = new TrainSide(Talons.FL, Talons.BL, Encoders.LA, Encoders.LB, true, true, false);
		right = new TrainSide(Talons.FR, Talons.BR, Encoders.RA, Encoders.RB, false, false, true);
	}
	
	public void initDefaultCommand() {
		Controls.DRIVE_CONTROL.initialize(this);
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
}

class TrainSide {
	
	private static final double THRESHOLD = 0.05;
	
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
	
	public TrainSide(int frontChannel, int backChannel, int encoderA,
			int encoderB, boolean frontReversed, boolean backReversed, boolean encoderReversed) {
		encoder = new Encoder(encoderA, encoderB);
		encoder.setReverseDirection(encoderReversed);
    	encoder.setDistancePerPulse(0.110087234303968548662280932146080546444);
		initializeTalons(frontChannel, backChannel);
		this.frontReversed = frontReversed;
		this.backReversed = backReversed;
		this.encoderReversed = encoderReversed;
	}
	
	protected void initializeTalons(int frontChannel, int backChannel) {
		front = new CANTalon(frontChannel);
		back = new CANTalon(backChannel);
	}
	
	public void drive(double speed) {
		rawDrive(speedAdjustments(speed));
	}
	
	public double speedAdjustments(double speed) {
		speed = (Math.abs(speed) < THRESHOLD) ? 0 : speed;
		speed = Math.max(Math.min(speed, 1), -1);
		speed *= Math.abs(speed);
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
}