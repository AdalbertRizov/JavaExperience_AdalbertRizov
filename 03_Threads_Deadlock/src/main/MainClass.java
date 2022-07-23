package main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MainClass {


	public static void main(String[] args) {
		Lock lock1 = new ReentrantLock(); 
        Lock lock2 = new ReentrantLock(); 
        
        Thread t1 = new Thread1(lock1, lock2);
        Thread t2 = new Thread2(lock1, lock2);

        t1.start();
        t2.start();
			
	}

}
