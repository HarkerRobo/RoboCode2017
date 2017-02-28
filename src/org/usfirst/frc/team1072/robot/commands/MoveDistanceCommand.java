package org.usfirst.frc.team1072.robot.commands;
import org.usfirst.frc.team1072.robot.*;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author cravuri
 */
public class MoveDistanceCommand extends Command {
	private double distance;
	//double kp = PID.MoveDist.P, ki = PID.MoveDist.I, kd = PID.MoveDist.D;
	//double sum = 0;
	//double prevError;
	//double currentError;
	//double errMargin = 2;
	
    public MoveDistanceCommand(double distance){
    	this.distance = distance;
    	//prevError = distance;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.getLeft().reset();
    	Robot.drivetrain.getRight().reset();
    	//Robot.drivetrain.drive(0.5, 0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double dif = distance - (Robot.drivetrain.getRight().getDistance() + Robot.drivetrain.getRight().getDistance())/2;
    	dif /= 30;
    	if(Math.abs(dif) > 0.4){
    		dif = Math.signum(distance) * 0.4;
    	} else if(Math.abs(dif) < 0.2){
    		dif = Math.signum(distance) * 0.2;
    	}
    	Robot.drivetrain.drive(dif, dif);
    	/*sum += prevError;
		currentError = distance - (Robot.drivetrain.getRight().getDistance() + Robot.drivetrain.getRight().getDistance())/2;
		Robot.drivetrain.drive(kp*currentError + ki*sum + kd*(currentError - prevError),
				kp*currentError + ki*sum + kd*(currentError - prevError));
		prevError = currentError;*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(Robot.drivetrain.getRight().getDistance() + Robot.drivetrain.getRight().getDistance())/2 >= Math.abs(distance);
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
