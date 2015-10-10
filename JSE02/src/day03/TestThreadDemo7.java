package day03;
/** 
 * 前台进程与后台进程
 * @author Administrator
 *
 */
public class TestThreadDemo7 {
	public static void main(String[] args) {
		/**
		 * rose 扮演者：前台进程
		 */
		Thread rose=new Thread(){
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("rose:let me go!");
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("rose: aaaaaaaaaa");
				System.out.println("音效：扑通");
				
			}
			
		};
		Thread jack=new Thread(){
			@Override
			public void run() {
				while(true){
					System.out.println("jack:you jump, i jump!");
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		};
		//设置后台线程工作要在start方法之前
		jack.setDaemon(true);
//		rose.start();
		jack.start();
		//加上这句话
//		while(true);
	}

}
