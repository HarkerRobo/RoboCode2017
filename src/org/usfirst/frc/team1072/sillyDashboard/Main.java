package org.usfirst.frc.team1072.sillyDashboard;
import org.usfirst.frc.team1072.shared.Networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.json.JSONObject;
import org.usfirst.frc.team1072.shared.Client;
/**
 *
 * @author Ashwin Reddy
 *
 */
public class Main {
	private static final int PORT = 5001;

//	public static final String raspiIP = Networking.RASPI_IP;
//	public static final int port = 0;
	public static RaspiNetworker raspinet;
	public static void main(String[] args) {
		raspinet = new RaspiNetworker();
		raspinet.run();
		raspinet.send(createStartStreamMessage(500, 2000));
	}

	public static JSONObject createStartStreamMessage(int iso, int shutterspeed)
	{
		try {
			JSONObject message = new JSONObject();
			message.put("type", "start");
			message.put("port", PORT);
			message.put("host", InetAddress.getLocalHost());
			message.put("iso", iso);
			message.put("shutterspeed", shutterspeed);
			return message;
		} catch (UnknownHostException e) {
			System.out.println(e);
			return null;
		}
	}
}
