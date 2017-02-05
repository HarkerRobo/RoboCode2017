package org.usfirst.frc.team1072.sillyDashboard;
import org.usfirst.frc.team1072.shared.Networking;
import org.json.JSONObject;
import org.usfirst.frc.team1072.shared.Client;
/**
 * 
 * @author Ashwin Reddy
 *
 */
public class Main {
//	public static final String raspiIP = Networking.RASPI_IP;
//	public static final int port = 0;
	public static RaspiNetworker raspinet;
	public static void main(String[] args) {
		raspinet = new RaspiNetworker();
		raspinet.run();
		raspinet.send(new JSONObject());
	}
}
