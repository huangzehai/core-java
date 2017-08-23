package com.hzh.corejava.concurrent.join;

public class JoinTest {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
                System.out.println("end thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
		
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end main");
	}

}
