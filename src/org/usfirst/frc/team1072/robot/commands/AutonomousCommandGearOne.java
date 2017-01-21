package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousCommandGearOne extends Command {

	double gearOneDist = 112; // inches
	double kp = RobotMap.P, ki = RobotMap.I, kd = RobotMap.D;
	
	public AutonomousCommandGearOne(int dist) {
		gearOneDist = dist;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double sum = 0;
		double prevError = gearOneDist;
		double curError;
		while (Robot.encoder.getDistance() < gearOneDist) {
			sum += prevError;
			curError = gearOneDist - Robot.encoder.getDistance();
			Robot.drivetrain.tankDrive(kp*curError + ki*sum + kd*(curError - prevError),
					kp*curError + ki*sum + kd*(curError - prevError));
			prevError = curError;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.encoder.getDistance() >= gearOneDist;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
