package day03;
/**
 * ��ȡ��ǰ����Ƭ�ε��߳�
 * @author Administrator
 *
 */
public class TestThreadDemo4 {

	public static void main(String[] args) {
		/**
		 * ��������������ʱ��OS������һ���߳�����JVM
		 * ������������ᴴ��һ���̣߳�������߳�������main����
		 */
		//�����ȡ�ľ�������main�������߳�
		Thread t=Thread.currentThread();
		System.out.println("����main�������߳��ǣ�"+t);
		
		//����һ���߳�
		Thread t1=new Thread(){

			@Override
			public void run() {
				System.out.println("t1�߳��ǣ�"+Thread.currentThread());
				testCurrent();
			}
			
		};
            t1.start();
            //�߳��е���
            testCurrent();
	}
	/**
	 * ������õ�ǰ�������߳�
	 * @return 
	 */
	public static void testCurrent(){
		System.out.println("����testCurrent�������̣߳�"+Thread.currentThread());
	}

}
