package Serialize;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.io.path.Path;

import Model.Product;

public class BinaryStrategy implements Serializable,fpt.com.SerializableStrategy {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectOutputStream os ;
	private ObjectInputStream is ;
	
	//constructor
	public BinaryStrategy () throws IOException{
		java.nio.file.Path path = Paths.get("products.ser");
		
		 byte[] data = Files.readAllBytes(path);
		 os = new ObjectOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE));

		is = new ObjectInputStream(new ByteArrayInputStream(data));
		
	}
	@Override
	public Product readObject() throws IOException {
		try {
			return (Product) is.readObject();
		} catch (Exception e) {
		}
		return null;
}

	@Override
	public void writeObject(Product p) throws IOException {
		os.writeObject(p); // write Object
		os.flush();		
	}//writeObject

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		os.flush();
		os.close();
		is.close();
	}
	
	
	
}
