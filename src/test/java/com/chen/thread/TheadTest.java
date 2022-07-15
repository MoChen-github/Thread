package com.chen.thread;

import com.chen.thead.MyTheadByThread;
import com.chen.thead.MyThreadByRunnable;
import org.junit.Test;

public class TheadTest {
    @Test
    public void testMyTheadByThread() {
        MyTheadByThread thead = new MyTheadByThread();
        thead.start();
    }

    /**
     * {@code @FunctionalInterface}
     * public interface Runnable、
     */
    // Thread的有参构造中有一个重载的参数为Runnable类的实例对象，
    // 该类是一个函数式接口，只有一个默认的抽象方法run，
    // 因此可以使用lambda表达式来实现run()中的执行体
    // 此方法是使用了实现Runnable方法来开启一个线程
    @Test
    public void testMyTheadByRunnable() {
        MyThreadByRunnable myThread = new MyThreadByRunnable();
        Thread thread = new Thread(myThread);
        thread.start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }).start();
    }
}
