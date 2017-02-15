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
    	Robot.drivetrain.getLeft().setCoefficient(0.5/Robot.drivetrain.getLeft().getCoefficient());
    	Robot.drivetrain.getRight().setCoefficient(0.5/Robot.drivetrain.getRight().getCoefficient());
    }

}
