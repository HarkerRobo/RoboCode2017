package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveCommand extends Command {
	
	private int flip;
	
	public ArcadeDriveCommand() {
		flip = 1;
		requires(Robot.drivetrain);
	}

	protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x = OI.controller.getX(Hand.kRight);
    	double y = OI.controller.getY(Hand.kRight);
    	Robot.drivetrain.tankDrive(flip*y*(1-x/2),flip*y*(1+x/2));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
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