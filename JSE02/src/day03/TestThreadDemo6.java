package day03;
/**
 * �߳����ȼ�
 * �߳����ȼ���10������ 1-10
 * 1��С���г�����Ӧ       MIN_PRIORITY
 * 10����г�����Ӧ     MAX_PRIORITY
 * 5ΪĬ�ϵ����ȼ���Ҳ�г�����Ӧ  NORM_PRIORITY
 * ���������ȼ�Խ�ߵ��̻߳�ȡʱ��Ƭ������Խ��
 * @author Administrator
 *
 */
public class TestThreadDemo6 {

	public static void main(String[] args) {
		Thread max=new Thread(){
			@Override
			public void run() {
				for (int i = 0; i <10; i++) {
					System.out.println("max");
				}
			}
			
		};
		Thread min=new Thread(){
			public void run() {
				for (int i = 0; i <10; i++) {
					System.out.println("min");
				}
			}
			
		};
		Thread norm=new Thread(){
			public void run() {
				for (int i = 0; i <10; i++) {
					System.out.println("norm");
				}
			}
			
		};
		max.setPriority(Thread.MAX_PRIORITY);
		min.setPriority(Thread.MIN_PRIORITY);
		norm.setPriority(Thread.NORM_PRIORITY);
		min.start();
		norm.start();
		max.start();

	}

}
