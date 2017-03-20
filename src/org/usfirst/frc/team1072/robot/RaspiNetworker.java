/**
 * 
 */
package org.usfirst.frc.team1072.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * @author joelmanning
 *
 */
public class RaspiNetworker extends Thread {
	
	public static final String ip = "10.10.72.12";
	public static final int port = 5800;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private List<JSONListener> listeners;
	private boolean running;
	
	public RaspiNetworker(){
		listeners = new ArrayList<JSONListener>();
		try {
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
		} catch(UnknownHostException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
			DriverStation.reportError("IOException", false);
		}
	}
	
	public void run(){
		running = true;
		while(running){
			try {
				String line = in.readLine();
				if(line == null || line.equals("")){
					continue;
				}
				System.out.println("Recieved line: " + line);
				JSONObject obj = new JSONObject(line);
				for(JSONListener l: listeners){
					l.recieve(obj);
				}
				System.out.println("Recieved JSON Object: " + obj.toString());
			} catch(JSONException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void send(JSONObject obj){
		out.println(obj.toString());
	}
	
	public void addListener(JSONListener l){
		listeners.add(l);
	}
	
	public interface JSONListener {
		public void recieve(JSONObject obj);
	}
	
	public void end(){
		running = false;
	}
	
	public void clearListeners(){
		listeners.clear();
	}
}