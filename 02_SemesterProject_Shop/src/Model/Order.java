 package Model;

import java.util.ArrayList;
import java.util.Iterator;

import fpt.com.Product;

public class Order extends ArrayList<Product> implements fpt.com.Order{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	@Override
	public boolean delete(Product p) {
		return super.remove(p);
	}//delete

	@Override
	public Product findProductById(long id) {
		for(Product p : this){
			if(p.getId()==id){
				return p;
			}
		}
		return null;
	}//findProductById

	@Override
	public Product findProductByName(String name) {
		for(Product p : this){
			if(p.getName()==name){
				return p;
			}
		}
		return null;
	}//findProductByName

	@Override
	public double getSum() {
		double sum=0.0;
		for(Product p: this){
			sum=sum+p.getPrice()*p.getQuantity();
		}
		return sum;
	}//getSum

	@Override
	public int getQuantity() {
		int totalQuantity=0;
		for(Product p: this){
			totalQuantity=totalQuantity+p.getQuantity();
		}
		return totalQuantity;
	}//getQuantity

	
	

}
