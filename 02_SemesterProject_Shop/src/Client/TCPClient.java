package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class TCPClient implements Runnable {
	
	//attributes
	Socket serverCon;
	public IncomingClient incoming;
	public OutgoingClient outgoing;
	
	
	//constructor
	public TCPClient(){
		//this.message=new AuthenticatedOrder();
//		try {
//			this.serverCon = new Socket("localhost",3141);
//			InputStream in = serverCon.getInputStream();
//			OutputStream out = serverCon.getOutputStream();
//			this.incoming=new IncomingClient(in);
//			this.outgoing=new OutgoingClient(out);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}//constructor
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
//		Thread outgoingThread=new Thread(this.outgoing);
//		Thread incomingThread=new Thread(this.incoming);
//		outgoingThread.start();
//		incomingThread.start();
	}//run
	
}//end of class

