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
public class ManualDriveCommand extends Command {

    private static final double THRESHOLD = 0.05;

	public ManualDriveCommand() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(Controls.DRIVE_CONTROL.get()){
    		case TANK:
    			tankDrive();
    			break;
    		case ARCADE:
    			arcadeDrive();
    			break;
    		default:
    			System.err.println("No drive control");
    			break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private void tankDrive(){
    	double sLeft = OI.gp.getLeftY();
    	double sRight = OI.gp.getRightY();
		sLeft = (Math.abs(sLeft) < THRESHOLD) ? 0 : sLeft;
		sRight = (Math.abs(sRight) < THRESHOLD) ? 0 : sRight;
		sLeft *= Math.abs(sLeft);
		sRight *= Math.abs(sRight);
		Robot.drivetrain.drive(sLeft, sRight);
    }
    
    private void arcadeDrive(){
		double x = OI.gp.getLeftX();
		double y = OI.gp.getLeftY();
		if(Math.abs(x) < THRESHOLD && Math.abs(y) < THRESHOLD){
			Robot.drivetrain.drive(0, 0);
		} else {
			double k = Math.max(1.0, Math.max(Math.abs(y+x), Math.abs(y-x)));
			double left = (y - x * Math.abs(x))/k;
			double right = (y + x * Math.abs(x))/k;
			Robot.drivetrain.drive(left, right);
		}
    }
}
