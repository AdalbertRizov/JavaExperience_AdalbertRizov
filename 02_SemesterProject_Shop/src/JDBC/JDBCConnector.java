package JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Product;

public class JDBCConnector {
	public enum Method {
		STATEMENT, PREPAREDSTATEMENT, CALLABLESTATMENT
	}
	
	private Connection con;
	
	//constructor
	public JDBCConnector(){
	  try {
		con = DriverManager.getConnection(
					"jdbc:postgresql://java.is.uni-due.de/ws1011", "ws1011",
					"ftpw10");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
		
	}//constructor
	
	public static void main(String[] args) {

		// Loading driver (can be skipped since java 1.5)
//		try {
//			Class.forName("org.postgresql.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		JDBCConnector myConnector=new JDBCConnector();
		myConnector.getDatabaseUrl();
		myConnector.getDatabaseTables();
		myConnector.getDatabaseUser();
		Product p=new Product();
		p.setName("Test19.02");
		p.setPrice(1.0099);
		p.setQuantity(9999);
		myConnector.insert(p);
		long temp=p.getId();
		System.out.println("The generated id is :"+temp);
		Product whatComesOut=myConnector.read(temp);
		System.out.println("The product with this id is : ");
		//System.out.print(whatComesOut.getName());
		System.out.print(whatComesOut.getProductInfo());
		
	}//main
	
	
	private  void getDatabaseUrl(){
		String result = "";
		try {
			result=con.getMetaData().getURL();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The URL of the database is :");
		System.out.println(result);
	}//getURLOfDB
	
	private  void getDatabaseUser(){
		String user="";
		try {
			 user=con.getMetaData().getUserName();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("The user name is:");
		System.out.println(user);
	}//getDatabaseUsers
	
	private  void getDatabaseTables(){
			System.out.println("The tables are :"); 
			ResultSet tables;
			try {
				//the parameters in the brackets of getTables are saying that
				//info for all available tables should be extracted
				tables = con.getMetaData().getTables("", "", "", null);
				//the next line means : read until there is smth inside this set
				while (tables.next()) { 
					System.out.println(tables.getString(3)); 
				} 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}//getDatabaseTables
	
	public static void executeMyStatement(Connection con, Method m) {
		switch (m) {

		case STATEMENT:

//			try (Statement stmt = con.createStatement();
//					ResultSet rs = stmt
//							.executeQuery("SELECT * FROM products")) {
//				while (rs.next()) {
//					System.out.println(rs.getString(1)+" "+rs.getString(2)+
//										" "+rs.getString(3)+" "+rs.getString(4)+
//										" "+rs.getString(5));
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//	
			try (Statement stmt = con.createStatement();
					//all results from the query will be recorded in this ResultSet
					ResultSet rs = stmt
							.executeQuery("SELECT * FROM products")) {
				while (rs.next()) {
					System.out.println(rs.getString(1)+" "+rs.getString(2)+
										" "+rs.getString(3)+" "+rs.getString(4)+
										" "+rs.getString(5));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case PREPAREDSTATEMENT:

			try (PreparedStatement pstmt = con
					.prepareStatement("SELECT * FROM  WHERE  = ?")) {
				pstmt.setDouble(1, 6.50);
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						System.out.println(rs.getString(2));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;

		case CALLABLESTATMENT:

			try (CallableStatement cstmt = con
					.prepareCall("{ call getpizzawithbigprice(?) }")) {
				cstmt.setDouble(1, 6.50);
				try (ResultSet rs = cstmt.executeQuery()) {
					while (rs.next()) {
						System.out.println(rs.getString(2));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;

		}

	}//executeMyStatement
	
	private  long insert(String name,double price,int quantity){
		try (PreparedStatement pstmt = con
				.prepareStatement("INSERT INTO products(name,price,quantity) VALUES (?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS)) {
				pstmt.setString(1, name);
				pstmt.setDouble(2, price);
				pstmt.setInt(3, quantity);
				pstmt.executeUpdate();
				//the next line saves the generated keys in a result set
				ResultSet rs = pstmt.getGeneratedKeys();
				//we have to call .next() even if there is just 1 thing to be read
				rs.next();
				return rs.getLong(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return -1;
	}//insert which returns the id
	
	private  void insert(Product p){
		String name=p.getName();
		double price=p.getPrice();
		int quantity=p.getQuantity();
		long id=insert(name,price,quantity);
		p.setId(id);
	}//insert which actually inserts the product
	
	private Product read(long productID){
		Product p=new Product();
		p.setId(productID);
		try(PreparedStatement pstmt = con
				.prepareStatement("SELECT id,name,price,quantity FROM products WHERE id=?")) {
			pstmt.setLong(1, p.getId());
			//pstmt.executeUpdate();
			//ResultSet rs=pstmt.getResultSet();
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			p.setId(Long.parseLong(rs.getString(1)));
			//JOptionPane.showMessageDialog(null,"This is the value of the id"+p.getId());
			p.setName(rs.getString(2));
			p.setPrice(Double.parseDouble(rs.getString(3)));
			p.setQuantity(Integer.parseInt(rs.getString(4)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}//read
}//class
