package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class DriveDistanceTimed extends TimedCommand {

	public static enum Surface {
		FLOOR(50.9731), CARPET(40.9377);
		
		private double divisor;
		
		Surface(double divisor){
			this.divisor = divisor;
		}
	}
	
	public static final Surface surface = Surface.CARPET;
	
	private double sign;
	private double startingAngle;
	public static final double kP = .1;
	public static final double kI  = .1;
	public static final double kD = .1;
	public static final double I = 1;
	public static final double D = 1;
	public double errors;
	public double prevError;

	
    public DriveDistanceTimed(double distance) {
        super(Math.abs(distance)/surface.divisor);
        sign = Math.signum(distance);
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.drive(sign * 0.4, sign * 0.4);
    	startingAngle = Robot.gyro.getAngle();
    }
    @Override 
    protected void execute(){
    	double error = (Robot.gyro.getAngle()-startingAngle)%360-180;
    	errors+=error;
    	double der = error-prevError;
    	double val = error*kP + errors*kI + der*kD;
    	Robot.drivetrain.drive(.4+val, .4-val);

    	prevError = error;
    }
    // Called once after timeout
    protected void end() {
    	Robot.drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
