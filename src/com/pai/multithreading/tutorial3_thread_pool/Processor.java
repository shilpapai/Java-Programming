package com.pai.multithreading.tutorial3_thread_pool;

public class Processor implements Runnable {

    private int id;
    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting processor " + id);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed processor " + id);
    }
}
