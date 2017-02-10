package org.usfirst.frc.team1072.robot.commands;
import org.usfirst.frc.team1072.robot.*;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveDistanceCommand extends Command {
	private double distance;
	double kp = PID.MoveDist.P, ki = PID.MoveDist.I, kd = PID.MoveDist.D;
	double sum = 0;
	double prevError;
	double currentError;
	double errMargin = 2;
	
    public MoveDistanceCommand(double distance){
    	this.distance = distance;
    	prevError = distance;
    	requires(Robot.drivetrain);
    	Robot.drivetrain.getBackRight().reset();
    	Robot.drivetrain.getBackLeft().reset();
    	Robot.drivetrain.getFrontRight().reset();
    	Robot.drivetrain.getFrontLeft().reset();
    	Robot.encoder.reset();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	sum += prevError;
		currentError = distance - Robot.encoder.getDistance();
		Robot.drivetrain.tankDrive(kp*currentError + ki*sum + kd*(currentError - prevError),
				kp*currentError + ki*sum + kd*(currentError - prevError));
		prevError = currentError;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return noError(Robot.encoder.getDistance() - distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private boolean noError(double err) {
    	if (err <= errMargin && err >= -errMargin) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
