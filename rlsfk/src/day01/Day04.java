package day01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Day04 extends JFrame {
	private int cubeX=0,cubeY=0;
	int max;
	int value;
	JTextField text;
	Timer timer;
	                                                                                                                                                                             
	public Day04(){
		setSize(350,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
//		CubePanel panel=new CubePanel();
//		add(panel);
		 JProgressBar pb=new JProgressBar();
 		pb.setMaximum(100);
 		int max=pb.getMaximum();
 		pb.setLocation(10,20);
 		pb.setValue(value);
    	pb.setStringPainted(true);
    	pb.setIndeterminate(true);
    	
    	repaint();
 		add(pb);
 		timer=new Timer();
		TimerTask dropTask=new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				value+=1;
				
			}};
			
			timer.schedule(dropTask, 1000,500);
			
		
		
		
	}
      public static void main(String[] args) {
    	Day04 cubeframe=new Day04();
    	cubeframe.setVisible(true);
    	
    	    
}
	
	        
      
//	private class  CubePanel extends JPanel {
//    	 public void paint(Graphics g){
//    		 g.setColor(new Color(0xfff000));
//    		 g.fillRect(0, 0, getWidth(), getHeight());
//    		 g.setColor(new Color(0xffff0));
//    		 g.fillRoundRect(cubeX+10,cubeY+10,30,30,5,5) ;
//    		 g.drawRoundRect(0,0,30,30,5,5);
//    	    g.fill3DRect(35, 35, 20, 20, true);
//    	    g.draw3DRect(35, 35, 20, 20, true);
//    	    Polygon p=new Polygon();
//    	    p.addPoint(80, 40);
//    	    p.addPoint(80,60);
//    	    p.addPoint(100, 50);
//    	    p.addPoint(100,20);
//    	    p.addPoint(300,200);
//    	    g.drawPolygon(p);
//    	    g.fillOval(90, 50, 30, 40);
//    	    JDialog d=new JDialog();
//    	    d.setSize(200,200);
//    	    d.setVisible(true);
//    	    d.setLocation(100,100);
//    	    g.setColor(new Color(0xffff0));
//    	   
    	    
    		
//    	    }
//    }
	
//	public void run() {
//		while(value<100){
//			value+=1;
//			repaint();
//			
//		}
//		try{
//			Thread.sleep(1000);
//			
//		}catch(InterruptedException e){
//			
//			e.printStackTrace();
//			
//			
//		}
		
//	}
	
      }

