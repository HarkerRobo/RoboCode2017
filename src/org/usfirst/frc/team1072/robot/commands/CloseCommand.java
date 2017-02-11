package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CloseCommand extends InstantCommand {

    public CloseCommand() {
        requires(Robot.push);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.push.getClose().set(Robot.push.getClose().get().equals(Value.kForward) ? Value.kReverse : Value.kForward);
    	if(Robot.push.getClose().get().equals(Value.kForward)){
    		System.out.println("Should be forward");
    	}
    }

}
