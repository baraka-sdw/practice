package day03;
/**
 * 获取线程信息的相关方法
 * @author Administrator
 *
 */
public class TestThreadDemo5 {
	public static void main(String[] args) {
		//获取调用main方法的线程
		Thread main=Thread.currentThread();
		System.out.println("id:"+main.getId());
		System.out.println("name:"+main.getName());
		System.out.println("优先级:"+main.getPriority());
		System.out.println("状态:"+main.getState());
		System.out.println("是否活动:"+main.isAlive());
		System.out.println("是否为守护进程:"+main.isDaemon());
		System.out.println("是否被中断:"+main.isInterrupted());
	}
}
