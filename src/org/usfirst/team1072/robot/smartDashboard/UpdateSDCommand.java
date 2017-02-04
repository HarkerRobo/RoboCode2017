package org.usfirst.team1072.robot.smartDashboard;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.EventQueue;
import org.freedesktop.gstreamer.Bin;
import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.commands.AutonomousCommandGearOne;
import org.usfirst.frc.team1072.robot.commands.AutonomousCommandGearTwo;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * @author Ashwin Reddy
 */
public class UpdateSDCommand extends Command {
	private SmartDashboard sd;
	AutonomousCommandGearOne autonomousCommand1;
	AutonomousCommandGearTwo autonomousCommand2;
	BufferedImage image;
	private int port;
	private String ip;
	private static Pipeline pipe;
	
    public UpdateSDCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	sd = new SmartDashboard();
    	autonomousCommand1 = new AutonomousCommandGearOne(0);
    	autonomousCommand2 = new AutonomousCommandGearTwo();
    	Gst.init("CameraTest", null);
    	EventQueue.invokeLater(new Runnable() {
    		@Override
    		public void run() {
    			
    		}
    	});
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putData("Autonomous Command Gear 1", autonomousCommand1);
    	SmartDashboard.putData("Autonomous Command Gear 2", autonomousCommand2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putData(Scheduler.getInstance());
    	Robot.drivetrain.toSmartDashboard();
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
    
   
}
