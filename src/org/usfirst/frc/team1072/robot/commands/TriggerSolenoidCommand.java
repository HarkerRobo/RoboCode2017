package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.subsystems.SolenoidSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
    	if(sol.getSol().get().equals(Value.kForward)){
    		sol.getSol().set(Value.kReverse);
    	} else {
    		sol.getSol().set(Value.kForward);
    	}
    }

}
