package com.chen.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 例子: 创建三个窗口买票， 总数为100张
 *
 * 存在线程安全问题
 *
 * 1. 问题: 买票的过程中，出现了重票、错票的问题
 * 2. 原因: 当某个线程操作车票的过程中，尚未操作完成时，其他线程参与进来，也操作车票。
 * 3. 如何解决: 当一个线程A在操作tickets时，其他线程不能参与进来。直到该线程A操作完ticket时，其他线程才可以开始操作ticket。
 *      方式一: 同步代码块
 *
 *      synchronized(同步监视器) {
 *          //需要被同步的代码
 *      }
 *      说明：1. 操作共享数据的代码，即为需要被同步的代码。 -->不能包含代码多了，也不能包含代码少了。
 *          2. 共享数据：多个线程共同操作的变量。比如tickets就是共享数据
 *          3. 同步监视器，俗称:锁。任何一个类的对象都可以充当锁。
 *              要求：多个线程必须要公用同一把锁
 *
 *              补充：在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器。
 *              说明：在继承Thread类的方式中，尽量避免使用this充当同步监视器，可以使用当前类。
 *
 *      方式二: 同步方法
 *          说明：
 *          1. 同步方法仍然涉及到同步监视器，只是不需要显示的声明。
 *          2. 非静态的同步方法，同步监视器是：this
 *              静态的同步方法，监视器是：当前对象的类
 * 5. 同步的方式，解决了线程的安全问题。 ---好处
 *      操作同步代码时，只能由一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。 ---局限性
 */
public class WindowTest {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 使用Thread方法实现
     *
     * 存在线程安全问题
     */
    @Test
    public void testWindowByThread() throws InterruptedException {
        WindowByThread windowByThread1 = new WindowByThread();
        WindowByThread windowByThread2 = new WindowByThread();
        WindowByThread windowByThread3 = new WindowByThread();

        windowByThread1.setName("窗口1");
        windowByThread2.setName("窗口2");
        windowByThread3.setName("窗口3");

        windowByThread1.start();
        windowByThread2.start();
        windowByThread3.start();

        windowByThread1.join();
        windowByThread2.join();
        windowByThread3.join();
    }

