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
    		this(ti, 1);
    }
    
    public MoveTimeCommand(double ti, double sp){
    		super(ti);
    		requires(Robot.drivetrain);
    		speed = sp;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.drivetrain.drive(speed, speed);
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
