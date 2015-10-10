package day03;

/**
 * 中断异常
 * 
 * @author Administrator
 * 
 */
public class TestThreadDemo10 {
	public static void main(String[] args) {
		final Thread lin = new Thread() {
			@Override
			public void run() {
				System.out.println("林：刚美容完，睡觉吧");
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException e) {
					System.out.println("林：干嘛呢！干嘛呢！干嘛呢！都破相了！");
				}
			}

		};
		/**
		 * 黄宏用于中断线程的线程
		 */
		Thread huang = new Thread() {
			@Override
			public void run() {
				System.out.println("黄：开始砸墙！");
				for (int i = 0; i < 10; i++) {
					System.out.println("黄：80!");
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
				System.out.println("黄：搞定");
				lin.interrupt();// 中断这个线程
			}
		};
		lin.start();
		huang.start();
		
	}
}
