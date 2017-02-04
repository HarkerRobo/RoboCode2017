/**
 * 
 */
package org.usfirst.frc.team1072.robot;

import java.util.EnumMap;
import java.util.Map;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author joelmanning
 *
 */
public class XboxWrapper extends XboxController {
	
	private static XboxWrapper instance = new XboxWrapper(0);

	private Map<Button, JoystickButton> buttons;
	
	public static enum Button {
		A(1), B(2), X(3), Y(4), LBUMPER(5), RBUMPER(6), LSTICK(9), RSTICK(10);
		
		private int num;
		
		private Button(int num){
			this.num = num;
		}
	}
	
	public static enum Axis {
		RIGHTX, RIGHTY, LEFTX, LEFTY, RIGHTTRIGGER, LEFTTRIGGER
	}
	
	/**
	 * @param port
	 */
	private XboxWrapper(int port) {
		super(port);
		buttons = new EnumMap<Button, JoystickButton>(Button.class);
		for(Button b: Button.values()){
			buttons.put(b, new JoystickButton(this, b.num));
		}
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
	
	public void cancelWhenPressed(Button b, Command c){
		buttons.get(b).cancelWhenPressed(c);
	}
	
	public void toggleWhenPressed(Button b, Command c){
		buttons.get(b).toggleWhenPressed(c);
	}
	
	public void whenPressed(Button b, Command c){
		buttons.get(b).whenPressed(c);
	}
	
	public void whenReleased(Button b, Command c){
		buttons.get(b).whenReleased(c);
	}
	
	public void whileHeld(Button b, Command c){
		buttons.get(b).whileHeld(c);
	}
	
	public static XboxWrapper getInstance(){
		return instance;
	}
}
