package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.XboxWrapper;
import org.usfirst.frc.team1072.robot.XboxWrapper.Button;
import org.usfirst.frc.team1072.robot.commands.TriggerSolenoidCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SolenoidSubsystem extends Subsystem {

    private DoubleSolenoid sol;
    private Button trigger;
    
    public SolenoidSubsystem(int channelF, int channelR, Button trigger){
    	sol = new DoubleSolenoid(channelF, channelR);
    	this.trigger = trigger;
    }

    public void initDefaultCommand() {
        XboxWrapper.getInstance().whenPressed(trigger, new TriggerSolenoidCommand(this));
    }

	public DoubleSolenoid getSol() {
		return sol;
	}

	public void toSmartDashboard(String name){
		SmartDashboard.putData("Solenoid " + name,  sol);
	}
    
}

