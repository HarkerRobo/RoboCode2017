package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousGear4CommandGroup extends CommandGroup {

	public static double robotHeight = 7.125 ; // half robot height
	public static double robotWidth = 15; // half robot width
	public static double hexToField = 162; // center of hexagon to edge of field
	public static double boilerDiag = 36.75; // half of the boiler diagonal
	public static double widthOfField  = Math.sqrt(3)*101 + 114.3;
	public static double triangleHeight = hexToField - boilerDiag - robotWidth; // long part of triangle
	public static double triangleWidth = Math.sqrt(3)*triangleHeight; // short part of triangle
	public static double initDistance = widthOfField - robotHeight - triangleWidth; // initial drive distance
	public static double perpGearDistance = (101/6.0);
	public static double normalCurrent = 20; //normal value for talon SRX current
	public static double spikeCurrent = 60; //min value for current spike
	public static double velocityToGetCurrentSpike = 40;

	private int tapeWidthLeft, tapeWidthRight; 

	public AutonomousGear4CommandGroup() {
		addSequential(new MoveDistanceCommand(initDistance));
		addSequential(new AngleTurnCommand(-60));
		addSequential(new MoveDistanceCommand(perpGearDistance));
		addSequential(new PusherCommand());
		addSequential(new MoveDistanceCommand(-24));
		/*while (!currentSpike()) {
			Robot.drivetrain.drive(velocityToGetCurrentSpike, velocityToGetCurrentSpike);
		}
		Robot.drivetrain.drive(0, 0);*/
		//Release gear here, drive back
	}

	public boolean currentSpike() {
		if (Robot.drivetrain.getLeft().getAverageOutputCurrent() >= spikeCurrent) {
			return true;
		}
		return false;
	}

}
