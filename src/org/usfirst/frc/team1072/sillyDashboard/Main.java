package org.usfirst.frc.team1072.sillyDashboard;


import org.usfirst.frc.team1072.shared.Networking;
import org.usfirst.frc.team1072.shared.messages.PortMessage;

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
 * @author ashwinreddy
 * @version Feb 5 2017
 *
 */
public class Main {
	private static final int PORT = 5001;
	private static Pipeline pipe;
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
    private static final String TITLE = "Prof. Reddy's Wonderful Silly Dashboard Copy+Paste Program";
    private static int iso = 500;
    private static int shutterspeed = 2000;

	public static RaspiNetworker raspinet;
	private static RaspiListener raspilistener;

    /**
     * main method for silly dashboard
     */
	public static void main(String[] args) {
		raspilistener = new RaspiListener();
		raspinet = new RaspiNetworker();
		
		raspinet.addListener(raspilistener);
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

                JFrame f = new JFrame(TITLE);
                f.add(vc);
                vc.setPreferredSize(new Dimension(WIDTH, HEIGHT));
                f.pack();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                pipe.play();
                f.setVisible(true);
            }
        });
        
		raspinet.send((new PortMessage(PORT, iso, shutterspeed)).generateMessage());
	}
}
