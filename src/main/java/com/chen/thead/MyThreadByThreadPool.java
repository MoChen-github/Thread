package com.chen.thead;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程的方式四： 使用线程池
 *
 * 好处：
 * 1. 提高响应速度（减少了创建新线程的时间）
 * 2. 降低资源消耗（重复利用线程池中的线程， 不需要每次都创建）
 * 3. 便于线程管理
 *      corePoolSize：核心池的大小
 *      maximumPoolSize：最大线程数
 *      keepAliveTime：线程没有任务时最多保持多长时间后会终止
 * 经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大。
 * 提前创建好多个线程，放入线程池中， 使用时直接获取，使用完放回池中，可以避免频繁创建销毁，实现重复利用。
 */
public class MyThreadByThreadPool {
    public static void main(String[] args) {
        //1. 提供指定线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 设置线程池的属性

        //2. 执行指定的线程的操作。需要提供实现Runnable接口或实现Callable接口的实现类的对象
        executorService.execute(new NumberThread()); //适合适用于Runnable
        executorService.execute(new NumberThread1()); //适合适用于Runnable
        //executorService.submit(); //适合使用于Callable

        //3. 关闭连接池
        executorService.shutdown();

    }
}

class NumberThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class NumberThread1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}