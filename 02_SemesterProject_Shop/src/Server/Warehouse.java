package Server;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Warehouse extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4104761488959565485L;
	//attributes
	  //servers
	DatagramServer clockServer;
	TCPServer orderServer;
	  //for the gui
	private Container c;
	private Container north;
	private Container south;
	private JLabel numberOfActiveThreads;
	private JTextField numberOfThreads;
	private JTextArea serverMessages;
	private JButton startServer;
	
	//constructor
	public Warehouse(){
		//the gui
		super("Warehouse View");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.numberOfActiveThreads=new JLabel("Number of active threads");
		this.startServer=new JButton("Start Server");
		this.startServer.setPreferredSize(new Dimension(150,40));
		this.startServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// START SERVER BUTTON CLICK CODE
			
				// initialize socket
				try (ServerSocket server = new ServerSocket(6668))
				{
					// when server accepts incoming connection
					try (Socket client = server.accept();
							InputStream in=client.getInputStream();
							OutputStream out=client.getOutputStream())
					{
						JOptionPane.showMessageDialog(null,"TCP Server Started");
						serverMessages.append("\nTCP Server Started");
						try {
							// to be sure to client to be flushed itself before this starts
							Thread.sleep(1000);
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						// Create the streams
						ObjectOutputStream objOut = new ObjectOutputStream(out);
						ObjectInputStream objIn = new ObjectInputStream(in);
						JOptionPane.showMessageDialog(null,"TCP Server Started");
						 
						// execute an infinite loop for connection waiting
						while (true) 
						{
						AuthenticatedOrder incomingMessage =new AuthenticatedOrder();
							try {
								// cast serialized incoming message into an object
								 incomingMessage = (AuthenticatedOrder) objIn.readObject();
								// save incoming object into a file
								 Random r=new Random();
								 byte[] serializedObject = serialize(incomingMessage);
								 java.nio.file.Path path = Paths.get("order " +r.nextLong()+".ser");
								ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE));
								os.writeObject(serializedObject);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"server : class failed." + e1.getMessage());
							}
							JOptionPane.showMessageDialog(null,"server : received this username" + incomingMessage.userName);
							JOptionPane.showMessageDialog(null,"server : received this password" + incomingMessage.password);

							objOut.writeObject("Server returns this");
							objOut.flush();
							objOut.close();
							objIn.close();
						}
					} 
					catch (IOException e1) 
					{
						JOptionPane.showMessageDialog(null,"server : server.accept, inputstream or outputstream initialization is failed." + e1.getMessage());
						return;
						
					}
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(null,"server : new server socket failed." + e2.getMessage());
					return;
				}					

			}//actionPerformed
		});
		this.numberOfThreads=new JTextField();
		this.numberOfThreads.setPreferredSize(new Dimension(40,20));
		this.numberOfThreads.setEnabled(true);
		this.numberOfThreads.setEditable(false);
		this.north=new Container();
		this.north.setLayout(new FlowLayout());
		this.north.add(numberOfActiveThreads);
		this.north.add(numberOfThreads);
		this.north.add(startServer);
		this.serverMessages=new JTextArea();
		this.serverMessages.setPreferredSize(new Dimension(450,350));
		this.serverMessages.setEnabled(true);
		this.serverMessages.setEditable(false);
		this.south=new Container();
		this.south.setLayout(new FlowLayout());
		this.south.add(serverMessages);
		c=this.getContentPane();
		c.add(north,BorderLayout.NORTH);
		c.add(south,BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(500,440));
		this.setResizable(true);
		this.setLocation(780,10);
		this.pack();
		this.setVisible(true);
		//JOptionPane.showMessageDialog(null,"Breakpoint 0");
		//the servers
		this.clockServer=new DatagramServer();
		//JOptionPane.showMessageDialog(null,"Breakpoint 1");
		this.orderServer=new TCPServer();
		//JOptionPane.showMessageDialog(null,"Breakpoint 2");
		this.serverMessages.setText("Datagram Server Started!!");
		//JOptionPane.showMessageDialog(null,"Breakpoint 3");
	}//constructor
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}//main

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread clockThread=new Thread(this.clockServer);
		//Thread orderThread=new Thread(this.orderServer);
		clockThread.start();
		//orderThread.start();
	}//run
	
	//serialize message object containing login information and order
		//after serialization, the message object will be transferred to server 
		//the server should deserialize it back to object again
		 public static byte[] serialize(Object obj) throws IOException {
		        ByteArrayOutputStream b = new ByteArrayOutputStream();
		        ObjectOutputStream o = new ObjectOutputStream(b);
		        o.writeObject(obj);
		        return b.toByteArray();
		 }//serialize

}//endOfClass
