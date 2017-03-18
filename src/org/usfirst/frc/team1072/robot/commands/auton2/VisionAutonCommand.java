package org.usfirst.frc.team1072.robot.commands.auton2;

import org.usfirst.frc.team1072.robot.commands.AngleTurnCommand;
import org.usfirst.frc.team1072.robot.commands.AutonPusherCommand;
import org.usfirst.frc.team1072.robot.commands.DriveDistanceTimed;
import org.usfirst.frc.team1072.robot.commands.PusherEnd;
import org.usfirst.frc.team1072.robot.commands.PusherStart;
import org.usfirst.frc.team1072.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionAutonCommand extends CommandGroup {
	
	public static final VisionAutonCommand RED_LEFT = new VisionAutonCommand(74.946, 60, 55.45);
	public static final VisionAutonCommand RED_CENTER = new VisionAutonCommand(53.136, 0, 0);
	public static final VisionAutonCommand RED_RIGHT = new VisionAutonCommand(88.34, -60, 28.718);
	public static final VisionAutonCommand BLUE_LEFT = new VisionAutonCommand(88.34, 60, 28.718);
	public static final VisionAutonCommand BLUE_CENTER = new VisionAutonCommand(53.136, 0, 0);
	public static final VisionAutonCommand BLUE_RIGHT = new VisionAutonCommand(74.946, -60, 55.45);

    public VisionAutonCommand(double initialDistance, double initialTurn, double secondDistance) {
        addSequential(new GyroDriveCommand(initialDistance));
        addSequential(new AngleTurnCommand(initialTurn));
        addSequential(new DriveWithVision(secondDistance, 0));
        addSequential(new WaitCommand(0.3));
        addSequential(new PusherStart());
        addSequential(new WaitCommand(0.3));
        addSequential(new DriveDistanceTimed(-24));
        addSequential(new PusherEnd());
    }
}
