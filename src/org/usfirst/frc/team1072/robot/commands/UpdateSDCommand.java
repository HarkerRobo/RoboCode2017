package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateSDCommand extends Command {

	static SmartDashboard sd = new SmartDashboard();
    public UpdateSDCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putNumber("GyroAngle",Robot.drivetrain.getGyro().getAngle());
    	SmartDashboard.putNumber("GyroRate", Robot.drivetrain.getGyro().getRate());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("GyroAngle",Robot.drivetrain.getGyro().getAngle());
    	SmartDashboard.putNumber("GyroRate", Robot.drivetrain.getGyro().getRate());
    	
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
