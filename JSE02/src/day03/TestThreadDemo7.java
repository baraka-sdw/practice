package day03;
/** 
 * ǰ̨�������̨����
 * @author Administrator
 *
 */
public class TestThreadDemo7 {
	public static void main(String[] args) {
		/**
		 * rose �����ߣ�ǰ̨����
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
				System.out.println("��Ч����ͨ");
				
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
		//���ú�̨�̹߳���Ҫ��start����֮ǰ
		jack.setDaemon(true);
//		rose.start();
		jack.start();
		//������仰
//		while(true);
	}

}
