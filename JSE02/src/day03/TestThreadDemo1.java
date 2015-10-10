package day03;
/**
 * 测试第一种创建线程的方式
 * @author Administrator
 *
 */
public class TestThreadDemo1 {

	public static void main(String[] args) {
		MyThread thread1=new MyThread();
		MyThread2 thread2=new MyThread2();
		thread1.start();		
		thread2.start();
	}

}
class MyThread extends Thread{

	@Override
	public void run() {
		for (int i = 0; i <100; i++) {
			System.out.println("who are you");
			
		}
	}
	
}
class MyThread2 extends Thread{

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("It's me");
			
		}
	}
	
}
