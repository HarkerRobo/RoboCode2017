package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author cravuri
 */
public class AngleTurnCommand extends Command {
	
	private double initialAngle;
	private double angle;
	/*private double prevError;
	double kp = PID.TurnAngle.P, ki = PID.TurnAngle.I, kd = PID.TurnAngle.D;
	double sum = 0;
	double currentError;*/
	double errMargin = 3;
	
	public AngleTurnCommand(double angle){
		requires(Robot.drivetrain);
    	this.angle = (Robot.gyro.getAngle() + angle) % 360;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double dif = (angle - Robot.gyro.getAngle()) % 360;
    	if(dif > 180){
    		dif -= 360;
    	}
    	double div = dif/90;
    	if(Math.abs(div) > 0.5){
    		div = Math.signum(div) * 0.5;
    	} else if(Math.abs(div) < 0.2){
    		div = Math.signum(div) * 0.2;
    	}
    	double leftSpeed = div;
    	double rightSpeed = -div;
    	Robot.drivetrain.drive(leftSpeed, rightSpeed);
    	System.out.println("Dif " + dif + " gave speeds " + leftSpeed + ", " + rightSpeed);
    	/*sum += prevError;
		currentError = (angle - (Robot.gyro.getAngle() % 360 - initialAngle)) % 360;
		Robot.drivetrain.drive(kp*currentError + ki*sum + kd*(currentError - prevError),
				-(kp*currentError + ki*sum + kd*(currentError - prevError)));
		prevError = currentError;*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs((angle - Robot.gyro.getAngle())%360) < errMargin;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    /*private boolean noError(double err) {
    	if (err <= errMargin && err >= -errMargin) {
    		return true;
    	} else {
    		return false;
    	}
    }*/
}
