package org.usfirst.frc.team1072.SillyDashboard;

import org.usfirst.frc.team1072.shared.ImageViewer;
import org.usfirst.frc.team1072.shared.Client;

/**
 * Provides a custom dashboard to display images from the Raspberry Pi
 * @author Ashwin Reddy
 * @version 24 Jan 2016
 */
public class Main {
	private static  int height = 640;
	private static int width = 480;
	private static ImageViewer imgviewer;
	private static Client c;
	
	private static String ip;
	private static int port;
	
	/**
	 * listen for images and display them
	 */
	public static void main(String[] args) {
//		send a serializable object to a given ip on a given port 
		// send a string with port to be listened to
		// serialization constant
		PortMessage pm = new PortMessage(port);
	}
}
