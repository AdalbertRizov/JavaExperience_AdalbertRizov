package main;

import java.util.concurrent.locks.Lock;

public class Thread2 extends Thread{

	Lock lock1;
	Lock lock2;

	public Thread2(Lock lock1, Lock lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	public void run() {
		// Setzt lock auf lock2
		lock2.lock();
		System.out.println("T2: Lock auf lock2 bekommen");
		
		// Wird auf lock1 keinen lock setzen k�nnen, da Thread1 diesen h�lt
		// DEADLOCK
		lock1.lock();
		System.out.println("T2: Lock auf lock1 bekommen");

		lock1.unlock();
		lock2.unlock();
	}
}
