package com.chen.thead;

/**
 * 多线程的创建：
 * 方式一：继承于Thread类
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类的run()方法 --> 将此线程执行的操作声明在run()中
 * 3. 创建此子类的对象
 * 4. 通过此对象调用start()方法
 *
 * 例子： 遍历100以内的所有偶数
 */
public class MyTheadByThread extends Thread {
    @Override
    public void run() {
        //super.run();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}

