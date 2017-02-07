package org.usfirst.frc.team1072.sillyDashboard;

import org.json.JSONObject;

import java.util.ArrayList;

import org.json.JSONArray;

/**
 * Class RaspiListener represents essentially a callback function for what to do
 * with received JSONObject messages
 * @author ashwinreddy
 * @version Feb 5 2017
 */
public class RaspiListener implements RaspiNetworker.JSONListener {
	private ArrayList<Integer> points;

    /**
     * Receives JSON and processes it as a 3D array
     * @param JSONObject obj JSONObject from RasPi
     */
	@Override
	public void recieve(JSONObject obj) {
        // TODO: make this code less terrible, maybe use an enhanced for loop?
		JSONArray results = obj.getJSONArray("results");
		System.out.println(results.toString());
		for(int i = 0; i < results.length(); i++) {
			JSONArray arr = results.getJSONArray(i);
			for(int j = 0; j < arr.length(); j++) {
                JSONArray arr2 = arr.getJSONArray(j);
                for(int k = 0; k < arr2.length(); k++) {
                   points.add(arr2.getInt(k));
                }
			}
		}
	}
}
