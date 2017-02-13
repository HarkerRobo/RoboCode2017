package org.usfirst.frc.team1072.robot.smartDashboard;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.EventQueue;
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
	AutonomousCommandGearOne autonomousCommand1;
	AutonomousCommandGearTwo autonomousCommand2;
	BufferedImage image;
	private int port;
	private String ip;

    public UpdateSDCommand() {
    	autonomousCommand1 = new AutonomousCommandGearOne(0);
    	autonomousCommand2 = new AutonomousCommandGearTwo();
    }

    protected void initialize() {
    	SmartDashboard.putData("Autonomous Command Gear 1", autonomousCommand1);
    	SmartDashboard.putData("Autonomous Command Gear 2", autonomousCommand2);
    }

    protected void execute() {
    	//Commands
    	SmartDashboard.putData(Scheduler.getInstance());
    	//Subsystems
    	Robot.drivetrain.toSmartDashboard();
    	Robot.winch.toSmartDashboard("Winch");
    	Robot.push.toSmartDashboard();
    	Robot.shifter.toSmartDashboard("Shifter Solenoid");
    	//Misc
    	SmartDashboard.putNumber("GyroAngle",Robot.drivetrain.getGyro().getAngle());
    	SmartDashboard.putNumber("GyroRate", Robot.drivetrain.getGyro().getRate());
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
