package org.usfirst.frc.team1072.robot.smartDashboard;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.EventQueue;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.commands.AngleTurnCommand;
import org.usfirst.frc.team1072.robot.commands.AutonomousCommandGear2;
import org.usfirst.frc.team1072.robot.commands.AutonomousGear1CommandGroup;
import org.usfirst.frc.team1072.robot.commands.AutonomousGear2CommandGroup;
import org.usfirst.frc.team1072.robot.commands.AutonomousGear3CommandGroup;
import org.usfirst.frc.team1072.robot.commands.AutonomousGear4CommandGroup;
import org.usfirst.frc.team1072.robot.commands.AutonomousGear5CommandGroup;
import org.usfirst.frc.team1072.robot.commands.MoveDistanceCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * @author Ashwin Reddy
 */
public class UpdateSDCommand extends Command {
	AutonomousGear1CommandGroup autonomousCommand1;
	AutonomousGear2CommandGroup autonomousCommand2;
	AutonomousGear3CommandGroup autonomousCommand3;
	AutonomousGear4CommandGroup autonomousCommand4;
	AutonomousGear5CommandGroup autonomousCommand5;
	BufferedImage image;
	private int port;
	private String ip;

    public UpdateSDCommand() {
    	autonomousCommand1 = new AutonomousGear1CommandGroup();
    	autonomousCommand2 = new AutonomousGear2CommandGroup();
    	autonomousCommand3 = new AutonomousGear3CommandGroup();
    	autonomousCommand4 = new AutonomousGear4CommandGroup();
    	autonomousCommand5 = new AutonomousGear5CommandGroup();
    }

    protected void initialize() {
    	SendableChooser<String> side = new SendableChooser<String>();
    	side.addObject("Red", (String) "Red");	
    	side.addObject("Blue", (String) "Blue");
    	SendableChooser<String> gear = new SendableChooser<String>();
    	gear.addObject("Left", (String) "Left");
    	gear.addObject("Center", (String) "Center");
    	gear.addObject("Right", (String) "Right");
    	
    	SmartDashboard.putData("Side Chooser", side);
    	SmartDashboard.putData("Gear Chooser", gear);
    	SmartDashboard.putData("Autonomous Command Gear 1", autonomousCommand1);
    	SmartDashboard.putData("Autonomous Command Gear 2", autonomousCommand2);
    	SmartDashboard.putData("Autonomous Command Gear 3", autonomousCommand3);
    	SmartDashboard.putData("Autonomous Command Gear 4", autonomousCommand4);
    	SmartDashboard.putData("Autonomous Command Gear 5", autonomousCommand5);
    	SmartDashboard.putData("Turn 90 Degrees", new AngleTurnCommand(90));
    	SmartDashboard.putData("Turn -90 Degrees", new AngleTurnCommand(-90));
    	SmartDashboard.putData("Move 100 Inches", new MoveDistanceCommand(100));
    }

    protected void execute() {
    	//Commands
    	SmartDashboard.putData(Scheduler.getInstance());
    	SmartDashboard.putData("Turn 90 Degrees", new AngleTurnCommand(90));
    	SmartDashboard.putData("Turn -90 Degrees", new AngleTurnCommand(-90));
    	//Subsystems
    	Robot.drivetrain.toSmartDashboard();
    	//Robot.winch.toSmartDashboard("Winch");
    	Robot.push.toSmartDashboard();
    	Robot.shifter.toSmartDashboard("Shifter");
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
