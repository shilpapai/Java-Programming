package com.pai.multithreading.tutorial1_start.demo3;

public class App {

    public static void main(String[] args) {
        Thread r1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " Number : " + i);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread r2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " Number : " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        r1.start();
        r2.start();
    }
}
