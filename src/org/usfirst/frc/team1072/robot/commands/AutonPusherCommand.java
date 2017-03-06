package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonPusherCommand extends CommandGroup {
	
	public AutonPusherCommand() {
    	addSequential(new PusherStart());
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveDistanceTimed(-24));
    	addSequential(new PusherEnd());
	}
}
