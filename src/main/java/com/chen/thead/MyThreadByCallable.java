package com.chen.thead;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三： 实现Callable接口。 ---JDK5.0新增
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口的方式创建多线程更强大？
 * 1. call()可以有返回值的。
 * 2. call()方法可以抛出异常，被外面的操作捕获，获取异常信息。
 * 3. callable是支持泛型的
 */
public class MyThreadByCallable {

    public static void main(String[] args) {
        //3. 创建Callable接口实现类的对象
        NumThread numThread = new NumThread();
        //4. 将此Callable接口实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask对象。
        FutureTask<Integer> futureTask = new FutureTask<>(numThread);
        //5. 将FutureTask对象作为参数传递到Thread类的构造器中，创建Thread类的对象，并执行start()方法
        new Thread(futureTask).start();

        try {
            //6. 获取Callable中的call方法的返回值
            Integer sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}


//1. 创建一个实现Callable接口的实现类
class NumThread implements Callable<Integer> {

    //2. 实现Call方法,将此线程需要执行的操作生命在call中
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}