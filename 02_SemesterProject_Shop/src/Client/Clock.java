package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Clock implements Runnable {

	//attributes
	private  int portNumber;
	InetAddress ia;
	DatagramSocket dSocket;
	ClockInterface clockListener;
	
	//constructor
	public Clock(ClockInterface clockListener){
		this.clockListener =clockListener;
		try {
			this.dSocket=new DatagramSocket();
			this.ia = InetAddress.getByName("localhost");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}
		
		
//		String socketNumberAsString=JOptionPane.showInputDialog(null,"Please enter a port number :");
//		portNumber=Integer.parseInt(socketNumberAsString);
//		JOptionPane.showMessageDialog(null,"The socket number you entered is"+portNumber);
	}//constructor
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			
			try {
				while (true) {
						String command = "DATE:";

						byte buffer[] = null;
						buffer = command.getBytes();

						// Paket mit der Anfrage vorbereiten
						DatagramPacket packet = new DatagramPacket(buffer,
								buffer.length, ia, 6667);
						// Paket versenden
						dSocket.send(packet);

						byte answer[] = new byte[1024];
						// Paket fьr die Antwort vorbereiten
						packet = new DatagramPacket(answer, answer.length);
						// Auf die Antwort warten
						dSocket.receive(packet);
						//the next line prints the time on the console
//						System.out.println(new String(packet.getData(), 0, packet
//								.getLength()));
						final String date=new String(packet.getData());
						
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								clockListener.setClock(date);
							}
						});
						
						
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}//while
			} catch (IOException e1) {
					e1.printStackTrace();
			}

		}//outer while
	}//run

}
