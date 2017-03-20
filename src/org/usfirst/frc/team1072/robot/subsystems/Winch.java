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
	
	private CANTalon talon1;
	private CANTalon talon2;
	public Winch(){
		talon1 = new CANTalon(Winches.A);
		talon2 = new CANTalon(Winches.B);
	}

	/**
	 * @param speed
	 * @see com.ctre.CANTalon#set(double)
	 */
	public void setSpeed(double speed) {
		talon1.set(speed);
		talon2.set(speed);
	}

	public void initDefaultCommand() {
		switch(Robot.winchControl){
			case BUMPERS:
				setDefaultCommand(new BumperWinchCommand());
				System.out.println("Bumper Winch");
				break;
			case TOGGLE:
				XboxWrapper.getInstance().toggleWhenPressed(Button.A, new WinchToggleCommand(-1));
				break;
			default:
				System.err.println("No winch control");
				break;
		}
	}
}

