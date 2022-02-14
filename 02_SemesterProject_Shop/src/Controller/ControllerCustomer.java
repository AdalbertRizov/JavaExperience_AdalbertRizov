package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Model.*;
import View.ViewCustomer;
import View.ViewShop;
import Client.*;

public class ControllerCustomer implements ActionListener {
	
	//atributes
	private ModelShop modelShop;
	private ViewCustomer viewCustom;
	private DatagramClient dateClient;
	//TCP client for the order
	private TCPClient tcpClient;
	//constructor
	public ControllerCustomer(ModelShop m, ViewCustomer vCus){
		this.modelShop=m;
		this.viewCustom=vCus;
		this.viewCustom.addActionListener(this);
		this.dateClient=new DatagramClient();
	}//constructor
	
	@Override
	//here is actually the connection to the server
	public void actionPerformed(ActionEvent e) {
		String userName = JOptionPane.showInputDialog(null,"Please enter your username :");
		String password = JOptionPane.showInputDialog(null,"Please enter your password :");
		AuthenticatedOrder authenticatedOrderToSend = new AuthenticatedOrder();
		authenticatedOrderToSend.userName=userName;
		authenticatedOrderToSend.userName=password;
		Order orderToSend = new Order();
		//go through the table, and read the order
		for (int i = 0; i < this.viewCustom.tableModelProducts.getRowCount(); i++) {
            int ammount=0;
            String ammountAsString=(String)this.viewCustom.tableModelProducts.getValueAt(i, 3);
            if (ammountAsString != null){
            	 ammount=Integer.parseInt(ammountAsString);
            	 if(ammount!=0){
                	 Product p=new Product();
                	 p.setName((String)this.viewCustom.tableModelProducts.getValueAt(i, 0));
                	 double price=(Double)this.viewCustom.tableModelProducts.getValueAt(i, 1);
                	 p.setPrice(price);
                	 p.setQuantity(ammount);
                	 orderToSend.add(p);
                 }
            }
        }//for
		authenticatedOrderToSend.order=orderToSend;
		JOptionPane.showMessageDialog(null,"TCP Client Started");
		// Create the connection
		// prepare streams
		try (Socket serverCon = new Socket("localhost", 6668);
				InputStream in = serverCon.getInputStream();
				OutputStream out = serverCon.getOutputStream()) {
			String mess="This is a test message";
			ObjectInputStream oIn=new ObjectInputStream(in);
			ObjectOutputStream oOut= new ObjectOutputStream(out);
			oOut.writeObject(authenticatedOrderToSend);
			oOut.flush();
			System.out.println("Message successfully send !!");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,"client : socket, inputstream or outputstream has failed." + ex.getMessage());
			return;
		}
	}//actionPerformed

}//endOfClass
