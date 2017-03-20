package org.usfirst.frc.team1072.robot.commands.auton2;

import org.json.JSONArray;
import org.json.JSONObject;
import org.usfirst.frc.team1072.robot.Robot;
import org.usfirst.frc.team1072.robot.RaspiNetworker.JSONListener;

/**
 *
 */
public class DriveWithVision extends GyroDriveCommand {

    public DriveWithVision(double initialDistance, double initialAngle) {
        super(initialDistance);
        this.zeroAngle = initialAngle;
    }
    
    @Override
    public void initialize(){
    	super.initialize();
        Robot.rpinet.addListener(new JSONListener(){

			@Override
			public void recieve(JSONObject obj) {
				JSONArray arr = obj.getJSONArray("corners");
				setGoals(arr.getDouble(1), arr.getDouble(0));
			}
        	
        });
        Robot.rpinet.start();
    }
    
    public void setGoals(double angle, double distance){
    	goalAngle = angle;
    	goalDistance = distance;
    	zero();
    }
    
    @Override
    public void end(){
    	super.end();
    	Robot.rpinet.end();
    	Robot.rpinet.clearListeners();
    }
}
