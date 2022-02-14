package main;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Variables var=new Variables();
		Akquise aq=new Akquise();
		Thread t1=new Thread(aq);
		t1.start();
	}

}
