package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.commands.auton2.GyroDriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * I'm given an array of 3 values
 */
public class AutonomousCommand extends CommandGroup {

	public static final AutonomousCommand RED_LEFT = new AutonomousCommand(74.946, 60, 55.45);
	public static final AutonomousCommand RED_CENTER = new AutonomousCommand(110.77 - 36/*53.136*/, 0, 0);
	public static final AutonomousCommand RED_RIGHT = new AutonomousCommand(88.34, -60, 28.718);
	public static final AutonomousCommand BLUE_LEFT = new AutonomousCommand(88.34, 60, 28.718);
	public static final AutonomousCommand BLUE_CENTER = new AutonomousCommand(110.77 - 36/*53.136*/, 0, 0);
	public static final AutonomousCommand BLUE_RIGHT = new AutonomousCommand(74.946, -60, 55.45);
	
    private AutonomousCommand(double initialDistance, double turn, double secondDistance) {
        addSequential(new GyroDriveCommand(initialDistance));
        addSequential(new WaitCommand(0.5));
        addSequential(new AngleTurnCommand(turn));
        addSequential(new GyroDriveCommand(secondDistance));
        addSequential(new PusherStart());
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveDistanceTimed(-24));
        addSequential(new PusherEnd());
    }
}
