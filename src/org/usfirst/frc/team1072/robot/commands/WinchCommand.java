package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class WinchCommand extends Command{
	private boolean prev;
	private double prevSpeed;
	
	public WinchCommand(){
		requires(Robot.winch);
	}
	
	 // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.winch.setSpeed(0);
    	prev = false;
    	prevSpeed = 0;
    }

	protected void execute(){
		boolean current = OI.controller.getAButton();
		if(!prev && current){
			prevSpeed = 1 - prevSpeed;
			Robot.winch.setSpeed(prevSpeed);
		}
		prev = current;
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
    protected void end() {
    	Robot.winch.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
