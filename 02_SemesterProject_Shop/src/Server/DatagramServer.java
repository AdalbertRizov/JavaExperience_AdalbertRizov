package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DatagramServer implements Runnable {
	private DatagramSocket socket;
	//the next line is the original text
//	public static void main(String[] args) {
	public DatagramServer(){
		//prepare a socket where the server is reachable
		//try (DatagramSocket socket =  new DatagramSocket(6667);){
			try {
				this.socket=new DatagramSocket(6667);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//} catch (IOException e) {
		//	e.printStackTrace();
		//} 

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null,"Datagram Server started!!");
		while (true) {

			//create a new package
			DatagramPacket packet = new DatagramPacket(new byte[5], 5);
			//wait for a package
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Read the data
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			int len = packet.getLength();
			byte[] data = packet.getData();

			System.out.printf(
					"\n Request from IP Address %s from port %d",
					address, port, len, new String(data, 0, len));
//			System.out.printf(
//					"Request from IP Address %s from port %d with the length %d:%n%s%n",
//					address, port, len, new String(data, 0, len));

			// Nutzdaten in ein Stringobjekt ьbergeben
			String da = new String(packet.getData());
			// Kommandos sollen durch : getrennt werden
			try (Scanner sc = new Scanner(da).useDelimiter(":")) {
				// Erstes Kommando filtern
				String keyword = sc.next();

				if (keyword.equals("DATE")) {

					Date dt = new Date();
					String dateString = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(dt); 

					
					byte[] myDate =  dateString.getBytes();

					// Paket mit neuen Daten (Datum) als Antwort vorbereiten
					packet = new DatagramPacket(myDate, myDate.length,
							address, port);
					// Paket versenden
					socket.send(packet);

				} else {
					byte[] myDate = null;
					myDate = new String("Command unknown").getBytes();

					// Paket mit Information, dass das Schlьsselwort
					// ungьltig
					// ist
					// als Antwort vorbereiten
					packet = new DatagramPacket(myDate, myDate.length,
							address, port);
					// Paket versenden
					socket.send(packet);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//while

	}//run
}
