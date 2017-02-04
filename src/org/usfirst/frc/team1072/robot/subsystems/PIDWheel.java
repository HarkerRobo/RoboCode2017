/**
 * 
 */
package org.usfirst.frc.team1072.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * @author joelmanning
 *
 */
public class PIDWheel extends Wheel {
	
	private PIDController pid;
	
	/**
	 * @param port
	 * @param encoder
	 */
	public PIDWheel(int port, Encoder encoder, double p, double i, double d) {
		this(port, encoder, false, p, i, d);
	}

	/**
	 * @param port
	 * @param encoder
	 * @param reversed
	 */
	public PIDWheel(int port, Encoder encoder, boolean reversed, double p, double i, double d) {
		super(port, encoder, reversed);
		encoder.setPIDSourceType(PIDSourceType.kRate);
		pid = new PIDController(p, i, d, encoder, this);
	}

	/**
	 * @see edu.wpi.first.wpilibj.PIDController#enable()
	 */
	public void enable() {
		pid.enable();
	}

	@Override
	public void setInternal(double speed) {
		pid.setSetpoint(speed);
	}
	
<<<<<<< Updated upstream
<<<<<<< Updated upstream
	public void toSmartDashboard(String name) {
		SmartDashboard.putNumber("Speed of " + name, getRate());
		SmartDashboard.putNumber("Proportional constant for " + name, this.getP());
		SmartDashboard.putNumber("Integral constant for " + name, this.getI());
		SmartDashboard.putNumber("Derivative constant for" + name, this.getD());
	}
=======
	
>>>>>>> Stashed changes
=======
	
>>>>>>> Stashed changes
}
