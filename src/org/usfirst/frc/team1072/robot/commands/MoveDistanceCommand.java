package org.usfirst.frc.team1072.robot.commands;
import org.usfirst.frc.team1072.robot.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveDistanceCommand extends Command {
	private double distance;
	private double speed;
	
    public MoveDistanceCommand(double dist) {
    	this(dist, 1.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }
    
    public MoveDistanceCommand(double distance, double speed){
    	this.distance = distance;
    	this.speed = speed;
    	requires(Robot.drivetrain);
    	Robot.drivetrain.getBackRight().reset();
    	Robot.drivetrain.getBackLeft().reset();
    	Robot.drivetrain.getFrontRight().reset();
    	Robot.drivetrain.getFrontLeft().reset();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.tankDrive(speed, speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.drivetrain.getBackRight().getDistance() >= distance;
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
