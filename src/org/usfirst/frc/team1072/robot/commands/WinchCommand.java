package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class WinchCommand extends Command{
	private boolean right = false;
	private boolean left = false;

	public WinchCommand(int port, Encoder encoder){
		requires(Robot.winch);
		Robot.winch.setSpeed(0);

	}

	protected void execute(){
		if(OI.controller.getTrigger(Hand.kRight)){
			if(right == false && left == false){
				Robot.winch.setSpeed(1); 
				right = true;
			}
			else if(left  == true){
				Robot.winch.setSpeed(0);
			}
		}
		if(OI.controller.getTrigger(Hand.kLeft)){
			if(left == false && right == false){
				Robot.winch.setSpeed(-1); 
				left = true;
			}
			else if(right  == true){
				Robot.winch.setSpeed(0);
			}
		}

	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}


}
