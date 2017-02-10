package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap.PID;

import edu.wpi.first.wpilibj.command.Command;

public class ImageAngleTurnCommand extends Command {
	
	private double gearX, gearY;
	private double imWidth = 640;
	private double imHeight = 320;
	private double prevError;
	double kp = PID.TurnAngle.P, ki = PID.TurnAngle.I, kd = PID.TurnAngle.D;
	double sum = 0;
	double currentError;
	double errMargin = 1;
	
	public ImageAngleTurnCommand() {
		
	}

	protected void initialize() {
    }
	
	protected void execute() {
		// Image Processing gives us the center of the rectangles
//		gearX = ImageProcessing.getGearX();
//		gearY = ImageProcessing.getGearY();
		sum += prevError;
		currentError = imWidth/2 - gearX;
		Robot.drivetrain.tankDrive(kp*currentError + ki*sum + kd*(currentError - prevError),
				-(kp*currentError + ki*sum + kd*(currentError - prevError)));
		prevError = currentError;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return noError(imWidth/2 - gearX);
	}

    // Called once after isFinished returns true
    protected void end() {
    	
    }
    
    private boolean noError(double err) {
    	if (err <= errMargin && err >= -errMargin) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
