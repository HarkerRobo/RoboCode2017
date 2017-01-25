package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.commands.AutonPistonCommand;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearPiston extends Subsystem {
	
	boolean buttonPressed;
	int pistonChannel;
	Solenoid piston;

	public GearPiston() {
		buttonPressed = false;
		pistonChannel = 0;
		piston = new Solenoid(pistonChannel);
	}
	
	/**
	 * @return the buttonPressed
	 */
	public boolean isButtonPressed() {
		return buttonPressed;
	}

	/**
	 * @param buttonPressed the buttonPressed to set
	 */
	public void setButtonPressed(boolean buttonPressed) {
		this.buttonPressed = buttonPressed;
	}

	/**
	 * @return the pistonChannel
	 */
	public int getPistonChannel() {
		return pistonChannel;
	}

	/**
	 * @param pistonChannel the pistonChannel to set
	 */
	public void setPistonChannel(int pistonChannel) {
		this.pistonChannel = pistonChannel;
	}

	/**
	 * @return the piston
	 */
	public Solenoid getPiston() {
		return piston;
	}

	/**
	 * @param piston the piston to set
	 */
	public void setPiston(Solenoid piston) {
		this.piston = piston;
	}
	
	public void putIn() {
		if (!buttonPressed) {
			buttonPressed = true;
			piston.set(buttonPressed);
		}
	}
	
	public void pullOut() {
		if (buttonPressed) {
			buttonPressed = false;
			piston.set(buttonPressed);
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new AutonPistonCommand());
	}

}
