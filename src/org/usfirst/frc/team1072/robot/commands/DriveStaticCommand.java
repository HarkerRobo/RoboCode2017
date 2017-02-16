/**
 * 
 */
package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Controls;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.XboxWrapper;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author joelmanning
 *
 */
public class DriveStaticCommand extends Command {
	
	public DriveStaticCommand(){
		requires(Robot.drivetrain);
	}
	
	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Command#execute()
	 */
	@Override
	protected void execute() {
		double speed = XboxWrapper.getInstance().getButton(Controls.PID_TEST) ? 0.5 : 0;
		Robot.drivetrain.drive(speed, speed);
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Command#end()
	 */
	@Override
	protected void end() {
		Robot.drivetrain.drive(0, 0);
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Command#isFinished()
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
