package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutonPistonCommand extends Command {
	
	private double gearDistance = 10; // inches
	double errMargin = 0.1;

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if (noError((Robot.drivetrain.getLeft().getDistance() + Robot.drivetrain.getRight().getDistance())/2 - gearDistance)) {
			Robot.gearPiston.putIn();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean noError(double err) {
    	if (err <= errMargin && err >= -errMargin) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
