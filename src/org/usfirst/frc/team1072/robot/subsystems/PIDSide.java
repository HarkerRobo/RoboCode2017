package org.usfirst.frc.team1072.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDSide implements PIDSource, PIDOutput{
	
	private static final double THRESHOLD = 0.03;
	private Wheel front;
	private Wheel back;
	private Encoder encoder;
	private PIDController pid;
	
	private PIDSourceType sourceType;
	private boolean reversed;
	
	public PIDSide(int frontChannel, int backChannel, int encoderA, int encoderB, boolean reversed, double Kp, double Ki, double Kd){
		encoder = new Encoder(encoderA, encoderB);
		encoder.setDistancePerPulse(0.110087234303968548662280932146080546444);//what a wonderful magic number (in inches)
		front = new Wheel(frontChannel, encoder, reversed);
		back = new Wheel(backChannel, encoder, reversed);
		pid = new PIDController(Kp, Ki, Kd, this, this);
		sourceType = PIDSourceType.kRate;
		this.reversed = reversed;
	}
	
	public void enable(){
		front.enable();
		back.enable();
		pid.enable();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.PIDOutput#pidWrite(double)
	 */
	@Override
	public void pidWrite(double output) {
		front.setInternal(output);
		back.setInternal(output);
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.PIDSource#setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType)
	 */
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		sourceType = pidSource;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.PIDSource#getPIDSourceType()
	 */
	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.PIDSource#pidGet()
	 */
	@Override
	public double pidGet() {
		switch(sourceType){
			case kRate:
				return getRate();
			case kDisplacement:
				return getDistance();
			default:
				return 0;
		}
	}
	
	public void setSpeed(double speed) {
		//check if speed it out of bounds
		speed = Math.max(Math.min(speed,  1), -1);
		//check if speed is within threshold of 0
		speed = (Math.abs(speed) < THRESHOLD) ? 0 : speed;
		//square speed for better control while preserving sign
		speed = Math.signum(speed) * Math.pow(speed, 2);
		//check if reversed
		if(reversed){
			speed = -speed;
		}
		pid.setSetpoint(speed);
	}
	
	public double getRate(){
		return reversed ? -encoder.getRate() : encoder.getRate();
	}
	
	public double getDistance(){
		return reversed ? -encoder.getDistance() : encoder.getDistance();
	}
}
