package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

	public static final AutonomousCommand RED_LEFT = new AutonomousCommand(74.946, 60, 55.45);
	public static final AutonomousCommand RED_CENTER = new AutonomousCommand(53.136, 0, 0);
	public static final AutonomousCommand RED_RIGHT = new AutonomousCommand(88.34, -60, 28.718);
	public static final AutonomousCommand BLUE_LEFT = new AutonomousCommand(88.34, 60, 28.718);
	public static final AutonomousCommand BLUE_CENTER = new AutonomousCommand(53.136, 0, 0);
	public static final AutonomousCommand BLUE_RIGHT = new AutonomousCommand(74.946, -60, 55.45);
	
    private AutonomousCommand(double initialDistance, double turn, double secondDistance) {
        addSequential(new DriveDistanceTimed(initialDistance));
        addSequential(new AngleTurnCommand(turn));
        addSequential(new DriveDistanceTimed(secondDistance));
        addSequential(new PusherStart());
        addSequential(new WaitCommand(1));
        addSequential(new DriveDistanceTimed(-24));
        addSequential(new PusherEnd());
    }
}
