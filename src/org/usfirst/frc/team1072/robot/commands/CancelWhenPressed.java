package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.XboxWrapper;
import org.usfirst.frc.team1072.robot.XboxWrapper.Button;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CancelWhenPressed extends Command {

	private Button b;
	private Command c;
	private boolean finished;
	
    public CancelWhenPressed(Button b, Command c) {
        this.b = b;
        this.c = c;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(XboxWrapper.getInstance().getButton(b)){
    		if(!c.isCanceled()){
    			c.cancel();
    		}
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
