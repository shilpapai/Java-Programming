package com.pai.multithreading.tutorial12_interrupting_thread;

import java.util.Random;

public class App {

    
    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        Thread thread = new Thread(() -> {
            System.out.println("Started");
            int i = random.nextInt(100);
            for (int j = 0; j < 100000000; j++) {
                /*if(Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted");
                    break;
                }*/

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    break;
                }

                Math.sin(random.nextDouble());
            }
            System.out.println("Finished");
        });

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
    }
}
