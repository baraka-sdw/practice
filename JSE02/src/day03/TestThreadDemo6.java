package day03;
/**
 * 线程优先级
 * 线程优先级有10个级别 1-10
 * 1最小，有常量对应       MIN_PRIORITY
 * 10最大，有常量对应     MAX_PRIORITY
 * 5为默认的优先级，也有常量对应  NORM_PRIORITY
 * 理论上优先级越高的线程获取时间片的数量越多
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
