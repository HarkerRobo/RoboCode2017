package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearTwoCommandGroup extends CommandGroup {
	
	double gearTwoDistance = 187.8;
	double gearTwoAngle = 120.0;
	
	public GearTwoCommandGroup() {
		double startAngle = Robot.gyro.getAngle();
		addSequential(new MoveDistanceCommand(gearTwoDistance));
		addSequential(new AngleTurnCommand(gearTwoAngle - Robot.gyro.getAngle() + startAngle));
	}

}
