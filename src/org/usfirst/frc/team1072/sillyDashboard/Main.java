package org.usfirst.frc.team1072.sillyDashboard;
import org.usfirst.frc.team1072.shared.Networking;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.freedesktop.gstreamer.Bin;
import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.json.JSONObject;
import org.usfirst.frc.team1072.shared.Client;
import org.usfirst.frc.team1072.robot.RaspiNetworker.JSONListener;
/**
 *
 * @author Ashwin Reddy
 *
 */
public class Main {
	private static final int PORT = 5001;

//	public static final String raspiIP = Networking.RASPI_IP;
//	public static final int port = 0;
	private static Pipeline pipe;
	private static int width = 640;
	private static int height = 480;
	
	public static RaspiNetworker raspinet;
	public static void main(String[] args) {
		raspinet = new RaspiNetworker();
		raspinet.run();
		raspinet.send(new JSONObject());
		Gst.init("CameraTest", args);
        EventQueue.invokeLater(new Runnable() {
//        	public Pipeline pipe;
            @Override
            public void run() {
                SimpleVideoComponent vc = new SimpleVideoComponent();
                Bin bin = Bin.launch("udpsrc port=5001 ! application/x-rtp, payload=96 ! rtph264depay ! avdec_h264 ! videoconvert", true);
                pipe = new Pipeline();
                pipe.addMany(bin, vc.getElement());
                Pipeline.linkMany(bin, vc.getElement());           

                JFrame f = new JFrame("Professor Reddy's Wonderful Copy+Paste Program");
                f.add(vc);
                vc.setPreferredSize(new Dimension(640, 480));
                f.pack();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                pipe.play();
                f.setVisible(true);
            }
        });
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
