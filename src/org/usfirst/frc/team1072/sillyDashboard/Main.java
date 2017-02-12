package org.usfirst.frc.team1072.sillyDashboard;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import org.freedesktop.gstreamer.Bin;
import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import org.json.JSONObject;
/**
 *
 * @author Ashwin Reddy
 * @author Ryan Adolf
 *
 */
public class Main {
	private static final int PORT_VISION = 5001;
	private static final int PORT_DRIVER = 5002;
	private static final int PORT = 6000;
	private static final String IP = "127.0.0.1"; //"192.168.1.29";

//	public static final String raspiIP = Networking.RASPI_IP;
//	public static final int port = 0;
	public static RaspiNetworker raspinetDriver;
	public static RaspiNetworker raspinetVision;
	public static void main(String[] args) {
		Gst.init("Stream viewer", args);
		
		raspinetVision = new RaspiNetworker(IP, 6000);
		raspinetVision.start();
		
		raspinetDriver = new RaspiNetworker(IP, 6001);
		raspinetDriver.start();
		initializeFrame();
	}
	
	public static void initializeFrame()
	{
		EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame("Camera Test");
                f.setLayout(new GridLayout(1,2));
                
                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setPreferredSize(new Dimension(640, 480));
                
                SimpleVideoComponent vc = GStreamerComponent.create(raspinetVision, PORT_VISION, 640, 480);
                vc.setBounds(0, 0, 640, 480);
                layeredPane.add(vc, new Integer(0));
                
                CornerViewer cv = new CornerViewer(raspinetVision);
                cv.setBounds(10, 0, 640, 480);
                layeredPane.add(cv, new Integer(1));
                
                f.add(layeredPane);
                f.add(GStreamerComponent.create(raspinetDriver, PORT_DRIVER, 1296, 972));
                
                f.pack();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                f.setVisible(true);
            }
        });
	}
}
