package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomousCommandGear3 extends Command{
	static double robotHeight = 7.125 ; // half robot height
	static double robotWidth = 15; // half robot width
	static double hexToField = 162; // center of hexagon to edge of field
	static double boilerDiag = 36.75; // half of the boiler diagonal
	static double widthOfField  = Math.sqrt(3)*101 + 114.3;
	static double triangleHeight = hexToField - boilerDiag - robotWidth; // long part of triangle
	static double triangleWidth = Math.sqrt(3)*triangleHeight; // short part of triangle
	static double initDistance = widthOfField - robotHeight - triangleWidth; // initial drive distance
	

	double initialTurnAngle = initAngle();
	double adjustAngle  = 0;
	double correctAngle;

	//PID stuff
	double kp = PID.MoveDist.P, ki = PID.MoveDist.I, kd = PID.MoveDist.D;
	double sum = 0;
	double prevError;
	double currentError;

	double rate;
	boolean direction;
	double distance;

	protected void initialize(){
		Robot.drivetrain.getRight().reset();
		Robot.drivetrain.getLeft().reset();

		moveFirstStretch();
		AngleTurnCommand ATC = new AngleTurnCommand(initAngle());
		Robot.drivetrain.drive(1, 1);

	}
	protected void execute(){
		if(isDone()||currentSpike()){
			Robot.drivetrain.drive(0, 0);
			return;
		}


	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	public double initAngle(){
		//vision will find correct angle
		return 60;
	}

	public void moveFirstStretch(){
		sum += prevError;
		currentError = initDistance - (Robot.drivetrain.getLeft().getDistance() + Robot.drivetrain.getRight().getDistance())/2;
		Robot.drivetrain.drive(kp*currentError + ki*sum + kd*(currentError - prevError),
				kp*currentError + ki*sum + kd*(currentError - prevError));
		prevError = currentError;
	}
	public boolean isDone(){
		//vision processing method that determines whether robot is done driving
		return true;
	}
	public boolean currentSpike(){
		//Talon SRX current spike test
		return true;
	}






}
