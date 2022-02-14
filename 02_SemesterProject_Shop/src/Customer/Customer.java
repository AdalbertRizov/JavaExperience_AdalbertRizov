package Customer;

import Model.*;
import View.*;
import Controller.*;

public class Customer implements Runnable {
	
	//attributes
	private ModelShop model;
	private ViewCustomer viewCustomer;
	private ControllerCustomer controllerCustomer;
	
	//constructor
	public Customer(ModelShop m){
		this.model=m;
		this.viewCustomer=new ViewCustomer(this.model);
		this.controllerCustomer=new ControllerCustomer(this.model,this.viewCustomer);
	}//constructor
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
		}
	}//run

}
