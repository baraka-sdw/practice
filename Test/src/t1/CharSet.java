package t1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

public class CharSet {

	public static void main(String[] args) throws IOException {
		   System.out.println(Charset.defaultCharset());
		   String str="���";
		   System.out.println(str);
		   System.out.println(str.length());
	       System.out.println(str.getBytes("gbk").length);
	       System.out.println(str.getBytes("utf-8").length);
	       System.out.println(str.getBytes("iso-8859-1").length);
           RandomAccessFile a=new RandomAccessFile("a.txt","rw");
           String s="���";
           byte[] arr=s.getBytes();//unicode-8 һ������3�ֽ� || gbk һ������2�ֽ�||Ĭ��Ϊgbk
           a.write(arr);
	}

}
