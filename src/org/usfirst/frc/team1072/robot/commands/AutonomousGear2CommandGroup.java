package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author Ashwin Reddy
 */
public class AutonomousGear2CommandGroup extends CommandGroup {
	
	public static double gearTwoDistance = 60; // inches
	public static double kp = PID.MoveDist.P, ki = PID.MoveDist.I, kd = PID.MoveDist.D;
	
    public AutonomousGear2CommandGroup() {
    	addSequential(new MoveDistanceCommand(gearTwoDistance));
    	addSequential(new AutonPusherCommand());
    	addSequential(new MoveDistanceCommand(-24));
    	System.out.println("Constructed 2");
    }

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.CommandGroup#initialize()
	 */
	@Override
	protected void initialize() {
		super.initialize();
		System.out.println("Initialized 2");
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.CommandGroup#execute()
	 */
	@Override
	protected void execute() {
		super.execute();
		System.out.println("Executed 2");
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.CommandGroup#end()
	 */
	@Override
	protected void end() {
		super.end();
		System.out.println("Ended 2");
	}
	
	
}
