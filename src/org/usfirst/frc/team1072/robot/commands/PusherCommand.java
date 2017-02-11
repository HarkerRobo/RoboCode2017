package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PusherCommand extends Command {

	private static boolean prev = false;
	
    public PusherCommand() {
        requires(Robot.push);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.push.getClose().set(prev ? Value.kForward : Value.kReverse);
    	setTimeout(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.push.getPush().set(prev ? Value.kOff : Value.kForward);
    	prev = !prev;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    
}
