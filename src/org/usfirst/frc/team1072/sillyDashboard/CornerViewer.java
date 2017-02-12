package org.usfirst.frc.team1072.sillyDashboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.usfirst.frc.team1072.sillyDashboard.RaspiNetworker.JSONListener;

public class CornerViewer extends JPanel {

	public static final int CIRCLE_DIAMETER = 10;
	public static final int STROKE_WIDTH = 3;
	
    private final Lock bufferLock = new ReentrantLock();
    private int[][][] points = new int[2][4][2];
    private int numRects = 0;
	
	public CornerViewer (RaspiNetworker raspinet) {
		setOpaque(false);
		setBackground(new Color(0,0,0,0));
		
		raspinet.addListener(new JSONListener() {

			@Override
			public void recieve(JSONObject obj) {
				if (!bufferLock.tryLock()) {
	                return;
	            }
				
				try {
					JSONArray results = obj.getJSONArray("corners");
					numRects = results.length();
					updatePoints(results);
					for (int i = 0; i < numRects; i++) {
						orderPoints(points[i]);
					}
				} finally {
					bufferLock.unlock();
				}
			}
			
		});
	}
	
	private void updatePoints(JSONArray results) {
		for(int i = 0; i < results.length(); i++) {
			JSONArray arr = results.getJSONArray(i);
			for(int j = 0; j < arr.length(); j++) {
                JSONArray arr2 = arr.getJSONArray(j);
                for(int k = 0; k < arr2.length(); k++) {
                   points[i][j][k] = arr2.getInt(k);
                }
			}
		}
	}
	
	private void swapPoints(int[][] points, int index1, int index2) {
		int tempX = points[index1][0];
		int tempY = points[index1][1];
		points[index1][0] = points[index2][0];
		points[index1][1] = points[index2][1];
		points[index2][0] = tempX;
		points[index2][1] = tempY;
	}
	
	private void orderPoints(int[][] points) {
		// Very simple pseudo-bubblesort that is very efficient as there are only 4 items to sort
		for (int i = 2; i < 4; i++) {
			int maxIndex = points[0][1] > points[1][1] ? 0 : 1;
			if (points[i][1] < points[maxIndex][1]) {
				swapPoints(points, i, maxIndex);
			}
		}
		// Now the first two elements of points should have the lowest Y values
		// Now sort x in ascending
		if (points[0][0] > points[1][0]) {
			swapPoints(points, 0, 1);
		}
		// Also the last two elemnets should be in descending order
		if (points[2][0] < points[3][0]) {
			swapPoints(points, 2, 3);
		}
	}
	
	public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        
        bufferLock.lock();
        
        g.setColor(Color.BLUE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(STROKE_WIDTH));
        
        for (int[][] rectangle : points) {
        	for (int i = 0; i < rectangle.length; i++) {
        		int[] point = rectangle[i];
        		g.drawOval(point[0]-CIRCLE_DIAMETER/2, point[1]-CIRCLE_DIAMETER/2,
        				CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        		
        		int[] ppoint = (i == 0) ? rectangle[3] : rectangle[i-1];
        		g.drawLine(ppoint[0], ppoint[1], point[0], point[1]);
        	}
        }
        
        bufferLock.unlock();
        
        super.paintComponent(g);
    }
	
}
