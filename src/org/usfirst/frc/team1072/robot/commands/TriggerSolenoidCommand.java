package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.subsystems.SolenoidSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class TriggerSolenoidCommand extends InstantCommand {

	private SolenoidSubsystem sol;
	
    public TriggerSolenoidCommand(SolenoidSubsystem sol) {
        super();
        requires(sol);
        this.sol = sol;
    }

    // Called once when the command executes
    protected void initialize() {
    	DoubleSolenoid.Value currentState = sol.getSol().get();
    	DoubleSolenoid.Value newState;
    	if (currentState == DoubleSolenoid.Value.kForward)
    		newState = DoubleSolenoid.Value.kReverse;
    	else if (currentState == DoubleSolenoid.Value.kReverse)
    		newState = DoubleSolenoid.Value.kForward;
    	else
    		newState = DoubleSolenoid.Value.kForward; //Default if solenoid is in kOff
    	sol.getSol().set(newState);
    }

}
