package Serialize;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Product;

public class JDBCStrategy implements fpt.com.SerializableStrategy{
	
	//attribute
	private  Connection con;
	private long startReadingFromProductIndex=4350;
	
	//constructor
	public JDBCStrategy(){
		try {
			con = DriverManager.getConnection(
						"jdbc:postgresql://java.is.uni-due.de/ws1011", "ws1011",
						"ftpw10");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}//constructor
	
	@Override
	public Product readObject() throws IOException {
		// TODO Auto-generated method 
		Product p=new Product();
		try(PreparedStatement pstmt = con
				.prepareStatement("SELECT id,name,price,quantity FROM products WHERE id>?")) {
			pstmt.setLong(1, startReadingFromProductIndex);
			//pstmt.executeUpdate();
			//ResultSet rs=pstmt.getResultSet();
			//pstmt.setMaxRows(10);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			p.setId(Long.parseLong(rs.getString(1)));
			startReadingFromProductIndex=p.getId();
			//JOptionPane.showMessageDialog(null,"This is the value of the id"+p.getId());
			p.setName(rs.getString(2));
			p.setPrice(Double.parseDouble(rs.getString(3)));
			p.setQuantity(Integer.parseInt(rs.getString(4)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}//readObject

	@Override
	public void writeObject(Product p) throws IOException {
		// TODO Auto-generated method stub
		String name=p.getName();
		double price=p.getPrice();
		int quantity=p.getQuantity();
		long id=insert(name,price,quantity);
		p.setId(id);
	}//writeObject

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//close
	
	private  long insert(String name,double price,int quantity){
		try (PreparedStatement pstmt = con
				.prepareStatement("INSERT INTO products(name,price,quantity) VALUES (?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS)) {
				pstmt.setString(1, name);
				pstmt.setDouble(2, price);
				pstmt.setInt(3, quantity);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				return rs.getLong(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return -1;
	}//insert which returns the id
	
}//class
