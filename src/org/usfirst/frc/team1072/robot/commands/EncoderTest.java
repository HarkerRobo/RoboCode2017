package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EncoderTest extends Command {

    public EncoderTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//SmartDashboard.putNumber("FR Encoder Raw", Robot.drivetrain.getFrontRight().get());
    	//SmartDashboard.putNumber("BR Encoder Raw", Robot.drivetrain.getBackRight().get());
    	//SmartDashboard.putNumber("FL Encoder Raw", Robot.drivetrain.getFrontLeft().get());
    	//SmartDashboard.putNumber("BL Encoder Raw", Robot.drivetrain.getBackLeft().get());
    	SmartDashboard.putNumber("FR Encoder Rate", Robot.drivetrain.getFrontRight().getRate());
    	SmartDashboard.putNumber("BR Encoder Rate", Robot.drivetrain.getBackRight().getRate());
    	SmartDashboard.putNumber("FL Encoder Rate", Robot.drivetrain.getFrontLeft().getRate());
    	SmartDashboard.putNumber("BL Encoder Rate", Robot.drivetrain.getBackLeft().getRate());
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
