package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class WinchCommand extends Command{
	private boolean right = false;
	private boolean left = false;

	public WinchCommand(int port, Encoder encoder){
		requires(Robot.winch);
		

	}
	
	 // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.winch.setSpeed(0);
    }

	protected void execute(){
		if(right&&left){
			Robot.winch.setSpeed(0);
			right = false;
			left = false;
		}
		else if(left){
			Robot.winch.setSpeed(-1);
			left = true;
		}
		else if(right){
			Robot.winch.setSpeed(1);
			right = true;
		}
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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
