package Serialize;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.openjpa.persistence.OpenJPAPersistence;

import Model.Product;

public class OpenJPAStrategy implements fpt.com.SerializableStrategy{

	//constructor
	public OpenJPAStrategy(){
		Product p;
		EntityManagerFactory fac = getWithoutConfig();
		//EntityManagerFactory fac = Persistence.createEntityManagerFactory(
		//		"openjpa", System.getProperties());

		EntityManager e = fac.createEntityManager();

		EntityTransaction t = e.getTransaction();
		 t.begin();
		 //e.persist(p);//here we write in the database
		 t.commit(); // all ok commit
		// all Data is saved in database now
		//t.begin();
		// QBE
//		for (Object o : e.createQuery("SELECT p FROM products c")
//				.getResultList()) {
//			System.out.println(o);
//			Product p = (Product) o;
//		}
		//t.commit(); // all ok commit
		
		e.close();
		fac.close();
	}//constructor
	
	@Override
	public Product readObject() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeObject(Product p) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	public static EntityManagerFactory getWithoutConfig() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("openjpa.ConnectionURL",
				"jdbc:postgresql://java.is.uni-due.de/ws1011/products/");
		map.put("openjpa.ConnectionDriverName", "org.postgresql.Driver");
		map.put("openjpa.ConnectionUserName", "ws1011");
		map.put("openjpa.ConnectionPassword", "ftpw10");
		map.put("openjpa.RuntimeUnenhancedClasses", "supported");
		map.put("openjpa.jdbc.SynchronizeMappings", "false");

		// find all classes to registrate them
//		List<Class<?>> types = new ArrayList<Class<?>>();
//		types.add(Product.class);
//
//		if (!types.isEmpty()) {
//			StringBuffer buf = new StringBuffer();
//			for (Class<?> c : types) {
//				if (buf.length() > 0)
//					buf.append(";");
//				buf.append(c.getName());
//			}
//			// <class>Producer</class>
//			map.put("openjpa.MetaDataFactory", "jpa(Types=" + buf.toString()
//					+ ")");
//		}

		return OpenJPAPersistence.getEntityManagerFactory(map);

	}
}//class
