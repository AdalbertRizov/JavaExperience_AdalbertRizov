package Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "products")
public class Product implements fpt.com.Product,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private long id;
	private double price;
	private int quantity;
	
	//standartConstructor
	public Product(){
		
	}//standartConstructor
	
	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return this.id;
	}//getId

	@Override
	public void setId(long id) {
		this.id=id;
	}//setId

	@Override
	public double getPrice() {
		return this.price;
	}//getPrice

	@Override
	public void setPrice(double price) {
		this.price=price;
	}//setPrice

	@Override
	public int getQuantity() {
		return this.quantity;
	}//getQuantity

	@Override
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}//setQuantity

	@Override
	public String getName() {
		return this.name;
	}//getName

	@Override
	public void setName(String name) {
		this.name=name;
	}//setName
	
	public String getProductInfo(){
		return this.getName()+" "+this.getPrice()+" "+
				this.getQuantity()+" "+this.getId();
	}//getProductInfo

	
}
