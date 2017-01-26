package org.usfirst.frc.team1072.sillyDashboard;
import java.io.Serializable;

/**
 * 
 * @author Ashwin
 *
 */
public class PortMessage extends Message {
	private static final long serialVersionUID = -2747663880481344516L;
	private int port;
	
	public PortMessage(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
}
