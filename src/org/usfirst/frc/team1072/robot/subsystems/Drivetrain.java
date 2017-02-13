package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.RobotMap.PID;
import org.usfirst.frc.team1072.robot.RobotMap.PID.Wheels;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Encoders;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Talons;
import org.usfirst.frc.team1072.robot.XboxWrapper;
import org.usfirst.frc.team1072.robot.XboxWrapper.Button;
import org.usfirst.frc.team1072.robot.commands.ArcadeDriveCommand;
import org.usfirst.frc.team1072.robot.commands.DriveCommand;
import org.usfirst.frc.team1072.robot.commands.TankDriveCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {

	private ADXRS450_Gyro gyro;
	private BuiltInAccelerometer accel;
	private Encoder leftEnc;
	private Encoder rightEnc;
	private Wheel frontLeft;
	private Wheel frontRight;
	private Wheel backLeft;
	private Wheel backRight;
	private boolean slow;
    
    public Drivetrain(){
    	gyro = new ADXRS450_Gyro();
    	accel = new BuiltInAccelerometer();
    	leftEnc = new Encoder(Encoders.LA, Encoders.LB);
    	rightEnc = new Encoder(Encoders.RA, Encoders.RB);
    	leftEnc.setPIDSourceType(PIDSourceType.kRate);
    	rightEnc.setPIDSourceType(PIDSourceType.kRate);
    	//leftEnc.setDistancePerPulse(6 * Math.PI);
    	//rightEnc.setDistancePerPulse(6 * Math.PI);
    	frontLeft = new PIDWheel(Talons.FL, leftEnc, true, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D);
    	frontRight = new PIDWheel(Talons.FR, rightEnc, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D);
    	backLeft = new PIDWheel(Talons.BL, leftEnc, true, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D);
    	backRight = new PIDWheel(Talons.BR, rightEnc, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D);
    	frontLeft.enable();
    	frontRight.enable();
    	backLeft.enable();
    	backRight.enable();
    	slow = true;
    	
    	gyro.calibrate();
    }

	public void tankDrive(double rightSpeed, double leftSpeed) {
		if(slow){
			leftSpeed *= 0.5;
			rightSpeed *= 0.5;
		}
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
	
	public void setRobotSpeed(double vel) {
		if(slow){
			vel *= 0.5;
		}
		this.getFrontLeft().setSpeed(vel);
		this.getFrontRight().setSpeed(vel);
		this.getBackLeft().setSpeed(vel);
		this.getBackRight().setSpeed(vel);
	}

	/**
	 * @return the gyro
	 */
	public Gyro getGyro() {
		return gyro;
	}

	public double rightSpeed(){
		return (frontRight.getRate() + backRight.getRate())/2;
	}

	public double leftSpeed(){
		return (frontLeft.getRate() + backLeft.getRate())/2;
	}
	
    public void initDefaultCommand() {
    	switch(Robot.driveControl){
			case ARCADE:
				setDefaultCommand(new ArcadeDriveCommand());
				break;
			case TANK:
				setDefaultCommand(new TankDriveCommand());
				break;
			case PIDTEST:
				XboxWrapper.getInstance().toggleWhenPressed(Button.RSTICK, new DriveCommand(0.1, 0.1));
				break;
			default:
				System.err.println("No drive control");
				break;
    	}
    }
    
    public void toSmartDashboard() {
    	frontLeft.toSmartDashboard("Front Left");
    	frontRight.toSmartDashboard("Front Right");
    	backLeft.toSmartDashboard("Back Left");
    	backRight.toSmartDashboard("Back Right");
    	SmartDashboard.putNumber("Acceleration in X",accel.getX());
    	SmartDashboard.putNumber("Acceleration in Y",accel.getY());
    	SmartDashboard.putNumber("Acceleration in Z",accel.getZ());
    	SmartDashboard.putNumber("Left Speed", frontLeft.getRate());
    	SmartDashboard.putNumber("Right Speed", backRight.getRate());
    	SmartDashboard.putBoolean("Slow Mode", slow);
    }

	public boolean isSlow() {
		return slow;
	}

	public void setSlow(boolean slow) {
		this.slow = slow;
	}
    
}

