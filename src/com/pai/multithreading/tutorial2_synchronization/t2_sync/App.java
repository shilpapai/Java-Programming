package com.pai.multithreading.tutorial2_synchronization.t2_sync;

public class App {

    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.doWork();

    }

    public void doWork() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    incrementCount();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    incrementCount();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Count : " + count);

    }

    private synchronized void incrementCount() {
        count++;
    }
}
