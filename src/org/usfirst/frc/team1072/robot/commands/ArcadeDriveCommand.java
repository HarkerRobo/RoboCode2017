package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.XboxWrapper;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveCommand extends Command {
	
	public ArcadeDriveCommand() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x = XboxWrapper.getInstance().getX(Hand.kRight);
    	double y = XboxWrapper.getInstance().getY(Hand.kRight);
    	double k = Math.max(1.0, Math.max(Math.abs(y+x), Math.abs(y-x)));
    	Robot.drivetrain.tankDrive((y-x)/k, (y +x)/k);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

}