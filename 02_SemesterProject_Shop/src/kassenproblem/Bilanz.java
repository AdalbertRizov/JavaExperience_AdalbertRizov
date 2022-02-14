package kassenproblem;

import java.util.concurrent.CopyOnWriteArrayList;

public class Bilanz extends  CopyOnWriteArrayList<Kasse>{
	
	//constructor
	public Bilanz(){
		
	}//constructor
	
	//methods
	public void addKasseToBilanz(Kasse k){
		this.add(k);
	}//addKasseToBilanz
	
	public synchronized void printCurrentBilanz(){
		int currentNumberOfKassen=this.size();
		System.out.println("Bilanz :");
		for(int i=0;i<currentNumberOfKassen;i++){
			int tempBestBal=this.get(i).getKasseBalance();
			int indexOfBestBal=i;
			for(int j=i;j<currentNumberOfKassen;j++){
				if(this.get(j).getKasseBalance()>tempBestBal){
					this.add(i, this.get(j));
					Kasse temp=this.get(i+1);
					this.remove(i+1);
					this.remove(j);
					this.add(j, temp);
				}
			}//inside loop
			System.out.print("Kasse "+this.get(indexOfBestBal).getKasseIDNumber()
					+":"+this.get(indexOfBestBal).getKasseBalance()+" EURO||");
		}//for
		System.out.println("");
	}//printCurrentBilanz
	
	public synchronized int getTotalBalance(){
		int totalBalance=0;
		int currentNumberOfKassen=this.size();
		for(int i=0;i<currentNumberOfKassen;i++){
			totalBalance+=this.get(i).getKasseBalance();
		}
		return totalBalance;
	}//getTotalBalance
	
	public synchronized int getTotalCustomerCount(){
		int totalCustomerCount=0;
		int currentNumberOfKassen=this.size();
		for(int i=0;i<currentNumberOfKassen;i++){
			totalCustomerCount+=this.get(i).getCustomerCount();
		}
		return totalCustomerCount;
	}//getTotalBalance
}//class
