package Serialize;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import fpt.com.Product;

public class XMLStrategy implements Serializable,fpt.com.SerializableStrategy{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	FileOutputStream fo;
//	XMLEncoder encoder;
//	FileInputStream fi;
//	XMLDecoder decoder;

	//constructor
	public XMLStrategy() throws IOException{
//		 fo = new FileOutputStream("products.xml");
//		encoder = new XMLEncoder(fo);
//		fi = new FileInputStream("products.xml");
//		decoder = new XMLDecoder(fi);
	}
	
	@Override
	public Model.Product readObject() throws IOException {
		Model.Product readObject = new Model.Product();
		try (FileInputStream fi = new FileInputStream("products.xml");
				XMLDecoder decoder = new XMLDecoder(fi)) {
			readObject = (Model.Product) decoder.readObject(); // Read Object
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (Model.Product) readObject;
	}

	@Override
	public void writeObject(Model.Product p) throws IOException {
		try (FileOutputStream fo = new FileOutputStream("products.xml");
				XMLEncoder encoder = new XMLEncoder(fo)) 	{
			encoder.writeObject(p); // write Object
			encoder.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//writeObject

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
}