    /**
     * 使用实现Runnable接口的方法实现
     *
     * 存在线程安全问题
     */
    @Test
    public void testWindowByRunnable() throws InterruptedException {
        WindowByRunnable windowByRunnable = new WindowByRunnable();

        Thread thread1 = new Thread(windowByRunnable);
        Thread thread2 = new Thread(windowByRunnable);
        Thread thread3 = new Thread(windowByRunnable);

        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

    /**
     * 通过同步代码块来解决Thread方式的线程安全问题
     */
    @Test
    public void testThreadSafetyBySynchronizedCodeBlock() throws InterruptedException {
        ThreadSafetyWindowByThreadAndSynchronizedCodeBlock windowByThread1 = new ThreadSafetyWindowByThreadAndSynchronizedCodeBlock();
        ThreadSafetyWindowByThreadAndSynchronizedCodeBlock windowByThread2 = new ThreadSafetyWindowByThreadAndSynchronizedCodeBlock();
        ThreadSafetyWindowByThreadAndSynchronizedCodeBlock windowByThread3 = new ThreadSafetyWindowByThreadAndSynchronizedCodeBlock();

        windowByThread1.setName("窗口1");
        windowByThread2.setName("窗口2");
        windowByThread3.setName("窗口3");

        windowByThread1.start();
        windowByThread2.start();
        windowByThread3.start();

        windowByThread1.join();
        windowByThread2.join();
        windowByThread3.join();
    }

    /**
     * 通过同步代码块解决来Runnable方式的线程安全问题
     */
    @Test
    public void testRunnableSafetyBySynchronizedCodeBlock() throws InterruptedException {
        ThreadSafetyWindowByRunnableAndSynchronizedCodeBlock windowByRunnable =
                new ThreadSafetyWindowByRunnableAndSynchronizedCodeBlock();

        Thread thread1 = new Thread(windowByRunnable);
        Thread thread2 = new Thread(windowByRunnable);
        Thread thread3 = new Thread(windowByRunnable);

        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

    /**
     * 通过同步方法来解决Thread方式的线程安全问题
     */
    @Test
    public void testThreadSafetyBySynchronizedMethod() throws InterruptedException {
        ThreadSafetyWindowByThreadAndSynchronizedMethod windowByThread1 = new ThreadSafetyWindowByThreadAndSynchronizedMethod();
        ThreadSafetyWindowByThreadAndSynchronizedMethod windowByThread2 = new ThreadSafetyWindowByThreadAndSynchronizedMethod();
        ThreadSafetyWindowByThreadAndSynchronizedMethod windowByThread3 = new ThreadSafetyWindowByThreadAndSynchronizedMethod();

        windowByThread1.setName("窗口1");
        windowByThread2.setName("窗口2");
        windowByThread3.setName("窗口3");

        windowByThread1.start();
        windowByThread2.start();
        windowByThread3.start();

        windowByThread1.join();
        windowByThread2.join();
        windowByThread3.join();
    }

    /**
     * 通过同步方法解决来Runnable方式的线程安全问题
     */
    @Test
    public void testRunnableSafetyBySynchronizedMethod() throws InterruptedException {
        ThreadSafetyWindowByRunnableAndSynchronizedMethod windowByRunnable =
                new ThreadSafetyWindowByRunnableAndSynchronizedMethod();

        Thread thread1 = new Thread(windowByRunnable);
        Thread thread2 = new Thread(windowByRunnable);
        Thread thread3 = new Thread(windowByRunnable);

        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

    static class WindowByThread extends Thread {
        private static int tickets = 100;

        @Override
        public void run() {
            while (true) {
                if (tickets > 0) {

                    //放大重票、错票的问题出现的概率
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(getName() + ": 买票， 票号为: " + tickets );
                    tickets--;
                } else {
                    break;
                }
            }

        }
    }

    static class WindowByRunnable implements Runnable{
        private int tickets = 100;
        @Override
        public void run() {
            while (true) {
                if (tickets > 0) {

                    //放大重票、错票的问题出现的概率
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ": 买票， 票号为: " + tickets);
                    tickets--;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 通过同步代码块来实现线程安全的继承Thread类实现Window类
     */
    static class ThreadSafetyWindowByThreadAndSynchronizedCodeBlock extends Thread {
        private int tickets = 100;
        //此时每个线程都对应一个window对象，每个window对象都有其自己对应的object，因此无法保证线程安全
        //Object object = new Object();

        //使用如下方法，将object设为该类的静态变量，可以保证三个线程对象公用一个object，如同三个线程对象公用一套tickets
        private static Object object = new Object();
        @Override
        public void run() {
            // Object object = new Object();
            // 多个线程公用同一个window对象，
            // 每个线程开始时都会执行该语句，new一个锁对象，
            // 因此会有3个锁对象，每个线程对应一个锁对象，
            // 因此无法保证线程安全
            while (true) {
                //synchronized (new object) {
                //此时每次进入循环体都会new一个对象，因此会有300个锁对象

                //正确的方式一：
                // synchronized (object) {
                // 正确的方式二:
                // 此时的锁对象是当前类
                // 类只会加载一次，因此是唯一的对象
                synchronized (ThreadSafetyWindowByThreadAndSynchronizedCodeBlock.class) {
                    // 错误的方式：
                    // 此时的当前对象this指windowThread对象，
                    // 既是线程对象，也是window对象，
                    // 创建三个线程会对应三个不同的this，
                    // 因此无法保证线程安全
                // synchronized (this) {
                    if (tickets > 0) {
                        //放大重票、错票的问题出现的概率
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println(Thread.currentThread().getName() + ": 买票， 票号为: " + tickets );

                        tickets--;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 通过同步代码块来实现线程安全的实现Runnable接口Window类
     */
    static class ThreadSafetyWindowByRunnableAndSynchronizedCodeBlock implements Runnable{
        private int tickets = 100;
        // 正确方式
        // Object object = new Object();
        @Override
        public void run() {
            // Object object = new Object();
            // 多个线程公用同一个window对象，
            // 每个线程开始时都会执行该语句，new一个锁对象，
            // 因此会有3个锁对象，每个线程对应一个锁对象，
            // 因此无法保证线程安全
            while (true) {
                //synchronized (new object) {
                //此时每次进入循环体都会new一个对象，因此会有300个锁对象

                //方式一：
                // synchronized (object) {
                //方式二：
                //此时this指当前对象，即唯一的window对象，多个线程公用同一个window对象，因此可以保证线程安全
                synchronized (this) {
                    if (tickets > 0) {
                        //放大重票、错票的问题出现的概率
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println(Thread.currentThread().getName() + ": 买票， 票号为: " + tickets );

                        tickets--;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 通过同步方法来实现线程安全的继承Thread类的Window类
     */
    static class ThreadSafetyWindowByThreadAndSynchronizedMethod extends Thread{
        private static int tickets = 100;

        @Override
        // 此时不符合题意，只有一个窗口在卖票
        // public synchronized void run() {
        public void run() {
            while (true) {
                show();
            }
        }

            // 错误的解决方式：
            // private synchronized void show() { //同步监视器：window1, window2, window3
            // 正确的解决方式：
            private static synchronized void show() { //同步监视器：当前的类

                if (tickets > 0) {
                //放大重票、错票的问题出现的概率
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + ": 买票， 票号为: " + tickets);

                tickets--;
            }
        }
    }

    /**
     * 通过同步方法来实现线程安全的实现Runnable接口Window类
     */
    static class ThreadSafetyWindowByRunnableAndSynchronizedMethod implements Runnable{
        private int tickets = 100;

        @Override
        // 此时不符合题意，只有一个窗口在卖票
        // public synchronized void run() {
        public void run() {
            while (true) {
                show();
            }
        }

        private synchronized void show() { //同步监视器：this
            if (tickets > 0) {
                //放大重票、错票的问题出现的概率
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + ": 买票， 票号为: " + tickets);

                tickets--;
            }
        }
    }
}
