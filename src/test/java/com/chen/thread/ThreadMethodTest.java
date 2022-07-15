package com.chen.thread;

/**
 * 测试Thread中的常用方法：
 * 1. start(): 启动当前线程；调用当前线程的run()
 * 2. run(): 通常需要重写Thread类中的此方法， 将创建的线程要执行的操作声明在此方法中
 * 3. currentThread(): 静态方法，返回执行当前代码的线程
 * 4. getName(): 获取当前线程的名字
 * 5. setName(): 设置当前线程的名字
 * 6. yield(): 释放当前cpu的执行权
 * 7. join(): 在线程A中调用线程B的join()方法， 此时线程A就进入阻塞状态， 直到线程B完全执行完以后，线程A才会结束阻塞状态。
 * 8. stop(): （已过时） 当执行此方法时， 强制结束该线程。
 * 9. sleep(Long militiamen): 让当前线程“睡眠”指定的毫秒数。在指定的毫秒时间内，当前线程是阻塞状态。
 * 10. isAlive(): 判断当前线程是否存活
 *
 * 线程的优先级:
 * 1. 三个常量:
 *      MAX_PRIORITY: 10
 *      MIN _PRIORITY: 1
 *      NORM_PRIORITY: 5
 * 2. 两个常用方法:
 *      getPriority(): 获取线程的优先级
 *      setPriority(int p): 设置线程的优先级
 */
public class ThreadMethodTest {
    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread("thread:1");
        helloThread.setPriority(Thread.MAX_PRIORITY);
        // helloThread.setName("线程1");
        helloThread.start();

        //给主线程命名
        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(3);

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":"+ i);
            }

//            if (i == 20) {
//                try {
//                    helloThread.join();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
        }
        System.out.println(helloThread.isAlive());
    }
}

class HelloThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
//                try {
//                    sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println(getName() +  ":" + getPriority() + ":" + i);
            }

//            if (i % 20 == 0) {
//                Thread.yield();
//            }
        }
    }

    public HelloThread(String name) {
        super(name);
    }
}
