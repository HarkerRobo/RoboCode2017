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
    	double mag = Math.sqrt(x * x + y * y);
    	if(mag > 1)
    	{
    		x /= mag;
    		y /= mag;
    	}
    	Robot.drivetrain.tankDrive((y * Math.abs(y) + x * Math.abs(x)), (y * Math.abs(y) - x * Math.abs(x)));
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