package entity;

import java.io.Serializable;

public class Computer implements Serializable{
	private long id;
	private String name;
	private String desc;
	private String pic;
	private double price;
	public Computer() {
	}
	
	public Computer(String name, String desc, String pic, double price) {
		this.name = name;
		this.desc = desc;
		this.pic = pic;
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:" + id + " name:" + name + " desc:" + desc + " pic:" + pic + " price:" + price;
	}
	
}
