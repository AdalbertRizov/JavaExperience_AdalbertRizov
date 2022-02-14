package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import chat.ChatClient;

import JDBC.JDBCConnector;
import JDBC.JDBCConnector.Method;
import Model.ModelShop;
import Model.Product;
import Serialize.BinaryStrategy;
import Serialize.JDBCStrategy;
import View.ViewShop;
 	
public class ControllerShop implements ActionListener{
	//attributes
	private ModelShop modelShop;
	private ViewShop viewShop;
	private String serString;
	private ChatClient chatClient;
	//constructor
	public ControllerShop(ModelShop m, ViewShop v){
		this.modelShop=m;
		this.viewShop=v;
		this.viewShop.addActionListener(this);
		this.serString="";
	}//constructor
	
	//methods
//	private void jdbc(){
//		modelShop.clear();
//		try {
//			Class.forName("org.postgresql.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		try (Connection con = DriverManager.getConnection(
//				"jdbc:postgresql://java.is.uni-due.de/ws1011", "ws1011",
//				"ftpw10")) {
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt
//					.executeQuery("SELECT * FROM products");
//			//we have to put EXPLICITLY  the names of the columns here
//			//for(rs.getMetaData().getColumnCount();
//			
//				int i=0;
//				while (rs.next() &&i<10) {
//					fpt.com.Product p=new Product();
//					p.setId(Long.parseLong(rs.getString(1)));
//					//JOptionPane.showMessageDialog(null,"This is the value of the id"+p.getId());
//					p.setName(rs.getString(2));
//					p.setPrice(Double.parseDouble(rs.getString(3)));
//					p.setQuantity(Integer.parseInt(rs.getString(5)));
//					modelShop.add(p);
//					//rs.next();
//					i++;
//				}//while
//			//JDBCConnector.executeMyStatement(con, Method.STATEMENT);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}//jdbc
	
	private void save(String strat){
		switch(strat){
			case "":{
				JOptionPane.showMessageDialog(null,"Please choose a strategy!!");
				break;
			}//case""
			case "binary":{
				try {
					modelShop.setSerStrat( new BinaryStrategy());
				} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			break;
			}//case"binary"
			case "jdbc":{
				modelShop.setSerStrat(new JDBCStrategy());
			}//case"jdbc"
//		case "xml":{
//
//			modelShop.setSerStrat( new BinaryStrategy());
//			break;
//			
//		}//case"xml"
//		case "xstream":{
//
//			modelShop.setSerStrat( new BinaryStrategy());
//			break;
//			
//		}//case"xstream"

		}//switch
		if((modelShop.getSerializationType()==null)){
			JOptionPane.showMessageDialog(null,"Please choose a strategy!!");
		}else{

			for (int i=0;i<modelShop.size();i++){
			try {
				modelShop.getSerializationType().writeObject((Product)modelShop.get(i));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			modelShop.clear();
		}//else
	}//save
	
	private void load(String strat){
		switch(strat){
			case "":{
				JOptionPane.showMessageDialog(null,"Please choose a strategy!!");
				break;
			}//case""
			case "binary":{
				try {
					modelShop.setSerStrat( new BinaryStrategy());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}//case"binary"
//		case "xml":{
//			this.serialization=new Serialization();
//			try {
//				this.serialization.setSerStrat(new XMLStrategy());
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			break;
//			
//		}//case"xml"
//		case "xstream":{
//			this.serialization=new Serialization();
//			try {
//				this.serialization.setSerStrat(new XStreamStrategy());
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			break;
//			
//		}//case"xml"
			case "jdbc" :{
				modelShop.setSerStrat(new JDBCStrategy());
				//this.jdbc();
			}//case "jdbc"
		}//switch
		if(modelShop.getSerializationType()==null){
			JOptionPane.showMessageDialog(null,"Please choose a strategy!!TEST4.12");
			return;
		}else{
			if(this.serString=="binary"){
				try {
					Product p =null;
					do {
						p =(Model.Product)modelShop.getSerializationType().readObject();
						this.modelShop.add(p);
					} while (p != null);
					
				} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
			}
			if(this.serString=="jdbc"){
				for(int i=0;i<10;i++){
					try {
						Product p =null;
						p =(Model.Product)modelShop.getSerializationType().readObject();
						this.modelShop.add(p);
					} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
					}
				}
			}
			
		}//else
	}//load
	
	private void startChatClient(){
		String userName = JOptionPane.showInputDialog(null,"Please enter your username :");
		String password = JOptionPane.showInputDialog(null,"Please enter your password :");
		this.chatClient=new ChatClient(userName, password);
	}//startChatClient
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//add button is pressed
		if(viewShop.getAddButton().hasFocus()==true){
			Product p=viewShop.getProduct();
			if(p!=null){
				modelShop.add(p);
				viewShop.getPriceTextField().setBadChars("`~!@#$%^&*()[]_+=\\|\"':;,?/>< ");
			}
		}
		
		//delete button is pressed
		if(viewShop.getDeleteButton().hasFocus()==true){
			
			int row=(int)viewShop.getProductsList().getSelectedRow();
			
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null,"You have to choose an element before you press the delete button!!");
				return;
			}
		
			
			
			//int row=(int)viewShop.getProductsList().getSelectedRow();
			int col=(int)viewShop.getProductsList().getSelectedColumn();
			if((row<0)||(col<0)||(row>viewShop.getProductsList().getRowCount())){
				JOptionPane.showMessageDialog(null,"You have to choose an element before you press the delete button!!");
			}else{
				if ((row>0)&&(viewShop.getProductsList().getRowCount()>0)){
					String name=(String)viewShop.getProductsList().getValueAt(row, 0);
					Product p=(Product) modelShop.findProductByName(name);	
					modelShop.delete(p);
					modelShop.notifyObservers();
					viewShop.getProductsList().clearSelection();
				}
				if((row==0)&&(viewShop.getProductsList().getRowCount()>0)){
					String name=(String)viewShop.getProductsList().getValueAt(row, 0);
					Product p=(Product) modelShop.findProductByName(name);	
					modelShop.delete(p);
					viewShop.getProductsList().clearSelection();
				}
				if((row==0)&&(viewShop.getProductsList().getRowCount()==0)){
					//JOptionPane.showMessageDialog(null,"This element will be removed if you add another one!!");
					viewShop.getProductsList().clearSelection();
				}
			}
			
		}
		//the menu choices
		String choice=e.getActionCommand();
		switch(choice){
			case "none" :{
				JOptionPane.showMessageDialog(null,"You must choose a serializable strategy!!");
				break;
			}
			case "binary":{
				this.serString="binary";
				break;
			}
			case "xml":{
				this.serString="xml";
				break;
			}
			case "xstream":{
				this.serString="xstream";
				break;
			}
			case "save":{
				//JOptionPane.showMessageDialog(null,"The save is choosen !!");
				this.save(this.serString);
				break;
			}
			case "load":{
				this.load(this.serString);
				break;
			}//case load
			case "jdbc":{
				//JOptionPane.showMessageDialog(null,"The jdbc is choosen !!");
				this.serString="jdbc";
				break;
			}
			case "someCommand1":{
				JOptionPane.showMessageDialog(null,"The chat is choosen !!");
				this.startChatClient();
				break;
			}
		}//switch
	}//actionPerformed
}
