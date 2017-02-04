package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class WinchCommand extends Command{
	private boolean isOn;
	
	public WinchCommand(){
		requires(Robot.winch);
	}
	
	 // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.winch.setSpeed(0);
    	isOn = false;
    }

	protected void execute(){
		if (OI.controller.getAButton()) {
			isOn = !isOn;
		}
		System.out.println("IsOn: " + isOn);
		
		Robot.winch.setSpeed((isOn) ? -1 : 0);
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
