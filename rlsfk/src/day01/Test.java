package day01;

import javax.swing.JFrame;

public class Test {
	 public static void main(String[] args) {
		
			Out o=new Out();
			o.printTime();
		}
		
		
		
	

}
class Out{
	private int time;
	private In inner;
	

     public void  Out(){
	this .time=time;
	inner=this.new In();
	inner.timeInc();
	}

	public void printTime(){
		System.out.println(time);
	}
	class In{     
		public void timeInc(){
			time++;
		}
		

	
	}
}