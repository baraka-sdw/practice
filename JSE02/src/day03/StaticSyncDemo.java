package day03;
/**
 * 静态方法锁
 * @author Administrator
 *
 */
public class StaticSyncDemo {

	public static void main(String[] args) {
		final StaticDemo demo=new StaticDemo();
		final StaticDemo demo1=new StaticDemo();
		Thread t1=new Thread(){
			public void run() {
				demo.methodA();
				System.out.println(getClass().getName());
			}
		};
		Thread t2=new Thread(){
			public void run() {
				demo1.methodA();
				System.out.println(getClass().getName());
			}
		};
		t1.start();
		t2.start();

	}

}
class StaticDemo{
	public synchronized static void methodA(){
		String name=Thread.currentThread().getName();
		System.out.println(name+"调用了methodA方法");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		System.out.println(name+"调用了methodA方法完毕");
	}
	public synchronized static void methodB(){
		String name=Thread.currentThread().getName();
		System.out.println(name+"调用了methodB方法");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		System.out.println(name+"调用了methodB方法完毕");
	}
}