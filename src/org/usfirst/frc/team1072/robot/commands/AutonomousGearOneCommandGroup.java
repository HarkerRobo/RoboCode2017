package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author Ashwin Reddy
 */
public class AutonomousGearOneCommandGroup extends CommandGroup {
	
	public static double gearOneDistance = 112; // inches
	public static double kp = PID.MoveDist.P, ki = PID.MoveDist.I, kd = PID.MoveDist.D;
	
    public AutonomousGearOneCommandGroup() {
    	addSequential(new MoveDistanceCommand(gearOneDistance));
    }
}
