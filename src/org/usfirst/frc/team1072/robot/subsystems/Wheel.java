package org.usfirst.frc.team1072.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Wheel extends CANTalon {

	private static final double MAX_RATE = 40000;
	private static final double THRESHOLD = 0.05;
	private boolean reversed = false;
    private Encoder encoder;
    
    public Wheel(int port, Encoder encoder, boolean reversed){
    	super(port);
    	this.encoder = encoder;
    	this.reversed = reversed;
    }
    
    public Wheel(int port, Encoder encoder){
    	this(port, encoder, false);
    }
    
    /**
	 * @param speed
	 * @see com.ctre.CANTalon#set(double)
	 */
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
		setInternal(speed);
	}
	
	protected void setInternal(double speed){
		set(speed);
	}

	/**
	 * 
	 * @see edu.wpi.first.wpilibj.Encoder#reset()
	 */
	public void reset() {
		encoder.reset();
	}

	/**
	 * @return
	 * @see edu.wpi.first.wpilibj.Encoder#getDistance()
	 */
	public double getDistance() {
		return encoder.getDistance();
	}

	/**
	 * @return
	 * @see edu.wpi.first.wpilibj.Encoder#getRate()
	 */
	public double getRate() {
		if (encoder == null) { return 0; }
		double rate = reversed ? -encoder.getRate() : encoder.getRate();
		if(Math.abs(rate) > MAX_RATE){
			rate = Math.signum(rate) * MAX_RATE;
		}
		return rate;
	}

	/**
	 * @return the reversed
	 */
	public boolean isReversed() {
		return reversed;
	}

	/**
	 * @param reversed the reversed to set
	 */
	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}
	/**
	 * @return the threshold
	 */
	public static double getThreshold() {
		return THRESHOLD;
	}

	/**
	 * @return the encoder
	 */
	public Encoder getEncoder() {
		return encoder;
	}
	
	public void toSmartDashboard(String name) {
		SmartDashboard.putNumber("Speed of " + name, getRate());
		//.out.println("Speed of " + name + ": " + getRate());
		//SmartDashboard.putNumber("Current of " + name, getOutputCurrent());
	}
}

