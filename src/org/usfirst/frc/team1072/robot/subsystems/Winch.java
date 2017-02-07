package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.RobotMap.Robot.Winches;
import org.usfirst.frc.team1072.robot.XboxWrapper;
import org.usfirst.frc.team1072.robot.XboxWrapper.Button;
import org.usfirst.frc.team1072.robot.commands.BumperWinchCommand;
import org.usfirst.frc.team1072.robot.commands.WinchCommand;
import org.usfirst.frc.team1072.robot.commands.WinchToggleCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Winch extends Subsystem {
	
	private Wheel wheel;
	private int port = Winches.port;
	public Winch(){
		wheel = new Wheel(port, null);
	}

	/**
	 * @param speed
	 * @see com.ctre.CANTalon#set(double)
	 */
	public void setSpeed(double speed) {
		wheel.setSpeed(speed);
	}

	/**
	 * 
	 * @see edu.wpi.first.wpilibj.Encoder#reset()
	 */
	public void reset() {
		wheel.reset();
	}

	/**
	 * @return
	 * @see edu.wpi.first.wpilibj.Encoder#getDistance()
	 */
	public double getDistance() {
		return wheel.getDistance();
	}

	/**
	 * @return
	 * @see edu.wpi.first.wpilibj.Encoder#getRate()
	 */
	public double getRate() {
		return wheel.getRate();
	}

	public void initDefaultCommand() {
		switch(Robot.winchControl){
			case BUMPERS:
				setDefaultCommand(new BumperWinchCommand());
			case TOGGLE:
				XboxWrapper.getInstance().toggleWhenPressed(Button.A, new WinchToggleCommand(1));
				XboxWrapper.getInstance().toggleWhenPressed(Button.B, new WinchToggleCommand(-1));
			default:
				System.err.println("No winch control");
		}
	}

	/**
	 * @return the reversed
	 */
	public boolean isReversed() {
		return wheel.isReversed();
	}

	/**
	 * @param reversed the reversed to set
	 */
	public void setReversed(boolean reversed) {
		wheel.setReversed(reversed);
	}

	/**
	 * @return the threshold
	 */
	public static double getThreshold() {
		return Wheel.getThreshold();
	}

	/**
	 * @return the encoder
	 */
	public Encoder getEncoder() {
		return wheel.getEncoder();
	}

	public void toSmartDashboard(String name) {
		SmartDashboard.putNumber("Speed of " + name, getRate());
	}
}

