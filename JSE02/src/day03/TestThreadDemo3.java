package day03;
/**
 * ʹ�������ڲ���
 * @author Administrator
 *
 */
public class TestThreadDemo3 {
	public static void main(String[] args) throws InterruptedException {
		//1�̳�Thread�ķ�ʽ
		Thread t1= new Thread(){
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("Are you ok");
				}
			}
		};
		//2 ʹ��Runnable�ķ�ʽ
		Runnable r1=new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("yes I am");
				}
			}
		};
		Thread t2=new Thread(r1);
		t1.start();
		t2.start();
		System.out.println(t2.currentThread());
	}
}
