package main;

public class Akquise implements Runnable{

	//attributes
	private KassenList  kassenList;
	private boolean stopProducing;
	
	//constructor
	public Akquise(){
		System.out.println("Akquise started!!");
		Customer c=new Customer();
		this.kassenList=new KassenList(c);
		Thread t=new Thread(this.kassenList);
		t.start();
		this.stopProducing=false;
	}//constructor
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!stopProducing){
			synchronized(kassenList){
				if (kassenList.stopAkquise()){
					//kassenList.printBalance();
					stop();
					continue;
				}else{
					Customer c=new Customer();
					if (kassenList.newKasseMustBeOpened()){
						kassenList.openNewKasse(c);
					}else{
						
						kassenList.getKasseWithSmallestQueue().enqueue(c);
					}
				}//else
			}//synchronized
			
			try{
				Thread.sleep((long)Variables.getRandomAkquiseTime());
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}//while
		System.out.println("Akquise beendet!!");
	}//run
	
	private void stop(){
		stopProducing=true;
	}//stop
}
