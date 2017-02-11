package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PusherCommand extends Command {
	
	public PusherCommand() {
		requires(Robot.push);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		if(Robot.push.getPush().get().equals(Value.kReverse) && Robot.push.getClose().get().equals(Value.kForward)){
			Robot.push.getClose().set(Value.kReverse);
			setTimeout(0.5);
		} else {
			setTimeout(0);
		}
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
		if(Robot.push.getPush().get().equals(Value.kForward)){
			Robot.push.getPush().set(Value.kReverse);
		} else {
			if(Robot.push.getClose().get().equals(Value.kReverse)){
				Robot.push.getPush().set(Value.kForward);
			} else {
				System.err.println("Closer closed by outside source during forward push command.");
			}
		}
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
	}
	
	
}
