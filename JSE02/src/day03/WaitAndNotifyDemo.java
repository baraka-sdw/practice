package day03;
/**
 * wait与notify方法
 * 这两个方法是定义在object上的
 * @author Administrator
 *
 */
public class WaitAndNotifyDemo {
	//表示图片是否下载完成
	public static boolean isFinish=false;


	public static void main(String[] args) throws InterruptedException {
		//用一个对象测试Wait与notify
		final Object obj=new Object();
		//下载线程
		final Thread download=new Thread(){
			@Override
			public void run() {
				System.out.println("download:开始下载图片");
				for (int i = 0; i <=10; i++) {
					System.out.println("download:正在下载图片"+i*10+"%");
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("download:图片下载完毕");
				isFinish=true;
				synchronized (obj) {
					obj.notify();
				}
				//通知显示进程可以工作了
				System.out.println("download:开始下载附件");
				for (int i = 0; i <=10; i++) {
					System.out.println("download:正在下载附件"+i*10+"%");
					try {
						
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread show=new Thread(){
			@Override
			public void run() {
				System.out.println("show:开始显示图片");
//					try {
//						download.join();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					try {
						//在obj对象上等待
						synchronized (obj) {
							obj.wait();
						}
						/**
						 * wait方法要求：
						 * 调用哪个方法的wait方法就要将该对象加锁
						 */  
						
						if(!isFinish){
							System.out.println("show:显示图片失败");
						}else{
							System.out.println("show:显示图片成功");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				
			}
		};
		download.start();
	    show.start();
			
		
		
		
	}
}
