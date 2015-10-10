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
 * ת���̰߳�ȫ�ļ��Ϻ�Map
 * @author Administrator
 *
 */
public class SyncCollectionAndMapDemo {

	public static void main(String[] args) {
		//List����
		List<String> list=new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("c");

		//ת��Ϊ�̰߳�ȫ��List����
		list=Collections.synchronizedList(list);
		/**
		 * �ܱ�֤���Լ���Ԫ�ؽ��в����ķ�������ͬ�����һ���ģ���֤���̰߳�ȫ
		 * ע�⣺�ڱ����Ĺ����У���Ȼ������ɾԪ��
		 * ����취���Ա����Ĵ���Ƭ�μ�����������������ϵĶ���
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
//��set����ת��Ϊ�̰߳�ȫ��
		set=Collections.synchronizedSet(set);
		System.out.println(set);
 
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("����",100);
		//��Mapת��Ϊһ���̰߳�ȫ��
		map=Collections.synchronizedMap(map);
		System.out.println(map);
	}

}
