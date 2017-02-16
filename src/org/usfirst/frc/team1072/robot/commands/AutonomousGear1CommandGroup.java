package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author cravuri
 */
public class AutonomousGear1CommandGroup extends CommandGroup {
	
	double gearOneDistance = 158.44;
	double perpGearDistance = 92.881;
	
	public AutonomousGear1CommandGroup() {
		addSequential(new MoveDistanceCommand(gearOneDistance));
		addSequential(new AngleTurnCommand(-60));
		addSequential(new MoveDistanceCommand(perpGearDistance));
		addSequential(new PusherCommand());
		addSequential(new MoveDistanceCommand(-24));
	}
	
}
