package com.chen.thead;

/**
 * 线程通信的例子：使用两个线程打印1-100， 线程1、线程2 交替打印
 *
 * 涉及到的 三个方法：
 * wait(): 一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器。
 * notify(): 一旦执行此方法，就会胡安行被wait的一个线程。如果有多个线程被wait，就胡安行优先级高的哪个。
 * notifyAll(): 一旦执行此方法，就会唤醒所有被wait的线程。
 *
 * 说明:
 * 1. 只能出现在同步代码块或同步方法中，不能用在lock当中。
 * 2. 要求调用者必须是同步代码块或同步方法中的同步监视者.
 * 3. 这三个方法是定义在java.long.Object类当中的
 *
 * 面试题：sleep() 和 wait() 的异同？
 * 1. 相同点：一旦执行方法，都可以时当前线程进入阻塞状态
 * 2. 不同点：1） 两个方法的生命位置不同：Thread类中声明sleep()， Object类中声明wait()
 *          2) 调用两个方法的要求不同：sleep() 可以在任何需要的场景下调用， wait()必须使用在同步方法或同步代码块中
 *          3） 关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中， Sleep()不会释放同步代码块， 而wait()会释放同步监视器。
 */
public class Communication {
    public static void main(String[] args) {
        Number number = new Number();
        Thread thread1 = new Thread(number);
        Thread thread2 = new Thread(number);

        thread1.setName("线程1");
        thread2.setName("线程2");

        thread1.start();
        thread2.start();
    }
}

class Number implements Runnable {
private int number = 1;
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //使得调用如下wait()方法的线程进入阻塞状态
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    break;
                }
            }
        }
    }
}
