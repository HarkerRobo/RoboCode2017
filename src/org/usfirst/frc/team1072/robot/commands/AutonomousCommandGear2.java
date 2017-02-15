package org.usfirst.frc.team1072.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

/**
 *
 */
public class AutonomousCommandGear2 extends Command {
	
	double gearTwoDistance = 187.8; // inches
	double kp = PID.MoveDist.P, ki = PID.MoveDist.I, kd = PID.MoveDist.D;
	double sum = 0;
	double prevError = gearTwoDistance;
	double currentError;
	
	public AutonomousCommandGear2() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
    	Robot.drivetrain.getRight().reset();
    	Robot.drivetrain.getLeft().reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		sum += prevError;
		currentError = gearTwoDistance - (Robot.drivetrain.getLeft().getDistance() + Robot.drivetrain.getRight().getDistance())/2;
		Robot.drivetrain.drive(kp*currentError + ki*sum + kd*(currentError - prevError),
				kp*currentError + ki*sum + kd*(currentError - prevError));
		prevError = currentError;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if ((Robot.drivetrain.getLeft().getDistance() + Robot.drivetrain.getRight().getDistance())/2 >= gearTwoDistance) {
			AngleTurnCommand ATC = new AngleTurnCommand(120);
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
