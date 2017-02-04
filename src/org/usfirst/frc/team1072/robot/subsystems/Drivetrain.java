package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Encoders;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Talons;
import org.usfirst.frc.team1072.robot.commands.ArcadeDriveCommand;
import org.usfirst.frc.team1072.robot.commands.TankDriveCommand;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {

	private Gyro gyro;
	private BuiltInAccelerometer accel;
	private Encoder leftEnc;
	private Encoder rightEnc;
	private Wheel frontLeft;
	private Wheel frontRight;
	private Wheel backLeft;
	private Wheel backRight;

	public Drivetrain(){
		gyro = new AnalogGyro(RobotMap.Robot.GYRO);
		accel = new BuiltInAccelerometer();
		leftEnc = new Encoder(Encoders.LA, Encoders.LB);
		rightEnc = new Encoder(Encoders.RA, Encoders.RB);
		frontLeft = new Wheel(Talons.FL, leftEnc, true/*, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D*/);
		frontRight = new Wheel(Talons.FR, rightEnc/*, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D*/);
		backLeft = new Wheel(Talons.BL, leftEnc, true/*, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D*/);
		backRight = new Wheel(Talons.BR, rightEnc/*, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D*/);
	}

	/**
	 * @param frontLeft the frontLeft to set
	 */
	public void setFrontLeft(PIDWheel frontLeft) {
		this.frontLeft = frontLeft;
	}

	/**
	 * @param frontRight the frontRight to set
	 */
	public void setFrontRight(PIDWheel frontRight) {
		this.frontRight = frontRight;
	}

	/**
	 * @param backLeft the backLeft to set
	 */
	public void setBackLeft(PIDWheel backLeft) {
		this.backLeft = backLeft;
	}

	/**
	 * @param backRight the backRight to set
	 */
	public void setBackRight(PIDWheel backRight) {
		this.backRight = backRight;
	}

	public void tankDrive(double rightSpeed, double leftSpeed) {
		frontLeft.setSpeed(leftSpeed);
		backLeft.setSpeed(leftSpeed);
		backRight.setSpeed(rightSpeed);
		frontRight.setSpeed(rightSpeed);
	}

	public Wheel getFrontLeft() {
		return frontLeft;
	}

	public Wheel getFrontRight() {
		return frontRight;
	}

	public Wheel getBackLeft() {
		return backLeft;
	}

	public Wheel getBackRight() {
		return backRight;
	}

	/**
	 * @return the gyro
	 */
	public Gyro getGyro() {
		return gyro;
	}

	/**
	 * @param gyro the gyro to set
	 */
	public void setGyro(Gyro gyro) {
		this.gyro = gyro;
	}

	public double rightSpeed(){
		return (frontRight.getRate() + backRight.getRate())/2;
	}

	public double leftSpeed(){
		return (frontLeft.getRate() + backLeft.getRate())/2;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveCommand());
	}

	public void toSmartDashboard() {
		frontLeft.toSmartDashboard("Front Left");
		frontRight.toSmartDashboard("Front Right");
		backLeft.toSmartDashboard("Back Left");
		backRight.toSmartDashboard("Back Right");
	}
}

