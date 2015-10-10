package day03;
/**
 * wait��notify����
 * �����������Ƕ�����object�ϵ�
 * @author Administrator
 *
 */
public class WaitAndNotifyDemo {
	//��ʾͼƬ�Ƿ��������
	public static boolean isFinish=false;


	public static void main(String[] args) throws InterruptedException {
		//��һ���������Wait��notify
		final Object obj=new Object();
		//�����߳�
		final Thread download=new Thread(){
			@Override
			public void run() {
				System.out.println("download:��ʼ����ͼƬ");
				for (int i = 0; i <=10; i++) {
					System.out.println("download:��������ͼƬ"+i*10+"%");
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("download:ͼƬ�������");
				isFinish=true;
				synchronized (obj) {
					obj.notify();
				}
				//֪ͨ��ʾ���̿��Թ�����
				System.out.println("download:��ʼ���ظ���");
				for (int i = 0; i <=10; i++) {
					System.out.println("download:�������ظ���"+i*10+"%");
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
				System.out.println("show:��ʼ��ʾͼƬ");
//					try {
//						download.join();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					try {
						//��obj�����ϵȴ�
						synchronized (obj) {
							obj.wait();
						}
						/**
						 * wait����Ҫ��
						 * �����ĸ�������wait������Ҫ���ö������
						 */  
						
						if(!isFinish){
							System.out.println("show:��ʾͼƬʧ��");
						}else{
							System.out.println("show:��ʾͼƬ�ɹ�");
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
