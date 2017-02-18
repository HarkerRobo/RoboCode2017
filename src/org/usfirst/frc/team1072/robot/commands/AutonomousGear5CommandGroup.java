package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousGear5CommandGroup extends CommandGroup {
	
	double gearFiveDistance = 60;
	double perpGearDistance = 43.167;
	
	public AutonomousGear5CommandGroup() {
		addSequential(new MoveDistanceCommand(gearFiveDistance));
		addSequential(new AngleTurnCommand(-54.739));
		addSequential(new MoveDistanceCommand(perpGearDistance));
		addSequential(new AutonPusherCommand());
		addSequential(new MoveDistanceCommand(-24));
	}

}
