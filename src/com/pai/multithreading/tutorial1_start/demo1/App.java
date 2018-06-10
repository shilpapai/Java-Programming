package com.pai.multithreading.tutorial1_start.demo1;

public class App {
    public static void main(String[] args) {
        Runner r1 = new Runner();
        Runner r2 = new Runner();

        r1.start();
        r2.start();
    }
}


class Runner extends Thread{
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread " + Thread.currentThread().getName() + " Number : " + i);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}