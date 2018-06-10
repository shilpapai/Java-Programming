package com.pai.multithreading.tutorial10_semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class App {

    public static void main(String[] args) {
        Connection connection = Connection.getInstance();
        ExecutorService service = newCachedThreadPool();
        for (int i = 0; i < 200; i++) {
            service.submit(() -> {
                try {
                    connection.connect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }
}

class Connection {

    private static Connection connection = new Connection();
    private int connections = 0;

    private Semaphore semaphore = new Semaphore(10, true);

    private Connection() {
    }

    public static Connection getInstance() {
        return connection;
    }

    public void connect() throws InterruptedException {
        try {
            semaphore.acquire();
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    private void doConnect() throws InterruptedException {

        synchronized (this) {
            connections++;
            System.out.println("Current connections : " + connections);
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            connections--;
        }
    }

}
