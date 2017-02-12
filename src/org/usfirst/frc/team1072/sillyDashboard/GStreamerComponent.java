package org.usfirst.frc.team1072.sillyDashboard;

import java.awt.Dimension;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.freedesktop.gstreamer.Bin;
import org.freedesktop.gstreamer.Pipeline;
import org.json.JSONObject;

public class GStreamerComponent {
	public static SimpleVideoComponent create(RaspiNetworker raspinet, int port, int width, int height) {
		try {
			raspinet.send(createStartStreamMessage(500, 6000, port));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		SimpleVideoComponent vc = new SimpleVideoComponent();
        Bin bin = Bin.launch("udpsrc port=" + port + " ! application/x-rtp, payload=96 ! rtph264depay ! avdec_h264 ! videoconvert", true);
        Pipeline pipe = new Pipeline();
        pipe.addMany(bin, vc.getElement());
        Pipeline.linkMany(bin, vc.getElement()); 
        
        pipe.play();
        vc.setPreferredSize(new Dimension(width, height));
        return vc;
	}
	
	public static JSONObject createStartStreamMessage(int iso, int shutterspeed, int port)
	{
		try {
			JSONObject message = new JSONObject();
			message.put("type", "start");
			message.put("port", port);
			message.put("host", InetAddress.getLocalHost().getHostAddress());
			message.put("iso", iso);
			message.put("shutterspeed", shutterspeed);
			return message;
		} catch (UnknownHostException e) {
			System.out.println(e);
			return null;
		}
	}
}
