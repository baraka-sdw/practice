package day03;
/**
 * 
 * @author Administrator
 *
 */
public class TestThreadDemo9 {
	//ͼƬ�Ƿ���������
	private static boolean isFinish;
	public static void main(String[] args) {
		final Thread download=new Thread(){
			public void run(){
				System.out.println("download:��ʼ����ͼƬ");
			for (int i = 0; i <= 100; i++) {
				System.out.println("�Ѿ�������"+i+"%");
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			  }
			System.out.println("download:ͼƬ�������");
			isFinish=true;
			}
		};
		  Thread show=new Thread(){
			@Override
			public void run() {
				System.out.println("show����ʾ������Ϣ");
				
				//����Ӧ���ȴ�download�̹߳������
				try {
					download.join();
					System.out.println("show:��ʾͼƬ");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(!isFinish){
					System.out.println("show:��ʾͼƬʧ��");
				}else{
					System.out.println("show:��ʾͼƬ���");
				}
			}
		};
		download.start();
		show.start();
	
	}

}
