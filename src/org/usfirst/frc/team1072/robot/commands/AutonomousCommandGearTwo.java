package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

/**
 *
 */
public class AutonomousCommandGearTwo extends Command {
	
	double gearTwoDistance = 187.8; // inches
	double kp = PID.MoveDist.P, ki = PID.MoveDist.I, kd = PID.MoveDist.D;
	double sum = 0;
	double prevError = gearTwoDistance;
	double currentError;
	
	public AutonomousCommandGearTwo() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
    	Robot.drivetrain.getBackRight().reset();
    	Robot.drivetrain.getBackLeft().reset();
    	Robot.drivetrain.getFrontRight().reset();
    	Robot.drivetrain.getFrontLeft().reset();
    	Robot.encoder.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		sum += prevError;
		currentError = gearTwoDistance - Robot.encoder.getDistance();
		Robot.drivetrain.tankDrive(kp*currentError + ki*sum + kd*(currentError - prevError),
				kp*currentError + ki*sum + kd*(currentError - prevError));
		prevError = currentError;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (Robot.encoder.getDistance() >= gearTwoDistance) {
			AngleTurnCommand ATC = new AngleTurnCommand(120);
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.tankDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
