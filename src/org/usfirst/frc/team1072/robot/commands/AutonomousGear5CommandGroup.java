package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousGear5CommandGroup extends CommandGroup {
	
	double gearFiveDistance = 158.44;
	double perpGearDistance = 92.881;
	
	public AutonomousGear5CommandGroup() {
		addSequential(new MoveDistanceCommand(gearFiveDistance));
		addSequential(new AngleTurnCommand(-60));
		addSequential(new MoveDistanceCommand(perpGearDistance));
		addSequential(new PusherCommand());
		addSequential(new MoveDistanceCommand(24));
	}

}
