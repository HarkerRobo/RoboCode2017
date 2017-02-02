/**
 * 
 */
package org.usfirst.frc.team1072.robot;

import org.usfirst.frc.team1072.robot.commands.CancelWhenPressed;
import org.usfirst.frc.team1072.robot.commands.StartWhenPressed;
import org.usfirst.frc.team1072.robot.commands.WheneverPressed;
import org.usfirst.frc.team1072.robot.commands.WhilePressed;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author joelmanning
 *
 */
public class XboxWrapper extends XboxController {
	
	private static XboxWrapper instance = new XboxWrapper(0);

	public static enum Button {
		A, B, X, Y, RSTICK, LSTICK, RBUMPER, LBUMPER
	}
	
	public static enum Axis {
		RIGHTX, RIGHTY, LEFTX, LEFTY, RIGHTTRIGGER, LEFTTRIGGER
	}
	
	/**
	 * @param port
	 */
	private XboxWrapper(int port) {
		super(port);
	}
	
	public boolean getButton(Button b){
		switch(b){
			case A : return getAButton();
			case B : return getBButton();
			case X : return getXButton();
			case Y : return getYButton();
			case RSTICK : return getStickButton(Hand.kRight);
			case LSTICK : return getStickButton(Hand.kLeft);
			case RBUMPER : return getBumper(Hand.kRight);
			case LBUMPER : return getBumper(Hand.kLeft);
			default : return false;
		}
	}
	
	public double getAxis(Axis a){
		switch(a){
			case RIGHTX : return getX(Hand.kRight);
			case RIGHTY : return getY(Hand.kRight);
			case LEFTX : return getX(Hand.kLeft);
			case LEFTY : return getY(Hand.kLeft);
			case RIGHTTRIGGER : return getTriggerAxis(Hand.kRight);
			case LEFTTRIGGER : return getTriggerAxis(Hand.kLeft);
			default : return 0;
		}
	}
	
	/*
	 * runs given command whenever button is pressed
	 */
	public Command whilePressed(Button b, Command c){
		return new WhilePressed(b, c);
	}
	
	/*
	 * runs given command once, starting when button is pressed
	 */
	public Command startWhenPressed(Button b, Command c){
		return new StartWhenPressed(b, c);
	}
	
	/*
	 * cancels given command as soon as button is pressed
	 */
	public Command cancelWhenPressed(Button b, Command c){
		return new CancelWhenPressed(b, c);
	}
	
	/*
	 * runs given command each time button is pressed
	 */
	public Command wheneverPressed(Button b, Command c){
		return new WheneverPressed(b, c);
	}
	
	public static XboxWrapper getInstance(){
		return instance;
	}
}
