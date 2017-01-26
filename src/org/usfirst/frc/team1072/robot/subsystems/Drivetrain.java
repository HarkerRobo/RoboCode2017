package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Drivetrain extends Subsystem {
	
	private Gyro gyro;
	private BuiltInAccelerometer accel;
	private PIDWheel frontLeft;
	private PIDWheel frontRight;
	private PIDWheel backLeft;
	private PIDWheel backRight;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Drivetrain(){
    	gyro = new AnalogGyro(RobotMap.Robot.GYRO);
    	accel = new BuiltInAccelerometer();
    	frontLeft = new PIDWheel(new Encoder(Drive.FLA, Drive.FLB), Drive.FL);
    	frontRight = new PIDWheel(new Encoder(Drive.FRA, Drive.FRB), RobotMap.Robot.Drive.FR);
    	backLeft = new PIDWheel(new Encoder(Drive.BLA, Drive.BLB), RobotMap.Robot.Drive.BL);
    	backRight = new PIDWheel(new Encoder(Drive.BRA, Drive.BRB), RobotMap.Robot.Drive.BR);
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
    
	public PIDWheel getFrontLeft() {
		return frontLeft;
	}
	
	public PIDWheel getFrontRight() {
		return frontRight;
	}
	
	public PIDWheel getBackLeft() {
		return backLeft;
	}
	
	public PIDWheel getBackRight() {
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
	
}

