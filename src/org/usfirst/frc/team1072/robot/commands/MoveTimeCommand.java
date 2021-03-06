package org.usfirst.frc.team1072.robot.commands;
import org.usfirst.frc.team1072.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class MoveTimeCommand extends TimedCommand {
	private double speed;
    public MoveTimeCommand(double ti) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		this(ti, 1);
    		requires(Robot.drivetrain);
    }
    
    public MoveTimeCommand(double ti, double sp){
    		super(ti);
    		speed = sp;
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
        return false;
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
