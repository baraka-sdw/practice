package day03;
/**
 * ��ȡ�߳���Ϣ����ط���
 * @author Administrator
 *
 */
public class TestThreadDemo5 {
	public static void main(String[] args) {
		//��ȡ����main�������߳�
		Thread main=Thread.currentThread();
		System.out.println("id:"+main.getId());
		System.out.println("name:"+main.getName());
		System.out.println("���ȼ�:"+main.getPriority());
		System.out.println("״̬:"+main.getState());
		System.out.println("�Ƿ�:"+main.isAlive());
		System.out.println("�Ƿ�Ϊ�ػ�����:"+main.isDaemon());
		System.out.println("�Ƿ��ж�:"+main.isInterrupted());
	}
}
