package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

	public static final AutonomousCommand RED_LEFT = new AutonomousCommand(77, 60, 55.5);
	public static final AutonomousCommand RED_CENTER = new AutonomousCommand(89, 0, 0);
	public static final AutonomousCommand RED_RIGHT = new AutonomousCommand(88.3, -60, 28.7);
	public static final AutonomousCommand BLUE_LEFT = new AutonomousCommand(88.3, 60, 28.7);
	public static final AutonomousCommand BLUE_CENTER = new AutonomousCommand(89, 0, 0);
	public static final AutonomousCommand BLUE_RIGHT = new AutonomousCommand(77, -60, 55.5);
	
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
