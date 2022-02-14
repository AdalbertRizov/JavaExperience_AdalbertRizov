package Server;

import java.io.Serializable;

import javax.swing.JOptionPane;

import Model.Order;

public class AuthenticatedOrder implements Serializable {
	public String userName;
	public String password;
	public Order order;
	
	//constructor
	public AuthenticatedOrder(){
		this.userName = null;
		this.password = null;
		this.order =null;
	}//constructor
	
	
}//class
