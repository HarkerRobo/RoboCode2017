package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EncoderTest extends Command {

	
    public EncoderTest() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("FREncoder", Robot.drivetrain.getFrontRight().getRate());
    	SmartDashboard.putNumber("FLEncoder", Robot.drivetrain.getFrontLeft().getRate());
    	SmartDashboard.putNumber("BREncoder", Robot.drivetrain.getBackRight().getRate());
    	SmartDashboard.putNumber("BLEncoder", Robot.drivetrain.getBackLeft().getRate());
    }

    // Make this return true when this Command no longer needs to run execute()
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
