package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.RobotMap.PID;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Encoders;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Drive.Talons;
import org.usfirst.frc.team1072.robot.commands.TankDriveCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class OldPIDDrivetrain extends Subsystem {

	private ADXRS450_Gyro gyro;
	private BuiltInAccelerometer accel;
	private PIDSide left;
	private PIDSide right;
	
	public OldPIDDrivetrain(){
		gyro = new ADXRS450_Gyro();
		accel = new BuiltInAccelerometer();
		left = new PIDSide(Talons.FL, Talons.BL, Encoders.LA, Encoders.LB, false, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D);
		right = new PIDSide(Talons.FR, Talons.BR, Encoders.RA, Encoders.RB, true, PID.Wheels.P, PID.Wheels.I, PID.Wheels.D);
	}

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

