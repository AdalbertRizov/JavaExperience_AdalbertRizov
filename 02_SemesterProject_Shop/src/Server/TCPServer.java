package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import Client.IncomingClient;

public class TCPServer implements Runnable{
	//attributes
	ServerSocket server;
	
	//constructor
	public TCPServer(){
		try {
			 this.server = new ServerSocket(3141);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//constructor
	
	@Override
	public void run() {
		//try (ServerSocket server = new ServerSocket(3141)) {
		 JOptionPane.showMessageDialog(null,"TCP Server Started");
			// Timeout after 1 Minute
			// server.setSoTimeout(60000);
		 JOptionPane.showMessageDialog(null,"Breakpoint 0 in the TCP server");
			try (Socket client = server.accept();
					InputStream in = client.getInputStream();// Create the streams
					OutputStream out = client.getOutputStream()) {
				 JOptionPane.showMessageDialog(null,"Breakpoint 1 in the TCP server before the while");
				 while (true) {
					 JOptionPane.showMessageDialog(null,"Breakpoint 2 in the TCP server inside the while");
						int temp=in.read();
						 JOptionPane.showMessageDialog(null,"Breakpoint 3 in the TCP server inside the while,after read");

						System.out.println("\n The accepted number is :"+temp);
					}
					//here we should read the incoming order
					//part of which is the authentification
					//AuthenticatedOrder incomingOrder=in.r
//				
//
//				// read the numbers
//				int a = in.read();
//				int b = in.read();
//
//				int result = ggT(a, b);
//
//				// write the result in output stream
//				out.write(result);
//				out.flush();

			} catch (IOException e1) {
				e1.printStackTrace();
			}

			
		//} catch (IOException e2) {
		//	e2.printStackTrace();
		//}

	}//run

}
