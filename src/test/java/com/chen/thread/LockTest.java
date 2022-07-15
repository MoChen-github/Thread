package com.chen.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁 ---JDK5.0新增
 */
public class LockTest {
}

class WindowByLock implements Runnable {
    private int tickets = 100;

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                //2. 调用锁定方法lock()
                lock.lock();
                if (tickets > 0 ) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ":售票, 票号为：" + tickets);
                    tickets--;
                } else {
                    break;
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                //调用解锁的方法： unlock()
                lock.unlock();
            }
        }
    }
}
