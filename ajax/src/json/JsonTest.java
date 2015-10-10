package json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import entity.Friend;

public class JsonTest {
	// JSON����ת��Ϊ�ַ���
	public static void test1() {
		Friend f = new Friend();
		f.setName("����");
		f.setAge(100);
		JSONObject jo = JSONObject.fromObject(f);
		String joString = jo.toString();
		System.out.println(joString);
	}

	// JSON����ת��Ϊ�ַ���
	public static void test2() {
		List<Friend> ls = new ArrayList<Friend>();
		for (int i = 0; i < 3; i++) {
			Friend f = new Friend();
			f.setName("����" + i );
			f.setAge(10+i * 10);
			ls.add(f);
		}
		JSONArray ja = JSONArray.fromObject(ls);
		String jaString = ja.toString();
		System.out.println(jaString);
	}

	// JSON�ַ���ת��Ϊ����
	public static void test3() {
		String Stringjo = "{\"name\":\"����\",\"age\":100}";
		JSONObject jo = JSONObject.fromObject(Stringjo);
		Friend f = (Friend) JSONObject.toBean(jo, Friend.class);
		System.out.println(f);
	}

	// JSON�ַ���ת��Ϊ����
	@SuppressWarnings("unchecked")
	public static void test4() {
        String sringja="[{\"name\":\"����\",\"age\":100},{\"name\":\"Ǯ��\",\"age\":100}]";
        JSONArray ja=JSONArray.fromObject(sringja);
        List<Friend> ls=(List<Friend>) JSONArray.toCollection(ja, Friend.class);//ֻ����collection
        for(Friend f:ls){
        	System.out.println(f);
        }
	}
	public static void main(String[] args) {
		System.out.println("JSON����ת��Ϊ�ַ���");
		test1();
		System.out.println("JSON����ת��Ϊ�ַ���");
		test2();
		System.out.println("JSON�ַ���ת��Ϊ����");
		test3();
		System.out.println("JSON�ַ���ת��Ϊ����");
		test4();
	}
}
