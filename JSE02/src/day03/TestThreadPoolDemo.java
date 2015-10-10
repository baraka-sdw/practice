package day03;
/*
 * 测试线程池
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestThreadPoolDemo {

	public static void main(String[] args) {
		//创建一个含有10个线程的线程池
		ExecutorService threadpool=Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			Hander hander=new Hander();
			threadpool.execute(hander);

		}
		System.out.println("任务指派完毕");

	}

}
//线程要执行的任务
class Hander implements Runnable{

	@Override
	public void run() {
		//获取运行当前任务的线程是
		String name=Thread.currentThread().getName();
		System.out.println("运行当前任务的线程是："+name);
		for (int i = 0; i < 10; i++) {
			System.out.println(name+":"+i);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name+"任务完毕");
	}
	
}