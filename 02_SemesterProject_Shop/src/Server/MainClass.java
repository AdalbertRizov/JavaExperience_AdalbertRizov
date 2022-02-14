package Server;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Warehouse myWarehouse=new Warehouse();
		Thread warehouseThread=new Thread(myWarehouse);
		warehouseThread.start();
	}

}
