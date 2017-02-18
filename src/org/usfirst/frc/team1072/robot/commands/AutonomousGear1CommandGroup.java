package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author cravuri
 */
public class AutonomousGear1CommandGroup extends CommandGroup {
	
	double gearOneDistance = 60;
	double perpGearDistance = 43.167;
	
	public AutonomousGear1CommandGroup() {
		addSequential(new MoveDistanceCommand(gearOneDistance));
		addSequential(new AngleTurnCommand(-54.739));
		addSequential(new MoveDistanceCommand(perpGearDistance));
		addSequential(new AutonPusherCommand());
		addSequential(new MoveDistanceCommand(-24));
	}
	
}
