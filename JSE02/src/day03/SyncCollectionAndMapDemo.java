package day03;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 转换线程安全的集合和Map
 * @author Administrator
 *
 */
public class SyncCollectionAndMapDemo {

	public static void main(String[] args) {
		//List集合
		List<String> list=new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("c");

		//转换为线程安全的List集合
		list=Collections.synchronizedList(list);
		/**
		 * 能保证：对集合元素进行操作的方法都是同步的且互斥的，保证了线程安全
		 * 注意：在遍历的过程中，依然可以增删元素
		 * 解决办法：对遍历的代码片段加锁，锁的是这个集合的对象
		 */
		System.out.println(list);
		synchronized (list) {
			Iterator<String> it=list.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
				
			}
		}
		Set<String> set=new HashSet<String>();
		set.add("a");
		set.add("a");
		set.add("a");
//将set集合转换为线程安全的
		set=Collections.synchronizedSet(set);
		System.out.println(set);
 
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("张三",100);
		//将Map转换为一个线程安全的
		map=Collections.synchronizedMap(map);
		System.out.println(map);
	}

}
