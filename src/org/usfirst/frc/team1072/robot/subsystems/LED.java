/**
 * 
 */
package org.usfirst.frc.team1072.robot.subsystems;

import edu.wpi.first.wpilibj.hal.DIOJNI;

/**
 * @author joelmanning
 *
 */
public class LED {
	
	private static int onVal = 5;
	
	private int channel;
	
	public LED(int channel){
		this.channel = channel;
		
	}
	
	public void set(boolean on){
		DIOJNI.setDIO(channel, (short)(on ? onVal : 0));
	}
}
