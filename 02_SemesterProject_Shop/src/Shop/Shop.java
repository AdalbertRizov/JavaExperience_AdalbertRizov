package Shop;

import Controller.ControllerShop;
import Model.ModelShop;
import View.ViewCustomer;
import View.ViewShop;

public class Shop {
	private ModelShop model;
	private ViewShop viewShop;
	private ViewCustomer viewCustomer;
	private ControllerShop controllerShop;
	
	//constructor
	public Shop(ModelShop m, ViewShop v1,ViewCustomer v2,ControllerShop c){
		this.model=m;
		this.viewShop=v1;
		this.viewCustomer=v2;
		this.controllerShop=c;
	}
}
