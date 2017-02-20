/**
 * 
 */
package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.Controls;
import org.usfirst.frc.team1072.robot.OI;
import org.usfirst.frc.team1072.robot.RobotMap;
import org.usfirst.frc.team1072.robot.commands.TriggerSolenoidCommand;

/**
 * @author joelmanning
 *
 */
public class Shifter extends SolenoidSubsystem {

	public Shifter() {
		super(RobotMap.Gears.SHIFTER_F, RobotMap.Gears.SHIFTER_R, Controls.SHIFTER);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1072.robot.subsystems.SolenoidSubsystem#initDefaultCommand()
	 */
	@Override
	public void initDefaultCommand() {
		OI.gp.getButtonB().whenPressed(new TriggerSolenoidCommand(this));
	}
	
	
}
