package day03;
/**
 * 使用匿名内部类
 * @author Administrator
 *
 */
public class TestThreadDemo3 {
	public static void main(String[] args) throws InterruptedException {
		//1继承Thread的方式
		Thread t1= new Thread(){
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("Are you ok");
				}
			}
		};
		//2 使用Runnable的方式
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
