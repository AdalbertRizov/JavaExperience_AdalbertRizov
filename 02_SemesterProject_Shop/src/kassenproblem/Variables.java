package kassenproblem;

import java.util.Random;

public class Variables {
	private static Random r= new Random();
	public static final int KasseMaxNumber=6;
	public static final int CustomerMaxNumber=8;
	public static final int MustOpenNewKasse=6;
	
	//methods
	public static long getRandomAkquiseTime(){
		//the next line controls how fast the customers are produced
		//long randomAkquiseTime=r.nextLong()%2000;
		long randomAkquiseTime=r.nextLong()%1000;
		if(randomAkquiseTime<0){
			return -randomAkquiseTime;
		}else{
			return randomAkquiseTime;
		}
	}//getRandomAkquiseTime
	
	public static long getRandomServeTime(){
		//the next line controls how fast the customers are served
		long randomServeTime=6000+r.nextLong()%4000;
		//long randomServeTime=r.nextLong()%4000;
		if(randomServeTime<0){
			return -randomServeTime;
		}else{
			return randomServeTime;
		}
	}//getRandomServeTime
	
	public static long getKasseOpeningTime(){
		return 6000;
	}// getKasseOpeningTime
	
	public static int getRandomAmmoutOfMoney(){
		int moneyToSpend=1+r.nextInt()%100;
		if(moneyToSpend<0){
			return -moneyToSpend;
		}else{
			return moneyToSpend;
		}
	}//getRandomAmmoutOfMoney
}
