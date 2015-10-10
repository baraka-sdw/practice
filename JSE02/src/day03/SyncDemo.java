package day03;
/**
 * �̰߳�ȫ����
 * @author Administrator
 * ����
 * ͬ����Ҫ����ͬ��Ч�������߳��жϵ�ͬ�������������ͬһ��
 *
 */
public class SyncDemo {

	public static void main(String[] args) {
		final Table table=new Table();
		Thread t1=new Thread(){

			@Override
			public void run() {
				while(true){
					System.out.println(getName()+":"+table.getBean());
					Thread.yield();
				}
			}
			
		};
		Thread t2=new Thread(){
			@Override
			public void run() {
				while(true){
					System.out.println(getName()+":"+table.getBean());
					Thread.yield();
				}
			}
			
		};
		
		t1.start();
		t2.start();

	}

}

class Table{
	//��������20������
	private int beans=20;
	//��������ȥһ������
//	public synchronized int getBean(){
//		if(beans==0){
//			throw new RuntimeException("û�ж�����!");
//		}
	public  int getBean(){
		synchronized(this) {
			if(beans==0){
				throw new RuntimeException("û�ж�����!");
			}
		}
		Thread.yield();
		return beans--;
	}
}