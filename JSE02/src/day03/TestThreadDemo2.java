package day03;
/**
 * �ڶ��ִ����̵߳ķ�ʽ
 * ʵ��Runnable��ڣ������������߳�����
 * @author Administrator
 *
 */
public class TestThreadDemo2 {

	public static void main(String[] args) {
		Runnable r1=new MyRunnable();
		Runnable r2=new MyRunnable1();
		
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t1.start();		
		t2.start();

		
		



	}

}
class MyRunnable implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Are you ok ?");
			
		}
		
	}
	
}
class MyRunnable1 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Are you ok ?");
			
		}
		
	}
	
}
