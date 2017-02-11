package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.XboxWrapper;
import org.usfirst.frc.team1072.robot.XboxWrapper.Button;
import org.usfirst.frc.team1072.robot.commands.CloseCommand;
import org.usfirst.frc.team1072.robot.commands.PusherCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearPusher extends Subsystem {

    private DoubleSolenoid push;
    private DoubleSolenoid close;
    private Button bpush;
    private Button bclose;
    
    public GearPusher(Button bpush, Button bclose){
    	push = new DoubleSolenoid(RobotMap.Gears.PUSHER_F, RobotMap.Gears.PUSHER_R);
    	close = new DoubleSolenoid(RobotMap.Gears.CLOSER_F, RobotMap.Gears.CLOSER_R);
    	this.bpush = bpush;
    	this.bclose = bclose;
    }

    public void initDefaultCommand() {
        XboxWrapper.getInstance().whenPressed(bpush, new PusherCommand());
        XboxWrapper.getInstance().whenPressed(bclose, new CloseCommand());
    }

	public DoubleSolenoid getPush() {
		return push;
	}

	public DoubleSolenoid getClose() {
		return close;
	}
	public void toSmartDashboard()
    {
    	SmartDashboard.putString("push: position",getPush().get().toString());
    	SmartDashboard.putString("close: position",getClose().get().toString());
    	
    }
    
    
}

