package main;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Thread1 extends Thread {

	Lock lock1;
	Lock lock2;

	public Thread1(Lock lock1, Lock lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	public void run() {
		// Setzt lock auf lock1
		lock1.lock();
		System.out.println("T1: Lock auf lock1 bekommen");

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		}
		// Wird auf lock2 keinen lock setzen k�nnen da Thread2 diesen h�lt
		// DEADLOCK
		lock2.lock();
		System.out.println("T1: Lock auf lock2 bekommen");

		lock2.unlock();
		lock1.unlock();
	}
}
