package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author Ashwin Reddy
 */
public class AutonomousGear2CommandGroup extends CommandGroup {
	
	public static double gearTwoDistance = 106; // inches
	public static double kp = PID.MoveDist.P, ki = PID.MoveDist.I, kd = PID.MoveDist.D;
	
    public AutonomousGear2CommandGroup() {
    	addSequential(new DriveDistanceTimed(gearTwoDistance));
    	addSequential(new PusherStart());
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveDistanceTimed(-24));
    	addSequential(new PusherEnd());
    }
}
