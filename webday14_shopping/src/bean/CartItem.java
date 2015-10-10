package bean;

import java.io.Serializable;

import entity.Computer;
/**
 * 商品条目
 * @author teacher
 *
 */
public class CartItem implements Serializable {
	private Computer computer;
	private int qty;
	public Computer getComputer() {
		return computer;
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
