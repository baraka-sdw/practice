package day03;
/**
 * 
 * @author Administrator
 *
 */
public class TestThreadDemo9 {
	//图片是否下载完了
	private static boolean isFinish;
	public static void main(String[] args) {
		final Thread download=new Thread(){
			public void run(){
				System.out.println("download:开始下载图片");
			for (int i = 0; i <= 100; i++) {
				System.out.println("已经下载了"+i+"%");
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			  }
			System.out.println("download:图片下载完成");
			isFinish=true;
			}
		};
		  Thread show=new Thread(){
			@Override
			public void run() {
				System.out.println("show：显示其他信息");
				
				//这里应当等待download线程工作完毕
				try {
					download.join();
					System.out.println("show:显示图片");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(!isFinish){
					System.out.println("show:显示图片失败");
				}else{
					System.out.println("show:显示图片完毕");
				}
			}
		};
		download.start();
		show.start();
	
	}

}
