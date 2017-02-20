package org.usfirst.frc.team1072.robot;

import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final GamepadWrapper gp = new GamepadWrapper(0);
	public static final GamepadWrapper gp2 = new GamepadWrapper(1, 1);
}
