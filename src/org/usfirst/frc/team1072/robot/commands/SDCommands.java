package org.usfirst.frc.team1072.robot.commands;

import java.awt.Image;
import java.awt.image.BufferedImage;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SDCommands extends Command {
	AutonomousCommandGearOne autonomousCommand1;
	AutonomousCommandGearTwo autonomousCommand2;
	BufferedImage image;
	private int port;
	private String ip;
	
	
    public SDCommands() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	autonomousCommand1 = new AutonomousCommandGearOne(0);
    	autonomousCommand2 = new AutonomousCommandGearTwo();
    	image  = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	SmartDashboard.putData("Autonomous Command Gear 1", autonomousCommand1);
    	SmartDashboard.putData("Autonomous Command Gear 2", autonomousCommand2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putData(Scheduler.getInstance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Autonomous Command Gear 1", "no longer running");
    	SmartDashboard.putString("Autonomous Command Gear 2", "no longer running");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public Image setImage(BufferedImage image){
    	
    	
    }
}
