package org.usfirst.frc.team1072.robot.smartDashboard;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.EventQueue;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.commands.AutonomousCommandGear2;
import org.usfirst.frc.team1072.robot.commands.AutonomousGear1CommandGroup;
import org.usfirst.frc.team1072.robot.commands.AutonomousGear2CommandGroup;
import org.usfirst.frc.team1072.robot.commands.AutonomousGear3CommandGroup;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * @author Ashwin Reddy
 */
public class UpdateSDCommand extends Command {
	AutonomousCommandGear2 autonomousCommand2; /* Is this needed? */
	BufferedImage image;
	private int port;
	private String ip;

    public UpdateSDCommand() {
    	autonomousCommand2 = new AutonomousCommandGear2(); /* Is this needed? */
    }

    protected void initialize() {
    	SendableChooser side = new SendableChooser();
    	side.addObject("Red", (String) "Red");	
    	side.addObject("Blue", (String) "Blue");
    	SendableChooser gear = new SendableChooser();
    	gear.addObject("Left", (String) "Left");
    	gear.addObject("Center", (String) "Center");
    	gear.addObject("Right", (String) "Right");
    	
    	SmartDashboard.putData("Side Chooser", side);
    	SmartDashboard.putData("Gear Chooser", gear);
    	
    	SmartDashboard.putData("Autonomous Command Gear 2", autonomousCommand2); /* Is this needed? */
    }

    protected void execute() {
    	//Commands
    	SmartDashboard.putData(Scheduler.getInstance());
    	//Subsystems
    	Robot.drivetrain.toSmartDashboard();
    	//Robot.winch.toSmartDashboard("Winch");
    	Robot.push.toSmartDashboard();
    	Robot.shifter.toSmartDashboard("Shifter Solenoid");
    	//Misc
    	SmartDashboard.putNumber("GyroAngle", Robot.gyro.getAngle());
    	SmartDashboard.putNumber("GyroRate", Robot.gyro.getRate());
    	SmartDashboard.putBoolean("Is Compressor Enabled", Robot.compress.enabled());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	SmartDashboard.putString("Autonomous Command Gear 1", "no longer running");
    	SmartDashboard.putString("Autonomous Command Gear 2", "no longer running");
    }

    protected void interrupted() {
    }


}
