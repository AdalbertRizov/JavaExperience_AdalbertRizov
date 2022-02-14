package main;

public class Customer {
	//attributes
	private static int lastCustomerIndex=1;
	private int myNumber;
	private int moneyToSpend;
	//constructor
	public Customer(){
		this.myNumber=lastCustomerIndex;
		this.moneyToSpend=Variables.getRandomAmmoutOfMoney();
		System.out.println("I am client number "+this.myNumber
						  +",I will spend "+this.moneyToSpend+" EURO");
		lastCustomerIndex++;
	}//constructor
	
	//methods
	public int getCustomerNumber(){
		return this.myNumber;
	}//getCustomerNumber
	
	public int getMoneyToSpend(){
		return this.moneyToSpend;
	}//getMoneyToSpend
}//class
