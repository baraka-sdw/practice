package day03;
/**
 * synchronizedҲ���л���Ч��
 * @author Administrator
 * ͬ���飺�sСͬ����������ߎհlЧ��
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
