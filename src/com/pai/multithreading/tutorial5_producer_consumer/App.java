package com.pai.multithreading.tutorial5_producer_consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class App {
    public static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }

    public static void producer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            int i = Math.abs(random.nextInt() % 100);
            queue.put(i);
            System.out.println("Produced " + i);
        }

    }

    public static void consumer() throws InterruptedException {
        Random r = new Random();
        while (true) {
            Thread.sleep(500);
            Integer i = queue.take();
            System.out.println("Consumed : " + i + " Queue size : " + queue.size());

        }
    }
}

