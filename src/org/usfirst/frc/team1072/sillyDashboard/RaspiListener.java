package org.usfirst.frc.team1072.sillyDashboard;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Class RaspiListener represents essentially a callback function for what to do 
 * with received JSONObject messages
 * @author ashwinreddy
 */
public class RaspiListener implements RaspiNetworker.JSONListener {
	@Override
	public void recieve(JSONObject obj) {
		String results = obj.getJSONArray("results").toString();
		System.out.println(results);
	}
}
