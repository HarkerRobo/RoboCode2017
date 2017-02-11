package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CloserCommand extends InstantCommand {

    public CloserCommand() {
        requires(Robot.push);
    }

    // Called once when the command executes
    protected void initialize() {
    	if(Robot.push.getClose().get().equals(Value.kForward)){
    		Robot.push.getClose().set(Value.kReverse);
    	} else {
    		Robot.push.getClose().set(Value.kForward);
    	}
    }

}
