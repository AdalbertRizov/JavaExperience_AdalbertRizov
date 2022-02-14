package Main;

import Controller.*;
import Model.ModelShop;
import View.ViewCustomer;
import View.ViewShop;
import Client.*;
import Server.*;
import Customer.*;

public class MainClass {
	
	public static void main(String [] args){
		
		ModelShop model=new ModelShop();
		ViewShop myView=new ViewShop(model);
		ControllerShop myShop=new ControllerShop(model,myView);
//		for(int i=0;i<1;i++){
//			Customer cus=new Customer(model);
//			Thread t=new Thread(cus);
//			t.start();
//		}
		ViewCustomer vCus=new ViewCustomer(model);
		ControllerCustomer conCust=new ControllerCustomer(model,vCus);
	
	}//main
}//end of class
