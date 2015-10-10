package day03;
/*
 * �����̳߳�
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestThreadPoolDemo {

	public static void main(String[] args) {
		//����һ������10���̵߳��̳߳�
		ExecutorService threadpool=Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			Hander hander=new Hander();
			threadpool.execute(hander);

		}
		System.out.println("����ָ�����");

	}

}
//�߳�Ҫִ�е�����
class Hander implements Runnable{

	@Override
	public void run() {
		//��ȡ���е�ǰ������߳���
		String name=Thread.currentThread().getName();
		System.out.println("���е�ǰ������߳��ǣ�"+name);
		for (int i = 0; i < 10; i++) {
			System.out.println(name+":"+i);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name+"�������");
	}
	
}