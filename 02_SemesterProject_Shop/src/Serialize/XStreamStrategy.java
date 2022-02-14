package Serialize;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import fpt.com.Product;

public class XStreamStrategy implements Serializable,fpt.com.SerializableStrategy{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//constructor
	public XStreamStrategy() throws IOException{
		
	}

	@Override
	public Model.Product readObject() throws IOException {
		XStream xstream = new XStream(new DomDriver());
		Model.Product readObject = new Model.Product();
		try(FileReader fr = new FileReader("productsXStream.xml");){
			readObject =(Model.Product) xstream.fromXML(fr); // Read Object
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readObject;
	}

	@Override
	public void writeObject(Model.Product p) throws IOException {
		XStream xstream = new XStream(new DomDriver());
		try (FileWriter fw = new FileWriter("productsXStream.xml")){
			xstream.toXML(p, fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	
}
