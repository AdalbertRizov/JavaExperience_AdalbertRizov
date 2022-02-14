package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.MenuElement;
import javax.swing.table.DefaultTableModel;

import IDGenerator.Generator;
import Model.ModelShop;
import Model.Product;
public class ViewShop extends JFrame implements java.util.Observer{

	/**
	 * 
	 */
	//attributes
	private static final long serialVersionUID = 1L;
	private ModelShop model;
	
	//componenets of the view
	private Container south;
	private Container north;
	private Container c;
	private Container cName;
	private Container cPrice;
	private Container cQuantity;
	//private Container cId;
	private JLabel lName;
	private JLabel lPrice;
	private JLabel lQuantity;
	//private JLabel lId;
	private JButton deleteButton;
	private JButton addButton;
	private NameTextField name;
	private PriceTextField price;
	private QuantityTextField quantity;
	//private QuantityTextField id;
	//private JList products;
	private JTable products;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu serialization;
    private JMenu loadSave;
    private JMenu database;
    private JMenu chat;
    private JMenuItem serNone;
    private JMenuItem serBin;
    private JMenuItem serXML;
    private JMenuItem serXStream;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem jdbc;
    private JMenuItem openJpa;
	
	//constructor
	public ViewShop(ModelShop model){
		super("View Shop 0.1 from group 9 :)");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.model=model;
		
		//register the observer
		this.model.addObserver(this);
		
		//create and add the componenets
		c=this.getContentPane();
		c.setLayout(new BorderLayout());
		
		this.tableModel = new DefaultTableModel();
        this.tableModel.addColumn("Name");
        this.tableModel.addColumn("ID");
        this.tableModel.addColumn("Quantity");
        this.tableModel.addColumn("Price per piece");
        
        this.products=new JTable(this.tableModel){
        	public boolean isCellEditable(int rowIndex, int colIndex) {
        		   return false; //Disallow the editing of any cell
        		 
        	}//isCellEditable
        	};
		this.scrollPane=new JScrollPane(products);
		
		deleteButton=new JButton("Delete");
		deleteButton.setPreferredSize(new Dimension(400,20));
		this.north=new Container();
		this.north.setLayout(new BorderLayout());
		this.north.add(deleteButton,BorderLayout.SOUTH);
		
		//this.database=new JMenu("Database");
		//this.jdbc=new JMenuItem("JDBC");
		//this.openJpa=new JMenuItem("OpenJPA");
		//jdbc.setSelected(true);
		//jdbc.setActionCommand("jdbc");
		//openJpa.setSelected(false);
		//openJpa.setActionCommand("openJpa");
		//ButtonGroup databaseGroup=new ButtonGroup();
		//databaseGroup.add(jdbc);
		//databaseGroup.add(openJpa);
		//database.add(jdbc);
		//database.add(openJpa);
		
		this.serialization=new JMenu("Serializable");
		this.serNone=new JMenuItem("None");
		this.serBin=new JMenuItem("Binary");
		this.serXML=new JMenuItem("Java Beans");
		this.serXStream=new JMenuItem("X Stream");
		this.jdbc=new JMenuItem("JDBC");
		this.openJpa=new JMenuItem("OpenJPA");
		serNone.setSelected(true);
		serNone.setActionCommand("none");
		serBin.setSelected(false);
		serBin.setActionCommand("binary");
		serXML.setSelected(false);
		serXML.setActionCommand("xml");
		serXStream.setSelected(false);
		serXStream.setActionCommand("xstream");
		jdbc.setSelected(true);
		jdbc.setActionCommand("jdbc");
		openJpa.setSelected(false);
		openJpa.setActionCommand("openJpa");
		
		ButtonGroup serGroup=new ButtonGroup();
		serGroup.add(serNone);
		serGroup.add(serBin);
		serGroup.add(serXML);
		serGroup.add(serXStream);
		serGroup.add(jdbc);
		serGroup.add(openJpa);
		serialization.add(serNone);
		serialization.add(serBin);
		serialization.add(serXML);
		serialization.add(serXStream);
		serialization.add(jdbc);
		serialization.add(openJpa);

		
		ActionListener serBinlistener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				String choice=ae.getActionCommand();
				switch(choice){
					case "none" :{
						JOptionPane.showMessageDialog(null,"You must choose a serializable strategy!!");
						break;
					}
					case "binary":{
						//model.setSerStrat(new BinaryStrategy());
						break;
					}
					case "xml":{
						
						break;
					}
					case "xstream":{
						
						break;
					}
					
				}//switch
				
			}//actionPerformed
			
		};
		
		this.loadSave=new JMenu("Load/Save");
		this.load=new JMenuItem("Load");
		this.save=new JMenuItem("Save");
		save.setSelected(true);
		save.setActionCommand("save");
		load.setSelected(false);
		load.setActionCommand("load");
		ButtonGroup loadSaveGroup=new ButtonGroup();
		loadSaveGroup.add(load);
		loadSaveGroup.add(save);
		loadSave.add(load);
		loadSave.add(save);
		
		this.chat=new JMenu("Chat");
		JMenuItem ch=new JMenuItem("Chat");
		ch.setActionCommand("someCommand");
		ButtonGroup chatGroup=new ButtonGroup();
		chatGroup.add(ch);
		chat.add(ch);
		chat.setActionCommand("someCommand1");
		
		this.menuBar=new JMenuBar();
		this.menuBar.add(serialization);
		this.menuBar.add(loadSave);
		//this.menuBar.add(database);
		this.menuBar.add(chat);
		this.north.add(menuBar,BorderLayout.NORTH);
		
		addButton=new JButton("Add");
		addButton.setPreferredSize(new Dimension(100,30));
		
		name=new NameTextField();
		name.setPreferredSize(new Dimension(200,20));
		name.setText("Test Product");
		lName=new JLabel("Name :");
		cName=new Container();
		cName.setLayout(new BorderLayout());
		cName.add(lName,BorderLayout.NORTH);
		cName.add(name,BorderLayout.SOUTH);
		
		price=new PriceTextField();
		price.setPreferredSize(new Dimension(50,20));
		price.setText("1.0");
		lPrice=new JLabel("Price :");
		cPrice=new Container();
		cPrice.setLayout(new BorderLayout());
		cPrice.add(lPrice,BorderLayout.NORTH);
		cPrice.add(price,BorderLayout.SOUTH);
		
		quantity=new QuantityTextField();
		quantity.setPreferredSize(new Dimension(50,20));
		quantity.setText("1");
		lQuantity=new JLabel("Quantity :");
		cQuantity=new Container();
		cQuantity.setLayout(new BorderLayout());
		cQuantity.add(lQuantity,BorderLayout.NORTH);
		cQuantity.add(quantity,BorderLayout.SOUTH);
		
		south=new Container();
		south.setLayout(new FlowLayout());
		south.add(cName);
		//south.add(cId);
		south.add(cQuantity);
		south.add(cPrice);
		south.add(addButton);
		
		c.add(north,BorderLayout.NORTH);
		c.add(scrollPane,BorderLayout.CENTER);
		c.add(south,BorderLayout.SOUTH);
	
		//this is setting the frame itself
        this.setPreferredSize(new Dimension(500,400));
		this.setResizable(false);
		this.setLocation(600,200);
		this.pack();
		this.setVisible(true);
	}//constructor
	
