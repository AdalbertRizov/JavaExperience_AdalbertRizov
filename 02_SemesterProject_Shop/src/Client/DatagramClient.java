package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

import javax.swing.JLabel;

public class DatagramClient implements ActionListener {
	
	//atributes
	//private JLabel clock;
	
	//constructor
	public DatagramClient(){
		//this.clock=newClock;
	}//constructor
	
	//methods
	public void run() {
		// Eigene Adresse erstellen
		InetAddress ia = null;
		try {
			ia = InetAddress.getByName("localhost");
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}
		// Socket fьr den Klienten anlegen
		try (DatagramSocket dSocket = new DatagramSocket(5555);) {

			try {
				int i = 0;
				while (true) {
					String command = "DATE:";

					byte buffer[] = null;
					buffer = command.getBytes();

					// Paket mit der Anfrage vorbereiten
					DatagramPacket packet = new DatagramPacket(buffer,
							buffer.length, ia, 3431);
					// Paket versenden
					dSocket.send(packet);

					byte answer[] = new byte[1024];
					// Paket fьr die Antwort vorbereiten
					packet = new DatagramPacket(answer, answer.length);
					// Auf die Antwort warten
					dSocket.receive(packet);
					//the next line prints the time on the console
					System.out.println(new String(packet.getData(), 0, packet
							.getLength()));
//					String date=new String(packet.getData());
//					this.clock.setText(date);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					i++;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (SocketException e1) {
			e1.printStackTrace();
		}

	}//run

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.run();
	}//actionPerformed
}
