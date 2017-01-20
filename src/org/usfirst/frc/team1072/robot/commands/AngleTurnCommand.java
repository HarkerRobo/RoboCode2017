package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AngleTurnCommand extends Command {
	private double initialAngle;
	private double angleChange;
	private double speed;
	private boolean sign; // true is positive
	
    public AngleTurnCommand(double angle) {
        this(angle, 1);
    }
    
    public AngleTurnCommand(double angle, double sp){
    		angleChange = angle;
    		speed = sp;
    		initialAngle = Robot.drivetrain.getGyro().getAngle();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		if(angleChange < 0){
    			Robot.drivetrain.tankDrive(speed, -speed);
    			sign = false;
    		}else{
    			Robot.drivetrain.tankDrive(-speed, speed);
    			sign = true;
    		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(sign){
    		return Robot.drivetrain.getGyro().getAngle() >= initialAngle+angleChange;
    	}
    	return Robot.drivetrain.getGyro().getAngle() <= initialAngle + angleChange;
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
