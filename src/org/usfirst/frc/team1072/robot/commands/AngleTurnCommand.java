package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AngleTurnCommand extends Command {
	
	private double initialAngle;
	private double angle;
	private double prevError;
	double kp = PID.TurnAngle.P, ki = PID.TurnAngle.I, kd = PID.TurnAngle.D;
	double sum = 0;
	double currentError;
	
	public AngleTurnCommand(double angle){
    		this.angle = angle;
    		prevError = angle;
    		initialAngle = Robot.drivetrain.getGyro().getAngle();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	sum += prevError;
		currentError = angle - (Robot.drivetrain.getGyro().getAngle() - initialAngle);
		Robot.drivetrain.tankDrive(kp*currentError + ki*sum + kd*(currentError - prevError),
				-(kp*currentError + ki*sum + kd*(currentError - prevError)));
		prevError = currentError;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.drivetrain.getGyro().getAngle() - initialAngle) >= angle;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
