package com.pai.multithreading.tutorial4_countdown_latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService service = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            service.submit(new Processor(latch));
        }
        latch.await();
        /*service.shutdown();
        System.out.println("Submitted all tasks") ;
        service.awaitTermination(1, TimeUnit.HOURS);
*/
        System.out.println("Completed all tasks") ;
    }
}

class Processor implements Runnable{

    private CountDownLatch c;

    public Processor(CountDownLatch c) {
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println("Starting") ;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        c.countDown();
        System.out.println("Completing") ;
    }
}
