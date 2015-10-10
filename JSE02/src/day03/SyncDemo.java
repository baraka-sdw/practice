package day03;
/**
 * 线程安全问题
 * @author Administrator
 * 互斥
 * 同步块要想起到同步效果，多线程判断的同步锁对象必须是同一个
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
	//桌子上有20个豆子
	private int beans=20;
	//从桌子上去一个豆子
//	public synchronized int getBean(){
//		if(beans==0){
//			throw new RuntimeException("没有豆子了!");
//		}
	public  int getBean(){
		synchronized(this) {
			if(beans==0){
				throw new RuntimeException("没有豆子了!");
			}
		}
		Thread.yield();
		return beans--;
	}
}