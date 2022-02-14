package Client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class IncomingClient implements Runnable{

	//attributes
	ObjectInputStream ois;
	
	//constructor
	public IncomingClient(InputStream is){
			try {
				this.ois=new ObjectInputStream(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
	}//constructor
	
	public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
	        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
	        ObjectInputStream o = new ObjectInputStream(b);
	        return o.readObject();
	}//deserialize
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}//run

}//end of class
