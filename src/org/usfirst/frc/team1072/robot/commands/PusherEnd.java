package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class PusherEnd extends InstantCommand {

    public PusherEnd() {
        super();
        requires(Robot.push);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.push.getPush().set(Value.kReverse);
    	Robot.push.getClose().set(Value.kForward);
    }

}
