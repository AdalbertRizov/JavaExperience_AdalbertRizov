package Client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.print.DocFlavor.BYTE_ARRAY;
import javax.swing.JOptionPane;

public class OutgoingClient implements Runnable{
	
	//attributes
	private ObjectOutputStream oos;
	private AuthenticatedOrder message;
	
	//constructor
	public OutgoingClient(OutputStream os){
		try {
			this.oos=new ObjectOutputStream(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}//constructor
	
	//serialize message object containing login information and order
	//after serialization, the message object will be transferred to server 
	//the server should deserialize it back to object again
	 public static byte[] serialize(Object obj) throws IOException {
	        ByteArrayOutputStream b = new ByteArrayOutputStream();
	        ObjectOutputStream o = new ObjectOutputStream(b);
	        o.writeObject(obj);
	        return b.toByteArray();
	 }//serialize
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				synchronized(this){
					while(this.message==null){
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}//synchronized
				//oos.write(this.serialize(message));
				//oos.writeObject((Object)message);
				oos.write(999);
				oos.flush();
				JOptionPane.showMessageDialog(null,"Message Send!!! YEEEEEEE!!!!");
			}catch(IOException e){
				e.printStackTrace();
			}
		}
//		while(true){
//			try{
//				synchronized(this){
//					while(message==null wait());
//				}//synchronized
//				oos.writeObject(message);
//				oos.flush();
//				message=null;
//				System.out.println("Message send!!");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		}//while
		
		
	}//run
	
	public void setMessage(AuthenticatedOrder authOrder){
		this.message=authOrder;
	}//setMessage
	
}//end of class
