package com.pai.multithreading.tutorial11_callable_future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args){
        Random random = new Random();
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            int duration = random.nextInt(100);
            futures.add(service.submit(() -> {
                System.out.println("Starting to run ");
                try {
                   /* if(duration > 50){
                        throw new Exception("duration is too long");
                    }*/
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished ");
                return duration;
            }));
        }
        service.shutdown();
        //service.awaitTermination(1000,TimeUnit.MILLISECONDS);

        for (Future f: futures) {
            Object o = null;
            try {
                o = f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("Future : " + o);
        }
    }
}
