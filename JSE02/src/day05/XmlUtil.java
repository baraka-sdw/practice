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
	 * ���ݸ�����xml�ļ�������xml�ļ��е�����Ա����Ϣ����
	 * һ��List�����в�����
	 */
  public static List<Emp> getAllEmp(String xmlFileName ){
	  List<Emp>  list=new ArrayList<Emp>();
	  try {
		/**
		 * ����XML���裺
		 * 1:����SAXReader
		 * 2:����File�����������ļ�
		 * 3��ʹ��SAXReader��ȡ�ļ�����ȡ�Ĺ��̣���������Document�������װ��������
		 * 4��ͨ��Document��ȡ��Ԫ�أ�����ǩ��
		 * 5������xml�ṹ��ȡ��ͬ�ڵ��Լ���Ӧ����Ϣ
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
		  //����ÿһ��emp��ǩ
		  for(Element empEle:elements){
			  Attribute idAttr=empEle.attribute("id");
			  int id=Integer.parseInt(idAttr.getValue());
			  String name=empEle.elementText("name");
			  String gender=empEle.elementText("gender");
			  int salary=Integer.parseInt(empEle.elementText("salary"));
			  int age=Integer.parseInt(empEle.elementText("age"));
			  /**
			   * ����������һ���û���Ϣ����һ��Empʵ����
			   * �����뼯��
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
   * ����������������Ա����Ϣ��xml��ʽд������ļ���
   * 
   * ʹ��DOMдxmlԭ��
   * ��֯��������
   * @param list
   * @param filename
   */
  public static void writeXmlToFile(List<Emp> list,String filename){
	  try {
		  /*
		   * 1:���ȴ���Document��������
		   * 2�����ݽṹ���Ԫ�����齨���Ľṹ
		   * 3������д���ļ�
		   */
		  //1
		Document doc=DocumentHelper.createDocument();
		//2
		/*
		 * Document��addElement��String name������
		 * ������Ӹ�Ԫ��
		 * ����ֵΪelement��ʾ��ӵ������Ԫ��
		 * addElement����ֻ�ܵ���һ��
		 * ��Ϊ��ֻ��һ��
		 */
		Element root=doc.addElement("list");
		//ѭ��list����
		for(Emp emp: list){
			//���Ԫ���������Ԫ��emp
			Element empEle=root.addElement("emp");
			/**
			 * Element�ķ�����
			 * addAttribute(String name,String value)
			 * ������ǰԪ����׷������
			 * name��������
			 * value������ֵ
			 */
			empEle.addAttribute("id", emp.getId()+" ");
			//��emp��Ԫ����׷����Ԫ��name
			Element nameEle=empEle.addElement("name");
			/*
			 * Element��addText(String str)����
			 * ��ǰԪ����׷���ı���Ϣ��ǰ�����м䣩
			 * <name>addText׷�ӵ��ı�</name>
			 */
			nameEle.addText(emp.getName());
		    empEle.addElement("age").addText(emp.getAge()+"");//����
		    empEle.addElement("gender").addText(emp.getGender());//�Ա�
		    empEle.addElement("salary").addText(emp.getSalary()+"");//����
			//3
		    FileOutputStream fos =new FileOutputStream(filename);
		    /*
		     * XMLWriter��һ���߼������������Լ�����д��������XML���ݽṹ
		     */
		    XMLWriter xmlWriter =new XMLWriter(fos);
		    
		    xmlWriter.write(doc);
		    //д��Ϻ�ر���
		    xmlWriter.close();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  /**
   * ����XPath
   * @param args
   */
  public static void testXPath(){
	  try {
		SAXReader reader =new SAXReader();
		File file=new File("book.xml");
		Document doc=reader.read(file);
		/**
		 * List selectNodes(String xpath)
		 * dom4j��Document��Xpath��֧�ַ���
		 * ����·�����ʽ����ȡ��Ӧ�ڵ���Ϣ
		 */
		List list =doc.selectNodes("/bookstore/book[price>35]/title");
		System.out.println("�������ˣ�"+list.size()+"��");
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
//	  Emp emp1=new Emp(1,"����",22,"��",5000);
//	  Emp emp2=new Emp(2,"����",22,"��",5000);
//	  list.add(emp1);
//	  list.add(emp2);
//	  writeXmlToFile(list, "employee.xml");
	  testXPath();
}
}
