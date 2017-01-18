package org.usfirst.frc.team1072.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	private PIDWheel frontLeft = new PIDWheel(0,0,0);
	private PIDWheel frontRight = new PIDWheel(1,1,1);
	private PIDWheel backLeft = new PIDWheel(2,2,2);
	private PIDWheel backRight = new PIDWheel(3,3,3);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
}