//	public JMenuBar getMenuBar(){
//		return this.menuBar;
//	}//getMenuBar
	
	public JMenuItem getBinButton(){
		return this.serBin;
	}//getBinButton
	
	public void setBinButton(JMenuItem m){
		this.serBin=m;
	}//setBinButton
	
	public JMenuItem getXmlButton(){
		return this.serXML;
	}//getXMLButton
	
	public void setXmlButton(JMenuItem m){
		this.serXML=m;
	}//setXMLButton
	
	public JMenuItem getXStreamButton(){
		return this.serXStream;
	}//getXStreamButton
	
	public void setXStreamButton(JMenuItem m){
		this.serXStream =m;
	}//setXStreamButton
	
	public JMenuItem getLoadButton(){
		return this.load;
	}//getLoadButton
	
	public void setLoadButton(JMenuItem m){
		this.load=m;
	}//setLoadButton
	
	public JMenuItem getSaveButton(){
		return this.save;
	}//getSaveButton
	
	public void setSaveButton(JMenuItem m){
		this.save=m;
	}//setSaveButton
	
	public JMenuItem getNoneButton(){
		return this.serNone;
	}//getNoneButton
	
	public void setNoneButton(JMenuItem m){
		this.serNone=m;
	}//setNoneButton
	
	public NameTextField getNameTextField(){
		return this.name;
	}//getNameTextField
	
	public String getProductName(){
		return	this.name.getText();
	}//getProductName
	
	public PriceTextField getPriceTextField(){
		return this.price;
	}//getPriceTextField
	
	public double getProductPrice(){
		return Double.parseDouble(this.price.getText());
	}//getProductPrice
	
	public QuantityTextField getQuantityTextField(){
		return this.quantity;
	}//getQuantityTextField
	
	public int getProductQuantity(){
		return Integer.parseInt(this.quantity.getText());
	}//getProductQuantoty
	
	public long getProductId(){
		long id=0;
		try {
			id = (long)Generator.getID();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//this.id.setText();
		return id;
	}//getProductId
	
	public JButton getAddButton(){
		return this.addButton;	
	}//getAddButton
	
	public JButton getDeleteButton(){
		return this.deleteButton;	
	}//getAddButton
	
	public JTable getProductsList(){
		return this.products;
	}//getProductList
	
	private void clearInputCells(){
		this.name.setText("");
		//this.id.setText("");
		this.quantity.setText("");
		this.price.setText("");
		this.name.grabFocus();
	}//clearInputCells
	
	private void close(){
		MenuElement[] menuElements=this.menuBar.getSubElements();
		
	}//close
	
	private boolean validateInput(){
		boolean validInput=false;
		boolean validName=true;
		boolean validPrice=true;
		boolean validQuantity=true;
		if(this.name.getText().length()==0){
			
			this.name.grabFocus();
			validName=false;
		}
		if(this.price.getText().length()==0){
			this.price.grabFocus();
			validPrice=false;
		}
		if(this.quantity.getText().length()==0){
			this.quantity.grabFocus();
			validQuantity=false;
		}
		if((validName==false)||(validPrice==false)||(validQuantity==false)){
			validInput=false;
		}
		if((validName==true)&&(validPrice==true)&&(validQuantity==true)){
			validInput=true;
		}
		return validInput;
	}//validateInput
	
	public Product getProduct(){
		Product p=new Product();
		if(this.validateInput()==true){
			p.setName(this.getProductName());
			p.setPrice(this.getProductPrice());
			p.setQuantity(this.getProductQuantity());
			//the next line enables the generating of id
			//p.setId(this.getProductId());
			this.clearInputCells();
			return p;
		}else{
			JOptionPane.showMessageDialog(null,"Please fill in the required fields !!!");
			return null;
		}
	}//getProduct
	
	@Override
	public void update(Observable o, Object arg) {
		// Make the table model empty
        tableModel.getDataVector().clear();

        // Add data to the new model
        for (int i = 0; i < model.getProducts().size(); i++) {
            Vector<Object> rowData = new Vector<Object>();
            if(model.getProducts().get(i)!=null){
            	rowData.add(model.getProducts().get(i).getName());
                rowData.add(model.getProducts().get(i).getId());
                rowData.add(model.getProducts().get(i).getQuantity());
                rowData.add(model.getProducts().get(i).getPrice());
                tableModel.addRow(rowData);
            }
            
        }
        this.pack();
        this.repaint();
	}//update
	
	public void addActionListener(ActionListener a) {
		//this.name.addActionListener(a);
		this.deleteButton.addActionListener(a);
		this.addButton.addActionListener(a);
		this.serNone.addActionListener(a);
		this.serBin.addActionListener(a);
		this.serXML.addActionListener(a);
		this.serXStream.addActionListener(a);
		this.load.addActionListener(a);
		this.save.addActionListener(a);
		this.jdbc.addActionListener(a);
		this.chat.addActionListener(a);
	}//addActionListener
	

}
