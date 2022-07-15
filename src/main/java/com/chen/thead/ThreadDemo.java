package com.chen.thead;

/**
 * 练习： 创建两个分线程， 其中一个线程遍历100以内的偶数，一个线程遍历100以内的奇数
 */
public class ThreadDemo {
    public static void main(String[] args) {
//        MyThead1 myThead1 = new MyThead1();
//        MyThead2 myThead2 = new MyThead2();
//
//        myThead1.start();
//        myThead2.start();

        //创建Thread类的匿名子类
/*
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();
        */

        //该方法详解见第二种创建线程的方式（ThreadTest）
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }).start();
    }
}

class  MyThead1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class  MyThead2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}