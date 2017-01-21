package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousCommandGearOne extends Command {

	double gearOneDistance = 112; // inches
	double kp = RobotMap.P, ki = RobotMap.I, kd = RobotMap.D;
	
	public AutonomousCommandGearOne(int dist) {
		gearOneDistance = dist;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.encoder.reset();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double sum = 0;
		double prevError = gearOneDistance;
		double currentError;
		while (Robot.encoder.getDistance() < gearOneDistance) {
			sum += prevError;
			currentError = gearOneDistance - Robot.encoder.getDistance();
			Robot.drivetrain.tankDrive(kp*currentError + ki*sum + kd*(currentError - prevError),
					kp*currentError + ki*sum + kd*(currentError - prevError));
			prevError = currentError;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.encoder.getDistance() >= gearOneDistance;
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
