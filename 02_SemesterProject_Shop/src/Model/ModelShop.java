package Model;

import java.util.ArrayList;
import java.util.Iterator;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class ModelShop extends java.util.Observable implements fpt.com.ProductList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  ProductList list;
	private SerializableStrategy serialization;
	
	//constructor
	public ModelShop(){
		super();
		this.list=new ProductList();
	}//constructor
	
	public SerializableStrategy getSerializationType(){
		return this.serialization;
	}//getSerStrat
	
	public void setSerStrat(SerializableStrategy s){
		this.serialization=s;
	}//setSer
	
	@Override
	public Iterator<Product> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Model.Product> getProducts(){
		return this.list.getProducts();
	}//getProducts
	
	@Override
	public boolean add(Product e) {
		list.add(e);
		super.setChanged();
		super.notifyObservers(e);
		return true;
		
	}//add

	@Override
	public boolean delete(Product product) {
		if (list.delete(product)==true){
			list.delete(product);
			super.setChanged();
			super.notifyObservers();
			return true;
		}else{
			return false;
		}
	}//delete

	@Override
	public int size() {
		return list.size();
	}//size

	@Override
	public Product findProductById(long id) {
		return list.findProductById(id);
	}//findProductById

	@Override
	public Product findProductByName(String name) {
		return list.findProductByName(name);
	}//findProductByName

	public void clear() {
		list.clear();
		setChanged();
		notifyObservers();
	}

	public Model.Product get(int i) {
		return (Model.Product) list.get(i);
	}

}
