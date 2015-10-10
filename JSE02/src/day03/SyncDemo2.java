package day03;
/**
 * synchronized也具有互斥效果
 * @author Administrator
 * 同步块：縮小同步範圍，提高幷發效率
 *
 */
public class SyncDemo2 {

	public static void main(String[] args) {
		Thread t=new Thread(){

			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		};
	}

}
