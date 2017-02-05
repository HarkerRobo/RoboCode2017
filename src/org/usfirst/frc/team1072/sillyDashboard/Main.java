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
 * Main provides a GUI for viewing Raspberry Pi Camera Stream
 * using Gstreamer
 * @author Ashwin Reddy
 * @version Feb 5 2017
 *
 */
public class Main {
	private static final int PORT = 5001;
	private static Pipeline pipe;
	private static int width = 640;
	private static int height = 480;
    private static String title = "Prof. Reddy's Wonderful Silly Dashboard Copy+Paste Program";
    private static int iso = 500;
    private static int shutterspeed = 2000;

	public static RaspiNetworker raspinet;

    /**
     * main method for silly dashboard
     */
	public static void main(String[] args) {
		raspinet = new RaspiNetworker();
		raspinet.run();
		raspinet.send(new JSONObject());
		Gst.init("RaspiCamera", args);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimpleVideoComponent vc = new SimpleVideoComponent();
                Bin bin = Bin.launch("udpsrc port=" + PORT + " ! application/x-rtp, payload=96 ! rtph264depay ! avdec_h264 ! videoconvert", true);
                pipe = new Pipeline();
                pipe.addMany(bin, vc.getElement());
                Pipeline.linkMany(bin, vc.getElement());

                JFrame f = new JFrame(title);
                f.add(vc);
                vc.setPreferredSize(new Dimension(width, height));
                f.pack();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                pipe.play();
                f.setVisible(true);
            }
        });
		raspinet.send(createStartStreamMessage(iso, shutterspeed));
	}
    /**
     * Generates a start stream message
     * @param  int iso          camera sensivity
     * @param  int shutterspeed  speed of camera shutter
     * @return    JSON object with relevant data
     */
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
