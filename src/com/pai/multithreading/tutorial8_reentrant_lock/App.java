package com.pai.multithreading.tutorial8_reentrant_lock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.second();
            }
        });


        first.start();
        second.start();

        first.join();
        second.join();

        runner.finished();
    }
}

class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void first() throws InterruptedException {
        lock.lock();

        System.out.println("Waiting");
        condition.await();
        System.out.println("Woken up..");

        try {
            increment();
            System.out.println("first done");
        } finally {
            lock.unlock();
        }
    }

    public void second() {

        lock.lock();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press return to notify");
        scanner.nextLine();
        condition.signal();
        System.out.println("Signalled");

        try {
            increment();
            System.out.println("second done");
        } finally {
            lock.unlock();
        }
    }


    public void finished() {
        System.out.println("Count : " + count);
    }
}

