package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.XboxWrapper;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveCommand extends Command {
	
	private static final double DEAD_ZONE = 0.08;
	private int flip;
	
	public ArcadeDriveCommand() {
		flip = -1;
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double x = XboxWrapper.getInstance().getX(Hand.kLeft);
		double y = -XboxWrapper.getInstance().getY(Hand.kLeft);
		if(Math.abs(x) < DEAD_ZONE && Math.abs(y) < DEAD_ZONE){
			Robot.drivetrain.drive(0, 0);
		} else {
			double k = Math.max(1.0, Math.max(Math.abs(y+x), Math.abs(y-x)));
			double left = (y - flip * x * Math.abs(x))/k;
			double right = (y + flip * x * Math.abs(x))/k;
			Robot.drivetrain.drive(left, right);
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
	
	public void setFlip(boolean b){
		if (b == true) {
			flip = -1;
		} else {
			flip = 1;
		}
	}
	
	public boolean getFlip(){
		return flip == -1;
	}
	
}