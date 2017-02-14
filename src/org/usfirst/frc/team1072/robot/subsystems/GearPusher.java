package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.Controls;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.XboxWrapper;
import org.usfirst.frc.team1072.robot.XboxWrapper.Button;
import org.usfirst.frc.team1072.robot.commands.CloserCommand;
import org.usfirst.frc.team1072.robot.commands.PusherCommand;
import org.usfirst.frc.team1072.robot.commands.TriggerSolenoidCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearPusher extends Subsystem {

    private DoubleSolenoid push;
    private DoubleSolenoid close;
    
    public GearPusher(){
    	push = new DoubleSolenoid(RobotMap.Gears.PUSHER_F, RobotMap.Gears.PUSHER_R);
    	close = new DoubleSolenoid(RobotMap.Gears.CLOSER_F, RobotMap.Gears.CLOSER_R);
    }

    public void initDefaultCommand() {
        XboxWrapper.getInstance().whenPressed(Controls.PUSHER, new PusherCommand());
        XboxWrapper.getInstance().whenPressed(Controls.CLOSER, new CloserCommand());
    }

	public DoubleSolenoid getPush() {
		return push;
	}

	public DoubleSolenoid getClose() {
		return close;
	}
	public void toSmartDashboard()
    {
    	SmartDashboard.putBoolean("Pusher out", push.get().equals(Value.kForward));
    	SmartDashboard.putBoolean("Closer out", close.get().equals(Value.kForward));
    }
    
    
}

