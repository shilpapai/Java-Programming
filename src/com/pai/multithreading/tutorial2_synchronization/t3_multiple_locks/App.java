package com.pai.multithreading.tutorial2_synchronization.t3_multiple_locks;

public class App {
    public static void main(String[] args) {

        // synchronized method will lock current object
        new SyncMethodWorker().main();

        // synchronized block will lock object passed into its argument
        new SyncBlockWorker().main();
    }
}
