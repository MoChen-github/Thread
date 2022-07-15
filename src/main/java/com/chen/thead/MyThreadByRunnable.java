package com.chen.thead;

/**
 * 方式二：实现Runnable接口
 * 1. 创建一个实现了Runnable接口的类
 * 2. 实现类去实现Runnable中的抽象方法：run()
 * 3. 创建实现类的对象
 * 4. 将此对象作为参数传到Thread类的构造器中，创建Thread类的对象
 * 5. 通过Thread类的对线调用start() 方法
 *
 * 比较两种创建线程的方式：
 * 在实际开发中，有限选择实现Runnable接口的方式
 * 原因：
 * 1. 实现Runnable接口的方式没有类的单继承的局限性
 * 2. 实现的方式更适合来处理多个线程有共享数据的情况
 *
 * 联系：public class implements Runnable
 * 相同点：两种方式都需要重写Run()， 将线程需要执行的逻辑声明在Run()方法中。
 */
public class MyThreadByRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
