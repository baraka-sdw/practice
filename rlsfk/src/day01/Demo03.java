package day01;

import java.util.Timer;
import java.util.TimerTask;

public class Demo03 {
public static void main(String[] args) {
	class MyTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(" ?");
		}
		
	};
	final Timer timer=new Timer();
	timer.schedule(new MyTask(),1000,50);
	TimerTask overTask=new TimerTask(){

		@Override
		public void run() {
			timer.cancel();
		}
		
	
	};
	timer.schedule(overTask,1000*10);
}
}
