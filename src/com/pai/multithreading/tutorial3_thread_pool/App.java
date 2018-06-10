package com.pai.multithreading.tutorial3_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);


        for (int i = 0; i < 5; i++) {
            executorService.submit(new Processor(i));
        }
        executorService.shutdown();
        System.out.println("All tasks submitted");
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("All tasks completed");

    }

}
