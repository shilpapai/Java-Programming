package com.pai.multithreading.tutorial7_low_level_synchronization;

import java.util.LinkedList;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Processor processor = new Processor();


        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}

class Processor{

    private Object lock = new Object();
    Random random = new Random();

    LinkedList<Integer> list = new LinkedList<>();
    private static final Integer LIMIT = 10;

    public void produce() throws InterruptedException {
        while (true){

            synchronized (lock) {

                if(list.size() == LIMIT){
                    lock.wait();
                }

                int i = Math.abs(random.nextInt() % 100);
                list.add(i);
                System.out.println("Produced : " + i + " List size : " + list.size());
                lock.notify();
                Thread.sleep(200);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true){
            System.out.println("Consuming .. ");
            synchronized (lock){
                if(list.size() == 0){
                    lock.wait();
                }
                Integer i = list.removeFirst();
                System.out.println("Consumed : " + i + " Size of list : " + list.size());
                lock.notify();
                Thread.sleep(500);
            }

        }
    }
}
