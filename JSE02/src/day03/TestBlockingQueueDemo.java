package day03;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 双缓冲队列
 * @author Administrator
 *
 */
public class TestBlockingQueueDemo {

	public static void main(String[] args) {
		/**
		 * 双缓冲队列，创建一个固定长度的，里面存放10个元素
		 * 该队列是单向的，遵循先进先出原则
		 */
		final BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(10);
		System.out.println(queue.getClass().getName());
//		A a=(A) new TestBlockingQueueDemo();
//		System.out.println(a.getClass().getName());
		/**
		 * 双缓冲双端队列，与单前队列区在于，队列两端都可以进出对
		 */
//		BlockingDeque<Integer>deque=new LinkedBlockingDeque<Integer>();
		Thread offThread=new Thread(){
			@Override
			public void run() {
				
		
		for (int i = 0; i < 20; i++) {
			//成功添加队列返回true
			boolean tf=false;
			//局部变量使用前必须初始化
			try {
				/**
				 * 该方法允许我们设置一个延迟时间
				 * 在超时后元素还没有被放入队列才返回false
				 */
				tf = queue.offer(i,5,TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("添加元素"+i+":"+tf);
		         }
		    }
		};
		offThread.start();
		//从队列中取出元素的线程
		Thread pllThread=new Thread(){
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					Integer num=0;
					try {
						num = queue.poll(5,TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("取出的元素是"+num);
//					int a = (Integer)null;
				}
			}
			
		};
		try {
			//阻塞
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pllThread.start();
		

	}
 }
   class A extends TestBlockingQueueDemo{
	   private void A() {
		   

	}
   }
