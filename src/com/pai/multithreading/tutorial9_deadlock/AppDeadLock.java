package com.pai.multithreading.tutorial9_deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppDeadLock {

    public static void main(String[] args) throws InterruptedException {
        RunnerSolution runner = new RunnerSolution();
        Thread first = new Thread(() -> {
            try {
                runner.first();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread second = new Thread(() -> {
            try {
                runner.second();
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    private AccountSolution account1 = new AccountSolution();
    private AccountSolution account2 = new AccountSolution();
    Random random = new Random();
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public void first() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            lock1.lock();
            Thread.sleep(1000);
            lock2.lock();
            try {
                AccountSolution.transfer(account1,account2,random.nextInt(100));
            }finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void second() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            lock2.lock();
            lock1.lock();
            try {
                AccountSolution.transfer(account1,account2,random.nextInt(100));
            }finally {
                lock2.unlock();
                lock1.unlock();
            }
        }

    }


    public void finished() {
        System.out.println("AccountSolution 1 balance : " + account1.getBalance());
        System.out.println("AccountSolution 2 balance : " + account2.getBalance());
        System.out.println("Total balance : " + (account1.getBalance() + account2.getBalance()));
    }
}


class Account {

    private double balance = 10000;

    public double getBalance() {
        return balance;
    }

    public void withdraw(double withdraw) {
        this.balance -= withdraw;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public static void transfer(AccountSolution from, AccountSolution to, double amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }

}
