package Model;

import java.util.ArrayList;
import java.util.Iterator;

import fpt.com.Product;

public class ProductList extends ArrayList<Product> implements fpt.com.ProductList{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	@Override
	public boolean delete(Product product) {
		return super.remove(product);
	}//delete

	@Override
	public Product findProductById(long id) {
		for(Product p : this){
			if (p.getId()==id){
				return p;
			}
		}
		return null;
	}//findProductById

	@Override
	public Product findProductByName(String name) {
		for(Product p : this){
			if (p.getName() ==name){
				return p;
			}
		}
		return null;
	}//findProductByName
	
	public ArrayList<Model.Product> getProducts(){
		ArrayList<Model.Product> temp=new ArrayList<Model.Product>();
		for (Product p : this){
			temp.add((Model.Product) p);
		}
		return  temp;
	}//getProducts
}//ProductList
