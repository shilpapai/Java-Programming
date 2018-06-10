package com.pai.multithreading.tutorial6_wait_notify;

import java.util.Scanner;


// wait() will release the lock
// notify() and notifyAll() won't release the lock
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

    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("producer started");
            wait();
            System.out.println("producer is resumed");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        synchronized (this){

            System.out.println("Consumer : Press return key to return lock..");
            scanner.nextLine();
            System.out.println("Consumer completed");
            notifyAll();
            Thread.sleep(5000);
        }
    }
}