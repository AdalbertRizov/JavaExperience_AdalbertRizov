package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Vector;
import Client.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ModelShop;

public class ViewCustomer extends JFrame implements ClockInterface,java.util.Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModelShop model;
	private static int customerCount=1;
	
	//components of the view
	private Container c;
	private Container north;
	private Container center;
	private Container south;
	private JButton buyButton;
	private JTable products;
	private  Clock clock;
	private JLabel clockLabel;
	//private DatagramClient dateClient;
    public DefaultTableModel tableModelProducts;
    private JScrollPane scrollPaneProducts;
    private JTable order;
    private DefaultTableModel tableModelOrder;
    private JScrollPane scrollPaneOrder;
    
    public ViewCustomer(ModelShop m){
    	super("View Customer "+customerCount+ " from group 9 :)");
    	//JOptionPane.showMessageDialog(null,"Breakpoint 1");
    	customerCount++;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//add the model
    	this.model=m;
    	
    	//register the observer
    	model.addObserver(this);
    	
   		//the table with the products, here the customer can order
		this.tableModelProducts = new DefaultTableModel();
        this.tableModelProducts.addColumn("Name");
        this.tableModelProducts.addColumn("Price");
        this.tableModelProducts.addColumn("Available");
        this.tableModelProducts.addColumn("Order");
        this.products=new JTable(this.tableModelProducts){
        	public boolean isCellEditable(int rowIndex, int colIndex) {
        		if(colIndex==3){
        			return true;
        		}else{
        			 return false; //Disallow the editing of any cell
        		}
     		}//isCellEditable
        };
		this.scrollPaneProducts=new JScrollPane(products);
		this.center=new Container();
		this.center.setLayout(new BorderLayout());
		this.center.add(scrollPaneProducts,BorderLayout.CENTER);
		this.center.setPreferredSize(new Dimension(500,200));
		this.center.setVisible(true);
		//the table with the things already order
		this.tableModelOrder = new DefaultTableModel();
        this.tableModelOrder.addColumn("Product");
        this.tableModelOrder.addColumn("Price");
        this.tableModelOrder.addColumn("Ordered");
        this.tableModelOrder.addColumn("Total Price");
        this.order=new JTable(this.tableModelOrder){
        	public boolean isCellEditable(int rowIndex, int colIndex) {
     		   return false; //Disallow the editing of any cell
        	}//isCellEditable
        	};
		this.scrollPaneOrder=new JScrollPane(order);
		this.north=new Container();
		this.north.setLayout(new BorderLayout());
		this.north.add(scrollPaneOrder,BorderLayout.CENTER);
		this.north.setPreferredSize(new Dimension(500,200));
		this.north.setVisible(true);
    	//the buy  button
		this.buyButton=new JButton("Buy");
		this.buyButton.setPreferredSize(new Dimension(300,30));
		this.buyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}//actionPerformed
		});
    	this.south=new Container();
    	this.south.setLayout(new BorderLayout());
    	this.south.add(buyButton,BorderLayout.NORTH);
    	//JOptionPane.showMessageDialog(null,"Breakpoint 2");
    	//the clock
    	this.clock=new Clock(this);
    	Thread clockThread=new Thread(clock);
    	clockThread.start();
    	//JOptionPane.showMessageDialog(null,"Breakpoint 3");
    	
    	clockLabel = new JLabel("Here will be the clock");
    	
    	//unite the clock and the buy button in one container
    	this.south.setPreferredSize(new Dimension(300,60));
    	this.south.add(clockLabel,BorderLayout.CENTER);
    	this.south.setVisible(true);
    	//JOptionPane.showMessageDialog(null,"Breakpoint 4");
    	//unite them in one
    	//the components of the view
    	c=this.getContentPane();
//		c.setLayout(new BorderLayout());
//		c.add(scrollPaneOrder,BorderLayout.NORTH);
//    	c.add(scrollPaneProducts,BorderLayout.CENTER);
//    	c.add(buyButton,BorderLayout.SOUTH);
    	c.add(north,BorderLayout.NORTH);
    	c.add(center,BorderLayout.CENTER);
    	c.add(south,BorderLayout.SOUTH);
    	//make the frame
    	this.setPreferredSize(new Dimension(500,440));
		this.setResizable(true);
		this.setLocation(50,50);
		this.pack();
		this.setVisible(true);
		//this.tcpClient = new TCPClient();
	}//constructor
    
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		// Make the table model empty
        tableModelProducts.getDataVector().clear();

        // Add data to the new model
        for (int i = 0; i < model.getProducts().size(); i++) {
            Vector<Object> rowData = new Vector<Object>();
            if(model.getProducts().get(i)!=null){
            	rowData.add(model.getProducts().get(i).getName());
            	rowData.add(model.getProducts().get(i).getPrice());
                rowData.add(model.getProducts().get(i).getQuantity());
                //rowData.add(model.getProducts().get(i).getId());
                tableModelProducts.addRow(rowData);
            }
            
        }
        this.pack();
        this.repaint();
	}//update
	
	public void addActionListener(ActionListener a) {
		//this.addActionListener(a);
		this.buyButton.addActionListener(a);
	}//addActionListener

	@Override
	public void setClock(String s) {
		// TODO Auto-generated method stub
		this.clockLabel.setText(s);
	}//setClock

}
