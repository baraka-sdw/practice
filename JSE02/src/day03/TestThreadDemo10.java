package day03;

/**
 * �ж��쳣
 * 
 * @author Administrator
 * 
 */
public class TestThreadDemo10 {
	public static void main(String[] args) {
		final Thread lin = new Thread() {
			@Override
			public void run() {
				System.out.println("�֣��������꣬˯����");
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException e) {
					System.out.println("�֣������أ������أ������أ��������ˣ�");
				}
			}

		};
		/**
		 * �ƺ������ж��̵߳��߳�
		 */
		Thread huang = new Thread() {
			@Override
			public void run() {
				System.out.println("�ƣ���ʼ��ǽ��");
				for (int i = 0; i < 10; i++) {
					System.out.println("�ƣ�80!");
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
				System.out.println("�ƣ��㶨");
				lin.interrupt();// �ж�����߳�
			}
		};
		lin.start();
		huang.start();
		
	}
}
