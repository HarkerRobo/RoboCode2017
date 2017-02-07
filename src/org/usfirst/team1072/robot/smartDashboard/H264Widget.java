///**
// * 
// */
//package org.usfirst.team1072.robot.smartDashboard;
//
//import java.awt.Graphics;
//
//import org.freedesktop.gstreamer.Bin;
//import org.freedesktop.gstreamer.Gst;
//import org.freedesktop.gstreamer.Pipeline;
//
//import edu.wpi.first.smartdashboard.gui.StaticWidget;
//import edu.wpi.first.smartdashboard.properties.Property;
//import edu.wpi.first.wpilibj.Sendable;
//import edu.wpi.first.wpilibj.tables.ITable;
//
///**
// * @author joelmanning
// *
// */
//public class H264Widget extends StaticWidget implements Sendable {
//	
//	private Pipeline pipe;
//	
//	public H264Widget(){
//
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.wpi.first.smartdashboard.properties.PropertyHolder#propertyChanged(edu.wpi.first.smartdashboard.properties.Property)
//	 */
//	@Override
//	public void propertyChanged(Property arg0) {
//		System.out.println("Property changed?");
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.wpi.first.smartdashboard.gui.DisplayElement#init()
//	 */
//	@Override
//	public void init() {
//		Gst.init("DashboardStreamer", new String[0]);
//		VideoComponent vc = new VideoComponent();
//        Bin bin = Bin.launch("udpsrc port=5001 ! application/x-rtp, payload=96 ! rtph264depay ! avdec_h264 ! videoconvert", true);
//        pipe = new Pipeline();
//        pipe.addMany(bin, vc.getElement());
//        Pipeline.linkMany(bin, vc.getElement());           
//        add(vc);
//        pipe.play();
//	}
//
//	/* (non-Javadoc)
//	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
//	 */
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//	}
//
//	@Override
//	public void initTable(ITable subtable) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public ITable getTable() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getSmartDashboardType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//}
