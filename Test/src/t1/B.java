package t1;

import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B {

	public B() {
		super();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		A a=F.a();
		D d=a;
       Object o=new B();
       int f=1;
      String b="1";
      String c="1";
      String e=new String("1");
       if(b.equals(b)){
    	   System.out.println("true");
       }else{
    	   System.out.println("false");
       }
       String str="ÄãºÃ";
       System.out.println(str.length());//unicode
       System.out.println(str.getBytes("gbk").length);
       System.out.println(str.getBytes("utf-8").length);
       System.out.println(str.getBytes("iso-8859-1").length);
   }
	public void B(){
		
	}
}