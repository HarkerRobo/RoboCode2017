package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.commands.TankDriveCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PIDDrivetrain extends Subsystem {

	private ADXRS450_Gyro gyro;
	private BuiltInAccelerometer accel;
	private PIDSide left;
	private PIDSide right;

    public void initDefaultCommand() {
        setDefaultCommand(new TankDriveCommand());
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed){
    	left.setSpeed(leftSpeed);
    	right.setSpeed(rightSpeed);
    }
    
    public void enable(){
    	gyro.calibrate();
    	left.enable();
    	right.enable();
    }

	public void reset() {
		gyro.reset();
	}

	public double getAngle() {
		return gyro.getAngle();
	}

	public double getX() {
		return accel.getX();
	}

	public double getY() {
		return accel.getY();
	}

	public double getZ() {
		return accel.getZ();
	}
    
}

