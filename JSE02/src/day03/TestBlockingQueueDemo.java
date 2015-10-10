package day03;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * ˫�������
 * @author Administrator
 *
 */
public class TestBlockingQueueDemo {

	public static void main(String[] args) {
		/**
		 * ˫������У�����һ���̶����ȵģ�������10��Ԫ��
		 * �ö����ǵ���ģ���ѭ�Ƚ��ȳ�ԭ��
		 */
		final BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(10);
		System.out.println(queue.getClass().getName());
//		A a=(A) new TestBlockingQueueDemo();
//		System.out.println(a.getClass().getName());
		/**
		 * ˫����˫�˶��У��뵥ǰ���������ڣ��������˶����Խ�����
		 */
//		BlockingDeque<Integer>deque=new LinkedBlockingDeque<Integer>();
		Thread offThread=new Thread(){
			@Override
			public void run() {
				
		
		for (int i = 0; i < 20; i++) {
			//�ɹ���Ӷ��з���true
			boolean tf=false;
			//�ֲ�����ʹ��ǰ�����ʼ��
			try {
				/**
				 * �÷���������������һ���ӳ�ʱ��
				 * �ڳ�ʱ��Ԫ�ػ�û�б�������вŷ���false
				 */
				tf = queue.offer(i,5,TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("���Ԫ��"+i+":"+tf);
		         }
		    }
		};
		offThread.start();
		//�Ӷ�����ȡ��Ԫ�ص��߳�
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
					System.out.println("ȡ����Ԫ����"+num);
//					int a = (Integer)null;
				}
			}
			
		};
		try {
			//����
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
