package day05;
//������������XML�ļ���һ��Ա������Ϣ
/*
 * �������ģʽҪϰ��
 * ����һ����񱣴���������ݶ��ԣ��ж����У��ֶΣ�
 * ��������Ͷ�Ӧ������Ӧ���͵�����
 * �������ǿ����ø����ÿһ��ʵ���������е�ÿһ������
 * 
 * vo:value object
 */
public class Emp {
	private int id;
	private String name;
	private int age;
	private String gender;
	private int salary;
	public Emp(int id, String name, int age, String gender, int salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "["+id+","+name+","+age+","+gender+","+salary+"]";
	}
   String a=new String("asaa");
 
}
 