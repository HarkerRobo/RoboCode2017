package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SlowModeCommand extends InstantCommand {

    public SlowModeCommand() {
        super();
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.drivetrain.setSlow(!Robot.drivetrain.isSlow());
    }

}
