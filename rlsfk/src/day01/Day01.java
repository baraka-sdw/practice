
package day01;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Day01 extends JFrame{
	public static void main(String[] args) {
		LoginFrame loginframe=new LoginFrame();
		loginframe.setVisible(true);
		Day01 frame=new Day01();
		frame.setVisible(true);
       frame.setSize(200,200);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel();
		panel.setBackground(Color.cyan);
		frame.add(panel);
		
		
	}
	
		
	}
  class LoginFrame extends JFrame{
	public LoginFrame(){
		this.setSize(200,200);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel();
		panel.setBackground(Color.cyan);
		this.add(panel);
		JLabel label=new JLabel("ÐÕÃû");
		panel.add(label);
		JTextField name=new JTextField(20);
		JButton login1=new JButton("µÇÂ½");
		panel.add(label);
		panel.add(name);
		panel.add(login1);
		
	}
//	 public static void main(String[] args) {
//		System.out.println();
//	}
//	
//	
//	
//class Outer{
//		private int time=101;
//		private Inner inner;
//		
//
//		Outer(int time){
//		this .time=time;
//		inner=this.new Inner();
//		inner.timeInc();
//		inner.show();
//		}
//
//		public void printTime(){
//			System.out.println(time);
//		}
//		class Inner{     
//			
//			public void timeInc(){
//				time++;
//			}
//			void show(){
//				
//			}
//
//		
//		}
//	}

}


//	public static void main(String[] args) {
//	Foo foo=new Foo(){};
//	Action action=new Action(){
//		public void excute(){
//			System.out.println("?");
//		}
//	};
//	
//}
//interface Foo{}
//interface Action{void excute();}

 
