package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;

/**
 *
 */
public class AutonomousCommandGearTwo extends Command {
	double gearTwoDistance = 187.8; // inches
	double kp = RobotMap.P, ki = RobotMap.I, kd = RobotMap.D;
	public AutonomousCommandGearTwo() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.exampleSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double sum = 0;
		double prevError = gearTwoDistance;
		double currentError;
		while (Robot.encoder.getDistance() < gearTwoDistance) {
			sum += prevError;
			currentError = gearTwoDistance - Robot.encoder.getDistance();
			Robot.drivetrain.tankDrive(kp*currentError + ki*sum + kd*(currentError - prevError),
					kp*currentError + ki*sum + kd*(currentError - prevError));
			prevError = currentError;
		}
		AngleTurnCommand ATC = new AngleTurnCommand(120);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
