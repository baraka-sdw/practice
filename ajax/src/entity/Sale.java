package entity;

public class Sale {
	private int id;
	private String prodName;
	private int qty;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Sale(int id, String prodName, int qty) {
		super();
		this.id = id;
		this.prodName = prodName;
		this.qty = qty;
	}
	public Sale() {
		super();
	}
}
