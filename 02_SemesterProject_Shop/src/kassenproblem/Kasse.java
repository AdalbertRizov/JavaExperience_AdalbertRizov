package kassenproblem;

import java.util.concurrent.CopyOnWriteArrayList;

public class Kasse extends CopyOnWriteArrayList<Customer> implements Runnable{

	//attributes
	private char uniqueID;
	private static char lastOpenedKasseUniqueID='A';
	private int kasseIDNumber;
	private static int lastOpenedKasseIDNumber=0;
	private boolean stopServing;
	private int numberOfServedCustomers;
	private int kasseBalance;
	private static Bilanz totalBilanz=new Bilanz();;
	private static int numberOfActiveKassen=Variables.KasseMaxNumber;
	
	//constructor
	public  Kasse(Customer c){
		//the kasse must know who is waiting at her queue
		synchronized(this){
			this.uniqueID=lastOpenedKasseUniqueID;
			lastOpenedKasseUniqueID++;
			this.kasseIDNumber=lastOpenedKasseIDNumber;
			lastOpenedKasseIDNumber++;
			this.stopServing=false;
			this.numberOfServedCustomers=0;
			this.kasseBalance=0;
			this.enqueue(c);
			System.out.print("Kasse "+this.kasseIDNumber
							+" is opening now.....");
			printQueueStatus();
			totalBilanz.add(this);
		}//synchronized
		
		try{
			Thread.sleep((long)Variables.getKasseOpeningTime());
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}//constructor
	
	//methods
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Kasse "+this.kasseIDNumber+" started!!");
		while(!stopServing){
			synchronized(this){
				if (this.isEmpty()==true){
					System.out.println("\nAll Customers Served!!Kasse "+this.kasseIDNumber
										+" is closing");
					System.out.println("Kasse "+this.kasseIDNumber
										+": Number of served customers :"
										+this.numberOfServedCustomers	
										+";Cash in the drawer:"
										+this.kasseBalance+" EURO\n");
					totalBilanz.printCurrentBilanz();
					numberOfActiveKassen--;
					if(numberOfActiveKassen==0){
						System.out.print("\nFinal ");
						totalBilanz.printCurrentBilanz();
						System.out.println("Total ammount of money in the Bilanz:"
										+totalBilanz.getTotalBalance()+" EURO");
						System.out.println("Total number of customers served:"
								+totalBilanz.getTotalCustomerCount());
					}
					stop();
					continue;
				} else {
					Customer c=(Customer)this.get(0);
					this.serve(c);
					this.remove(0);
//					synchronized(totalBilanz){
//						Customer c=(Customer)this.get(0);
//						this.serve(c);
//						this.remove(0);
//					}//synchronized for totalBilanz
					
				}
			}//synchronized
			try{
				Thread.sleep((long)Variables.getRandomServeTime());
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}//while
		
	}//run
	
	private synchronized void serve(Customer c){
		System.out.print("Kasse "+this.kasseIDNumber
						+":I am happy to serve you client number "
						+c.getCustomerNumber()+"!!");
		printQueueStatus();
		numberOfServedCustomers++;//count the customers
		this.kasseBalance+=c.getMoneyToSpend();//add the money which the customer has spend
		totalBilanz.printCurrentBilanz();
	}//serve
	
	private synchronized void stop(){
		stopServing=true;
	}//stop
	
	public  Customer dequeue(){
		if (this.isEmpty()==true){
			return null;
		}else{
			return this.remove(0);
		}
	}//dequeue
	
	public  void enqueue(Customer c){
		this.add(c);
	}//enqueue
	
	public synchronized int getNumberOfPeopleWaiting(){
		return this.size();
	}//numberOfPeopleWaiting
	
	private synchronized void printQueueStatus(){
		int numberOfPeoplewaiting=this.getNumberOfPeopleWaiting();
		System.out.print(" Queue Status :(");
		for(int i=0;i<8;i++){
			if(numberOfPeoplewaiting>0){
				System.out.print("X|");
				numberOfPeoplewaiting--;
			}else{
				System.out.print("O|");
			}
		}
		System.out.print(")\n");
	}//printQueueStatus
	
	public synchronized int getKasseBalance(){
		return this.kasseBalance;
	}//getKasseBalance
	
	public synchronized int getKasseIDNumber(){
		return this.kasseIDNumber;
	}//getKasseIDNumber
	
	public synchronized void setKasseIDNumber(int newKasseIDNumber){
		this.kasseIDNumber=newKasseIDNumber;
	}//setKasseIDNumber
	
	public synchronized char getUniqueId(){
		return this.uniqueID;
	}//getUniqueId
	
	public synchronized int getCustomerCount(){
		return this.numberOfServedCustomers;
	}//getCustomerCount
}//class
