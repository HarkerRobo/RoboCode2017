package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomousCommandGearOne extends Command {

	double gearOneDistance = 112; // inches
	double kp = PID.MoveDist.P, ki = PID.MoveDist.I, kd = PID.MoveDist.D;
	double sum = 0;
	double prevError;
	double currentError;
	double rate;
	boolean direction;
	double distance;
	
	public AutonomousCommandGearOne(int dist) {
		gearOneDistance = dist;
		prevError = gearOneDistance;
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void initialize() {
		Robot.drivetrain.getBackRight().reset();
    	Robot.drivetrain.getBackLeft().reset();
    	Robot.drivetrain.getFrontRight().reset();
    	Robot.drivetrain.getFrontLeft().reset();
    	Robot.encoder.reset();
		Robot.encoder.reset();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		sum += prevError;
		currentError = gearOneDistance - Robot.encoder.getDistance();
		Robot.drivetrain.tankDrive(kp*currentError + ki*sum + kd*(currentError - prevError),
				kp*currentError + ki*sum + kd*(currentError - prevError));
		prevError = currentError;
		rate = Robot.encoder.getRate();
		direction = Robot.encoder.getDirection();
		distance = Robot.encoder.getDistance();
		
		System.out.println("Rate: " + rate + "Direction: " + direction + "Distance: " + distance);
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.encoder.getDistance() >= gearOneDistance;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.drivetrain.tankDrive(0, 0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
