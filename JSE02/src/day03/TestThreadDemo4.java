package day03;
/**
 * 获取当前代码片段的线程
 * @author Administrator
 *
 */
public class TestThreadDemo4 {

	public static void main(String[] args) {
		/**
		 * 当程序运行起来时，OS会启动一个线程运行JVM
		 * 而进程启动后会创建一个线程，用这个线程来运行main方法
		 */
		//这里获取的就是运行main方法的线程
		Thread t=Thread.currentThread();
		System.out.println("运行main方法的线程是："+t);
		
		//创建一个线程
		Thread t1=new Thread(){

			@Override
			public void run() {
				System.out.println("t1线程是："+Thread.currentThread());
				testCurrent();
			}
			
		};
            t1.start();
            //线程中调用
            testCurrent();
	}
	/**
	 * 输出调用当前方法的线程
	 * @return 
	 */
	public static void testCurrent(){
		System.out.println("运行testCurrent方法的线程："+Thread.currentThread());
	}

}
