package org.usfirst.frc.team1072.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

public class PIDSide {
	
	private Wheel front;
	private Wheel back;
	private Encoder encoder;
	private PIDController pid;
	
	public PIDSide(int frontChannel, int backChannel, int encoderA, int encoderB, boolean reversed){
		encoder = new Encoder(encoderA, encoderB);
		front = new Wheel(frontChannel, encoder, reversed);
		back = new Wheel(backChannel, encoder, reversed);
	}
}
