package day05;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {
	/**
	 * 根据给定的xml文件名将该xml文件中的所有员工信息存入
	 * 一个List集合中并返回
	 */
  public static List<Emp> getAllEmp(String xmlFileName ){
	  List<Emp>  list=new ArrayList<Emp>();
	  try {
		/**
		 * 解析XML步骤：
		 * 1:创建SAXReader
		 * 2:创建File对象来描述文件
		 * 3：使用SAXReader读取文件（读取的过程），并返回Document对象。其封装了整棵树
		 * 4：通过Document获取根元素（根标签）
		 * 5：根据xml结构获取不同节点以及对应的信息
		 */
		  //1
		  SAXReader reader=new SAXReader();
		  //2
		  File file=new File(xmlFileName);
		  //3
		  Document document=reader.read(file);
		  //4
		  Element root=document.getRootElement();
		  //5
		  List<Element> elements=root.elements();
		  //遍历每一个emp标签
		  for(Element empEle:elements){
			  Attribute idAttr=empEle.attribute("id");
			  int id=Integer.parseInt(idAttr.getValue());
			  String name=empEle.elementText("name");
			  String gender=empEle.elementText("gender");
			  int salary=Integer.parseInt(empEle.elementText("salary"));
			  int age=Integer.parseInt(empEle.elementText("age"));
			  /**
			   * 将解析出的一个用户信息存入一个Emp实例中
			   * 并放入集合
			   */
			  Emp emp =new Emp(id,name,age,gender,salary);
			  list.add(emp);
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
  }
  /**
   * 将给定集合中所有员工信息以xml形式写入给定文件中
   * 
   * 使用DOM写xml原则：
   * 组织好整棵树
   * @param list
   * @param filename
   */
  public static void writeXmlToFile(List<Emp> list,String filename){
	  try {
		  /*
		   * 1:首先创建Document来描述树
		   * 2：根据结构添加元素来组建树的结构
		   * 3：将树写入文件
		   */
		  //1
		Document doc=DocumentHelper.createDocument();
		//2
		/*
		 * Document的addElement（String name）方法
		 * 用于添加根元素
		 * 返回值为element表示添加的这个根元素
		 * addElement方法只能调用一次
		 * 因为根只有一个
		 */
		Element root=doc.addElement("list");
		//循环list集合
		for(Emp emp: list){
			//向根元素中添加子元素emp
			Element empEle=root.addElement("emp");
			/**
			 * Element的方法：
			 * addAttribute(String name,String value)
			 * 用于向当前元素中追加属性
			 * name：属性名
			 * value：属性值
			 */
			empEle.addAttribute("id", emp.getId()+" ");
			//向emp子元素中追加子元素name
			Element nameEle=empEle.addElement("name");
			/*
			 * Element的addText(String str)方法
			 * 向当前元素中追加文本信息（前后标记中间）
			 * <name>addText追加的文本</name>
			 */
			nameEle.addText(emp.getName());
		    empEle.addElement("age").addText(emp.getAge()+"");//年龄
		    empEle.addElement("gender").addText(emp.getGender());//性别
		    empEle.addElement("salary").addText(emp.getSalary()+"");//工资
			//3
		    FileOutputStream fos =new FileOutputStream(filename);
		    /*
		     * XMLWriter是一个高级流，该流可以简化我们写出繁琐的XML数据结构
		     */
		    XMLWriter xmlWriter =new XMLWriter(fos);
		    
		    xmlWriter.write(doc);
		    //写完毕后关闭流
		    xmlWriter.close();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  /**
   * 测试XPath
   * @param args
   */
  public static void testXPath(){
	  try {
		SAXReader reader =new SAXReader();
		File file=new File("book.xml");
		Document doc=reader.read(file);
		/**
		 * List selectNodes(String xpath)
		 * dom4j的Document对Xpath的支持方法
		 * 传入路径表达式，获取对应节点信息
		 */
		List list =doc.selectNodes("/bookstore/book[price>35]/title");
		System.out.println("检索出了："+list.size()+"项");
		for(Object o:list){
			Attribute element =(Attribute) o;
			System.out.println(element.getValue());
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  
  }
  
  public static void main(String[] args) {
//	List<Emp> list=getAllEmp("MyXml.xml");
//	for(Emp emp :list){
//		System.out.println(emp);
//	}
//	  List<Emp> list =new ArrayList<Emp>();
//	  Emp emp1=new Emp(1,"张三",22,"男",5000);
//	  Emp emp2=new Emp(2,"李四",22,"男",5000);
//	  list.add(emp1);
//	  list.add(emp2);
//	  writeXmlToFile(list, "employee.xml");
	  testXPath();
}
}
