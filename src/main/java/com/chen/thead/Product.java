package com.chen.thead;

/**
 * 线程通信的应用：生产者/消费者问题
 *
 * 生产者(Producer) 将产品教给店员(Clerk)， 而消费者(Customer) 从店员处取走产品，
 * 电源一次只能持有固定数量的产品（比如：20）， 如果生产者试图生产更多的产品， 店员会叫生产者停一下，
 * 如果店中有空位放产品了再通知生产者继续生产，如果殿中没有产品了，店员会告诉消费者等一下，
 * 如果店中有产品了再通知消费者来取走产品
 *
 * 分析：
 * 1. 是否是多线程问题？是，生产者线程、消费者线程和
 * 2. 是否享有共享数据？是， 店员（产品）
 * 3. 如何解决线程安全问题？ 同步机制，有三种方法
 * 4. 是否涉及线程通信？是
 */
public class Product {
    public static void main(String[] args) {
        Production production = new Production();
        Clerk clerk = new Clerk(production);

        Producer producer = new Producer(clerk);
        producer.setName("生产者1");

        Consumer consumer = new Consumer(clerk);
        consumer.setName("消费者1");

        producer.start();
        consumer.start();
    }
}

class Clerk {
    private Production production;

    Clerk() {

    }
    Clerk(Production production) {
        this.production = production;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public void saleProduction() throws InterruptedException {
        synchronized (production) {
            if (production.getProductionCount() > 0) {
                System.out.println(Thread.currentThread().getName() + ":开始消费第" + production.getProductionCount() + "个产品");
                production.setProductionCount(production.getProductionCount() - 1);
                production.notify();
            } else {
                soldOut();
            }
        }
    }

    public void soldOut() throws InterruptedException { //通知消费者售罄的方法
        production.wait();
    }

    public void fullWarehouse() throws InterruptedException { //通知生产者满仓的方法
        production.wait();
    }
}

class Production {
    private int productionCount = 0;

    public int getProductionCount() {
        return productionCount;
    }

    public void setProductionCount(int productionCount) {
        this.productionCount = productionCount;
    }
}

class Producer extends Thread{ //生产者
    private Clerk clerk;
    private Production production;

    public Producer() {}
    public Producer(Clerk clerk) {
        this.clerk = clerk;
        this.production = this.clerk.getProduction();
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public void createProduction(Production production) throws InterruptedException {
        synchronized (production) {
            if (production.getProductionCount() < 20) {
                production.setProductionCount(production.getProductionCount() + 1);
                System.out.println(Thread.currentThread().getName() + ":开始生产第" + production.getProductionCount() + "个产品");
                production.notify();
            } else {
                clerk.fullWarehouse();
            }
        }
    }

    @Override
    public void run() {
        System.out.println(getName() + "开始生产产品");
        while (true) {
            try {
                createProduction(production);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class  Consumer extends Thread { //消费者
    private Clerk clerk;
    private Production production;

    public Consumer() {

    }
    public Consumer(Clerk clerk) {
        this.clerk = clerk;
        this.production = this.clerk.getProduction();
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
    }

    public Production getProduction() {
        return production;
    }

    public synchronized void setProduction(Production production) {
        this.production = production;
    }

    @Override
    public void run() {
        System.out.println(getName() + "开始消费产品");
        while (true) {
            try {
                clerk.saleProduction();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
