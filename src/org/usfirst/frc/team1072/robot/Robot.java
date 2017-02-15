
package org.usfirst.frc.team1072.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.usfirst.frc.team1072.robot.RobotMap.Gears;
import org.usfirst.frc.team1072.robot.XboxWrapper.Button;
import org.usfirst.frc.team1072.robot.commands.AutonomousCommandGearTwo;
import org.usfirst.frc.team1072.robot.commands.SlowModeCommand;
import org.usfirst.frc.team1072.robot.smartDashboard.UpdateSDCommand;
import org.usfirst.frc.team1072.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1072.robot.subsystems.OldDrivetrain;
import org.usfirst.frc.team1072.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team1072.robot.subsystems.GearPusher;
import org.usfirst.frc.team1072.robot.subsystems.PIDDrivetrain;
import org.usfirst.frc.team1072.robot.subsystems.Shifter;
import org.usfirst.frc.team1072.robot.subsystems.Winch;
//import org.usfirst.team1072.robot.smartDashboard.H264Widget;
import org.usfirst.frc.team1072.robot.subsystems.Piston;
import org.usfirst.frc.team1072.robot.subsystems.SolenoidSubsystem;
//import org.usfirst.team1072.robot.smartDashboard.UpdateSDCommand;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static enum WinchControl {
		TOGGLE, BUMPERS
	}
	
	public static enum DriveControl {
		TANK, ARCADE, PIDTEST
	}
	
	public static final WinchControl winchControl = WinchControl.BUMPERS;
	public static final DriveControl driveControl = DriveControl.TANK;
	public static OI oi;
	public static PIDDrivetrain drivetrain;
	public static Piston gearPiston;
	public static Winch winch;
	//public static RaspiNetworker raspi;
	public static Compressor compress;
	public static GearPusher push;
	public static SolenoidSubsystem shifter;
	public static ADXRS450_Gyro gyro;
	public static BuiltInAccelerometer accel;

	Command autonomousCommand;
	//SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		drivetrain = new PIDDrivetrain();
		winch = new Winch();
		push = new GearPusher();
		shifter = new Shifter();
		gyro = new ADXRS450_Gyro();
		accel = new BuiltInAccelerometer();
		//raspi = new RaspiNetworker();
		compress = new Compressor(0);
		compress.setClosedLoopControl(true);
		//CameraServer.getInstance().startAutomaticCapture();
		//raspi.start();
		//SmartDashboard.putData("H264", new H264Widget());
		//SmartDashboard.putData("Test Encoders:", new EncoderTest());
		//gearPiston = new GearPiston();
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", chooser);
		//SmartDashboard.putNumber("Value" + counter, 5.0);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		drivetrain.getLeft().reset();
		drivetrain.getRight().reset();
		drivetrain.disable();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autks with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboaonomous modes using the dashboard. The sendable
	 * chooser code worrd, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
//		autonomousCommand = chooser.getSelected();
//
//		String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
//		switch(autoSelected) {
//			case "My Auto": autonomousCommand = new MyAutoCommand(); break;
//			case "Default Auto": default: autonomousCommand = new ExampleCommand(); break;
//		}
//
//		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		UpdateSDCommand sdc = new UpdateSDCommand();
		sdc.start();
		XboxWrapper.getInstance().whenPressed(Button.A, new SlowModeCommand());
		Robot.drivetrain.enable();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
