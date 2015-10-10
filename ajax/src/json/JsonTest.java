package json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import entity.Friend;

public class JsonTest {
	// JSON对象转换为字符串
	public static void test1() {
		Friend f = new Friend();
		f.setName("张三");
		f.setAge(100);
		JSONObject jo = JSONObject.fromObject(f);
		String joString = jo.toString();
		System.out.println(joString);
	}

	// JSON数组转换为字符串
	public static void test2() {
		List<Friend> ls = new ArrayList<Friend>();
		for (int i = 0; i < 3; i++) {
			Friend f = new Friend();
			f.setName("李四" + i );
			f.setAge(10+i * 10);
			ls.add(f);
		}
		JSONArray ja = JSONArray.fromObject(ls);
		String jaString = ja.toString();
		System.out.println(jaString);
	}

	// JSON字符串转换为对象
	public static void test3() {
		String Stringjo = "{\"name\":\"王五\",\"age\":100}";
		JSONObject jo = JSONObject.fromObject(Stringjo);
		Friend f = (Friend) JSONObject.toBean(jo, Friend.class);
		System.out.println(f);
	}

	// JSON字符串转换为数组
	@SuppressWarnings("unchecked")
	public static void test4() {
        String sringja="[{\"name\":\"赵六\",\"age\":100},{\"name\":\"钱七\",\"age\":100}]";
        JSONArray ja=JSONArray.fromObject(sringja);
        List<Friend> ls=(List<Friend>) JSONArray.toCollection(ja, Friend.class);//只能用collection
        for(Friend f:ls){
        	System.out.println(f);
        }
	}
	public static void main(String[] args) {
		System.out.println("JSON对象转换为字符串");
		test1();
		System.out.println("JSON数组转换为字符串");
		test2();
		System.out.println("JSON字符串转换为对象");
		test3();
		System.out.println("JSON字符串转换为数组");
		test4();
	}
}
