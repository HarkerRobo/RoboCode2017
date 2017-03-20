package org.usfirst.frc.team1072.robot.commands.auton2;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.commands.DriveDistanceTimed.Surface;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroDriveCommand extends Command {
	
	public static enum Surface {
		FLOOR(50.9731), CARPET(40.9377);
		
		private double divisor;
		
		Surface(double divisor){
			this.divisor = divisor;
		}
	}
	
	public static final Surface SURFACE = Surface.CARPET;
	
	public static final double KP = 120;
	
	protected double zeroAngle;
	protected double zeroTime;
	
	protected double goalAngle;
	protected double goalDistance;

    public GyroDriveCommand(double distance) {
        requires(Robot.drivetrain);
        goalDistance = distance;
        goalAngle = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	zero();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double adj = (goalAngle + zeroAngle - Robot.gyro.getAngle()) % 360;
    	if(adj > 180)
    		adj -= 360;
    	adj /= KP;
    	SmartDashboard.putNumber("Adjustment", adj);
    	Robot.drivetrain.drive(0.4 + adj,  0.4 - adj);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (timeSinceInitialized() - zeroTime) >= goalDistance / SURFACE.divisor;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void zero(){
    	zeroAngle = Robot.gyro.getAngle();
    	zeroTime = timeSinceInitialized();
    }
}
