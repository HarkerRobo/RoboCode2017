package org.usfirst.frc.team1072.sillyDashboard;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.freedesktop.gstreamer.Bin;
import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;

/**
 *
 * @author Neil C Smith (http://neilcsmith.net)
 */
public class SillyDashboard extends JFrame {

    /**
     * @param args the command line arguments
     */
    
	private Pipeline pipe;
	private JButton button;
	private JPanel panel;
    
	public SillyDashboard(){
		
		//Video networking stuff
        SimpleVideoComponent vc = new SimpleVideoComponent();
        Bin bin = Bin.launch("udpsrc port=5001 ! application/x-rtp, payload=96 ! rtph264depay ! avdec_h264 ! videoconvert", true);
        pipe = new Pipeline();
        pipe.addMany(bin, vc.getElement());
        Pipeline.linkMany(bin, vc.getElement());
        vc.setPreferredSize(new Dimension(640, 480));

        //JButton
        button = new JButton("Refresh");
        button.setSize(new Dimension(150, 75));

        //JPanel
        panel = new JPanel();
        add(panel);
        panel.add(vc);
        panel.add(button);
        pack();
        
        //JButton Listener
        button.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
                Bin bin = Bin.launch("udpsrc port=5001 ! application/x-rtp, payload=96 ! rtph264depay ! avdec_h264 ! videoconvert", true);
                pipe = new Pipeline();
                pipe.addMany(bin, vc.getElement());
                Pipeline.linkMany(bin, vc.getElement());
        	}
        });
        
        //Start stuff
        pipe.play();
        button.setVisible(true);
        panel.setVisible(true);
        setVisible(true);
	}
	
    public static void main(String[] args) {
    	SillyDashboard sdboard = new SillyDashboard();
    }

}
