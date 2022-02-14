package chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ClientService{
		private String userName;
		private String password;
		private Container c;
		private Container north;
		private Container south;
		private JTextField writeMessageHere;
		private JTextArea seeMessageHere;
		private JButton sendMessage;
		
	//constructor
	public ChatClient(String uName,String pass){
		super("Chat Client Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.seeMessageHere=new JTextArea();
		this.seeMessageHere.setPreferredSize(new Dimension(200,100));
		this.north=new Container();
		this.north.setLayout(new FlowLayout());
		this.north.add(seeMessageHere);
		this.writeMessageHere=new JTextField();
		this.writeMessageHere.setPreferredSize(new Dimension (150,40));
		this.sendMessage=new JButton("SendMessage");
		this.sendMessage.setPreferredSize(new Dimension(50,40));
		this.south=new Container();
		this.south.setLayout(new FlowLayout());
		this.south.add(writeMessageHere);
		this.south.add(sendMessage);
		this.userName=uName;
		this.password=pass;
		c=this.getContentPane();
		c.add(north,BorderLayout.NORTH);
		c.add(south,BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(200,200));
		this.setResizable(true);
		this.setLocation(780,10);
		this.pack();
		this.setVisible(true);
		//gui part
	}//constructor
	
	@Override
	public void anmelden(String s) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void abmelden(String s) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nachricht(String s) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getUserList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName()  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNames(List<String> a) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
