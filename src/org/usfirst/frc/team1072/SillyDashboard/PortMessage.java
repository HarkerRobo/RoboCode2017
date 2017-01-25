package org.usfirst.frc.team1072.SillyDashboard;
import java.io.Serializable;


public class PortMessage implements Serializable {
	private static final long serialVersionUID = -2747663880481344516L;
	private int port;
	
	
	public PortMessage(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
}
