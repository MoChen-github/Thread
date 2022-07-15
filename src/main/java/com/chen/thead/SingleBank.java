package com.chen.thead;

/**
 * 使用同步机制将单例模式中的懒汉式改写成线程安全的
 */
public class SingleBank {
    private static SingleBank instance = null;
    //将构造器私有化
    private SingleBank() {}
/*
    public static synchronized SingleBank getInstance() {
        if (instance == null) {
            instance = new SingleBank();
        }
        return instance;
    }
*/

    public static SingleBank getInstance() {
//        //方式一：效率略差
//        synchronized (SingleBank.class) {
//            if (instance == null) {
//                instance = new SingleBank();
//            }
//            return instance;
//        }

        //方式二：
        if (instance == null) {
            synchronized (SingleBank.class) {
                if (instance == null) {
                    instance = new SingleBank();
                }
            }
        }
        return instance;
    }
}
