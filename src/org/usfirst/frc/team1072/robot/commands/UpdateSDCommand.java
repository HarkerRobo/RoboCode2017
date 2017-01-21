package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateSDCommand extends Command {
	private SmartDashboard sd;
	private Encoder talon1;
	private Encoder talon2;
	private Encoder talon3;
	private Encoder talon4;
    public UpdateSDCommand(Encoder talon1,Encoder talon2,Encoder talon3,Encoder talon4) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	sd = new SmartDashboard();
    	this.talon1=talon1;
    	this.talon2=talon2;
    	this.talon3=talon3;
    	this.talon4=talon4;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	sd.putNumber("talon1speed", talon1.getRate());
    	sd.putNumber("talon2speed", talon2.getRate());
    	sd.putNumber("talon3speed", talon3.getRate());
    	sd.putNumber("talon4speed", talon4.getRate());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	 sd.putString("talon1speed", "no Longer Updating");
    	 sd.putString("talon2speed", "no Longer Updating");
    	 sd.putString("talon3speed", "no Longer Updating");
    	 sd.putString("talon4speed", "no Longer Updating");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
