package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Controls;
import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.XboxWrapper;
import org.usfirst.frc.team1072.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveCommand extends Command {
	
	public TankDriveCommand() {
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double sLeft = XboxWrapper.getInstance().getAxis(Controls.TANK_LEFT);
    	double sRight = XboxWrapper.getInstance().getAxis(Controls.TANK_RIGHT);
		Robot.drivetrain.tankDrive(sRight, sLeft);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
