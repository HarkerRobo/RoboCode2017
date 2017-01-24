package smartDashboard;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateSDCommand extends Command {
	private SmartDashboard sd;
	private Encoder talon1;
	private Encoder talon2;
	private Encoder talon3;
	private Encoder talon4;
	
    public UpdateSDCommand(Encoder talon1, Encoder talon2, Encoder talon3, Encoder talon4) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	sd = new SmartDashboard();
    	this.talon1 = talon1;
    	this.talon2 = talon2;
    	this.talon3 = talon3;
    	this.talon4 = talon4;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	sd.putString("title", "Title");  
    	sd.putNumber("talonspeed", talon1.getRate());
    	sd.putNumber("talonspeed", talon2.getRate());
    	sd.putNumber("talonspeed", talon3.getRate());
    	sd.putNumber("talonspeed", talon4.getRate());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
