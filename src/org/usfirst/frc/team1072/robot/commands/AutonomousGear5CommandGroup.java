package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousGear5CommandGroup extends CommandGroup {
	
	double gearFiveDistance = 60;
	double perpGearDistance = 43.167;
	
	public AutonomousGear5CommandGroup() {
		addSequential(new DriveDistanceTimed(gearFiveDistance));
		addSequential(new AngleTurnCommand(-54.739));
		addSequential(new DriveDistanceTimed(perpGearDistance));
		addSequential(new AutonPusherCommand());
		addSequential(new DriveDistanceTimed(-24));
	}

}
