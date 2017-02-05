/**
 * 
 */
package org.usfirst.frc.team1072.sillyDashboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author joelmanning
 *
 */
public class RaspiNetworker extends Thread {
	//Message types
	public static final String TYPE_START_STREAM = "start";
	public static final String TYPE_STOP_STREAM = "stop";
	public static final String TYPE_ERROR = "error";
	public static final String TYPE_RESULTS = "results";

	//Fields
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_PORT = "port";
	public static final String FIELD_HOST = "host";
	public static final String FIELD_ISO = "iso";
	public static final String FIELD_SS = "shutterspeed";
	public static final String FIELD_ERROR = "message";
	public static final String FIELD_CORNERS = "corners";
	
	public static final String ip = "10.10.72.43";
	public static final int port = 3000;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private List<JSONListener> listeners;
	
	public RaspiNetworker(){
		try {
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			listeners = new ArrayList<JSONListener>();
		} catch(UnknownHostException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			try {
				JSONObject obj = new JSONObject(in.readLine());
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
}
