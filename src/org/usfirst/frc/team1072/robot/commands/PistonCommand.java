package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PistonCommand extends Command {
	
	private boolean penetration = false;
	
	/**
	 * @return the penetration
	 */
	public boolean isPenetrated() {
		return penetration;
	}

	/**
	 * @param penetration the penetration to set
	 */
	public void setPenetration(boolean penetration) {
		this.penetration = penetration;
	}

	public PistonCommand() {
		requires(Robot.gearPiston);
	}

	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (OI.controller.getXButton()) {
			if (penetration) {
				Robot.gearPiston.pullOut();
			} else {
				Robot.gearPiston.putIn();
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}
