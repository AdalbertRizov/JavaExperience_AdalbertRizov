package main;

import java.util.concurrent.CopyOnWriteArrayList;

public class KassenList extends CopyOnWriteArrayList<Kasse> implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//attributes
	private boolean closeAllKassen=false;
	private int totalBalance;
	
	//constructor
	public KassenList(Customer c){
		this.totalBalance=0;
		this.openNewKasse(c);
	}//constructor
	
	//methods
	public synchronized int getNumberOfKassenCurrentlyOpened(){
		return this.size();
	}//getNumberOfKassenCurrentlyOpened
	
	public synchronized void openNewKasse(Customer c){
		Kasse newKasse=new Kasse(c);//the "producer" must know to which queue to send the customer
		Thread t=new Thread(newKasse);
		t.start();
		this.add(newKasse);
	}//openNewKasse
	
	public synchronized boolean stopAkquise(){
		boolean result=false;
		for(Kasse k : this){
			if(k.getNumberOfPeopleWaiting()>=Variables.CustomerMaxNumber){
				result=true;
			}
		}//for		
		return result;
	}//stopAkquise
	
	public synchronized boolean newKasseMustBeOpened(){
		boolean aLotOfPeopleWaiting=false;
		boolean maximumNumberOfKasenReached=false;
		boolean mustOpenNewKasse=false;
		if(this.size()>=Variables.KasseMaxNumber){
			maximumNumberOfKasenReached=true;
		}
		for(Kasse k:this){
			if(k.getNumberOfPeopleWaiting()>=Variables.MustOpenNewKasse){
				aLotOfPeopleWaiting=true;
			}
		}
		mustOpenNewKasse=(aLotOfPeopleWaiting&&(!maximumNumberOfKasenReached));
		return mustOpenNewKasse;
	}//newKasseMustBeOpened
	
	public synchronized Kasse getKasseWithSmallestQueue(){
		Kasse kasseWithSmallestQueue=this.get(0);
			for(Kasse k :this){
				if(k.getNumberOfPeopleWaiting()<kasseWithSmallestQueue.getNumberOfPeopleWaiting()){
					kasseWithSmallestQueue=k;
				}
			}
		return kasseWithSmallestQueue;
	}//
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!closeAllKassen){
			if(this.stopAkquise()){
				stop();
				continue;
			}		
		}//while
	}//run
	
	private void stop(){
		this.closeAllKassen=true;
	}//stop
	
	private void calculateBalance(){
		for(Kasse k :this){
			this.totalBalance=this.totalBalance
					+k.getKasseBalance();
		}
	}//calculateBalance
	
	public int getBalance(){
		this.calculateBalance();
		return this.totalBalance;
	}//getBalance
	
	public void printBalance(){
		this.calculateBalance();
		System.out.println("The total Balance is :"+this.totalBalance);
	}//printBalance
}//class
