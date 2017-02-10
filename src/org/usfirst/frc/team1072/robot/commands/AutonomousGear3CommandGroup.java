package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGear3CommandGroup extends CommandGroup {
	public static double robotHeight = 7.125 ; // half robot height
	public static double robotWidth = 15; // half robot width
	public static double hexToField = 162; // center of hexagon to edge of field
	public static double boilerDiag = 36.75; // half of the boiler diagonal
	public static double widthOfField  = Math.sqrt(3)*101 + 114.3;
	public static double triangleHeight = hexToField - boilerDiag - robotWidth; // long part of triangle
	public static double triangleWidth = Math.sqrt(3)*triangleHeight; // short part of triangle
	public static double initDistance = widthOfField - robotHeight - triangleWidth; // initial drive distance
	public static double perpGearDistance = (101/6.0);
	private int tapeWidthLeft, tapeWidthRight; 
    public AutonomousGear3CommandGroup() {
    	addSequential(new MoveDistanceCommand(initDistance));
    	addSequential(new AngleTurnCommand(60));
    	addSequential(new MoveDistanceCommand(perpGearDistance));
    
    }
}
