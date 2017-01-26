package org.usfirst.frc.team1072.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import java.lang.Math;

import org.usfirst.frc.team1072.robot.RobotMap;

public class PIDWheel extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static double THRESHOLD = 0.1;
	private static final double maxa = 0.3;
	private Talon t; 
	private Encoder enc;
	private boolean reversed = false;

	public PIDWheel(Encoder e, int tport) {
		super(RobotMap.P, RobotMap.I, RobotMap.D);
		enc = e;
		t = new Talon(tport);
	}
	
	public double getSpeed() {
		return enc.getRate();
	}
	
	public void reset() {
		enc.reset();
	}
	
	public double getDistance() {
		return enc.getDistance();
	}
	
	public void setSpeed(double speed){
		//check if speed it out of bounds
		speed = Math.max(Math.min(speed,  1), -1);
		//check if speed is within threshold of 0
		if(Math.abs(speed) < THRESHOLD){
			speed = 0;
		}
		//square speed for better control while preserving sign
		speed = Math.signum(speed) * Math.pow(speed, 2);
		//check if reversed
		if(reversed){
			speed = -speed;
		}
		//set setpoint, I ignore acceleration since we are using PID
		setSetpoint(speed);
	}
	
	/*public void setSpeed(double speed) {
		if (Math.abs(this.getSpeed() - speed) > maxa) {	
			if (getSpeed() - speed > 0) {
				helperSpeed(maxa + getSpeed());
			} else if (getSpeed() - speed < 0) {
				helperSpeed(getSpeed() - maxa);
			}
		} else {
			this.helperSpeed(speed);
		}
	}
	
	public void helperSpeed (double speed) {
		if (speed <= 1.0 && speed >= -1.0) {
			if (reversed) {
				setSetpoint(-speed);
			} else {
				setSetpoint(speed);
			}
		} else if (speed <= 0.05 && speed >= -0.05) {
			setSetpoint(0);
		} else if (speed >= 1.0) {
			if (reversed) {
				setSetpoint(-1.0);
			} else {
				setSetpoint(1.0);
			}
		} else if (speed <= -1.0) {
			if (reversed) {
				setSetpoint(1.0);
			} else {
				setSetpoint(-1.0);
			}
		}
	}*/
	
	public void setReverse(boolean thing){
		reversed = thing;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		return getSpeed();
	}
	
	@Override
	protected void usePIDOutput(double output) {
		t.set(output);
	}
}