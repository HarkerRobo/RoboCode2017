package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Controls;
import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.XboxWrapper;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BumperWinchCommand extends Command {
	
	private static final double LEFT_SPEED = .3;
	private static final double RIGHT_SPEED = .7;

    public BumperWinchCommand() {
        requires(Robot.winch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = (OI.gp.getButtonBumperLeftState() ? LEFT_SPEED : 0) + (OI.gp.getButtonBumperRightState() ? RIGHT_SPEED : 0);
    	Robot.winch.setSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
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
