/**
 * 
 */
package org.usfirst.frc.team1072.robot;

import org.usfirst.frc.team1072.robot.XboxWrapper.Axis;
import org.usfirst.frc.team1072.robot.XboxWrapper.Button;
import org.usfirst.frc.team1072.robot.subsystems.Drivetrain.DriveControl;

/**
 * @author joelmanning
 *
 */
public class Controls {
	
	//Drivetrain
	public static final Axis TANK_LEFT = Axis.LEFTY;
	public static final Axis TANK_RIGHT = Axis.RIGHTY;
	public static final Axis ARCADE_Y = Axis.RIGHTY;
	public static final Axis ARCADE_X = Axis.RIGHTX;
	public static final Button SLOW_MODE = Button.A;
	public static final Button PID_TEST = Button.RSTICK;
	public static final DriveControl DRIVE_CONTROL = DriveControl.TANK;
	
	//Winch
	public static final Button WINCH_WHILE_PRESSED = Button.RBUMPER;
	public static final Button WINCH_TOGGLE = Button.RBUMPER;
	
	//Solenoids
	public static final Button PUSHER = Button.X;
	public static final Button CLOSER = Button.Y;
	public static final Button SHIFTER = Button.B;
}