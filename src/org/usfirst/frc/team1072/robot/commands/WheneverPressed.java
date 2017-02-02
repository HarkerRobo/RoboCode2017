package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.XboxWrapper;
import org.usfirst.frc.team1072.robot.XboxWrapper.Button;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WheneverPressed extends Command {

	private Button b;
	private Command c;
	private boolean prev;
	
    public WheneverPressed(Button b, Command c) {
    	this.b = b;
    	this.c = c;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean current = XboxWrapper.getInstance().getButton(b);
    	if(current && !prev){
    		if(!c.isRunning()){
    			c.start();
    		}
    	}
    	prev = current;
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