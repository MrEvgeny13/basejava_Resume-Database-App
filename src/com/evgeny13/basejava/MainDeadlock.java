package com.evgeny13.basejava;

public class MainDeadlock {

    public static final String LOCK_1 = "lock_one";
    public static final String LOCK_2 = "lock_two";

    public static void main(String[] args) {
        deadLock(LOCK_1, LOCK_2);
        deadLock(LOCK_2, LOCK_1);
    }

    private static void deadLock(Object lock_one, Object lock_two) {
        new Thread(() -> {
            System.out.println(getThreadName() + " пытается захватить " + lock_one);
            synchronized (lock_one) {
                System.out.println(getThreadName() + " захватил " + lock_one);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getThreadName() + " пытается захватить " + lock_two);
                synchronized (lock_two) {
                    System.out.println(getThreadName() + " захватил " + lock_two);
                }
            }
        }).start();
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
