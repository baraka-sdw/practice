package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * ���ﳵ
 * @author teacher
 *
 */
public class Cart implements Serializable {
	private List<CartItem> items = 
		new ArrayList<CartItem>();
	/**
	 * �����Ʒ�������ﳵ���Ѿ���ӹ�����Ʒ����
	 * ��������ˣ����ҷ���false;
	 * @param item
	 * @return
	 */
	public boolean add(CartItem item){
		for(int i=0;i<items.size();i++){
			CartItem old = items.get(i);
			if(old.getComputer().getId()== 
				item.getComputer().getId())
			{
				return false;
			}
		}
		items.add(item);
		return true;
	}
	
	public void delete(long id){
		for(int i=0;i<items.size();i++){
			CartItem old = items.get(i);
			if(old.getComputer().getId() == id){
				items.remove(old);
				return;
			}
		}
	}
	
	public void modify(long id,int qty){
		for(int i=0;i<items.size();i++){
			CartItem old = items.get(i);
			if(old.getComputer().getId() == id){
				old.setQty(qty);
				return;
			}
		}
	}
	
	public double sum(){
		double total = 0;
		for(int i=0;i<items.size();i++){
			CartItem old = items.get(i);
			total += old.getComputer().getPrice() * 
			old.getQty();
		}
		return total;
	}
	
	public void clear(){
		items.clear();
	}
	
	public List<CartItem> list(){
		return items;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return items.toString();
	}
	
	
}
