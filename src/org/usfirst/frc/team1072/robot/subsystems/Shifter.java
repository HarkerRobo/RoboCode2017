/**
 * 
 */
package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.robot.Controls;
import org.usfirst.frc.team1072.robot.RobotMap;

/**
 * @author joelmanning
 *
 */
public class Shifter extends SolenoidSubsystem {

	public Shifter() {
		super(RobotMap.Gears.SHIFTER_F, RobotMap.Gears.SHIFTER_R, Controls.SHIFTER);
	}
	
}
