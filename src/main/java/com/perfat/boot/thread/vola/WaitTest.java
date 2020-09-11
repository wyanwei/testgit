/*
 * Chsi
 * Created on 2020-08-04
 */
package com.perfat.boot.thread.vola;


/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class WaitTest {
    public static void main(String[] args) {
        AbstractStorage storage = new StorageImpl();

        //生产者
        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);
        Producer p6 = new Producer(storage);
        Producer p7 = new Producer(storage);
        Producer p8 = new Producer(storage);
        Producer p9 = new Producer(storage);
        Producer p10 = new Producer(storage);

        //消费者
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);
        Consumer c4 = new Consumer(storage);

        p1.setNum(50);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(10);
        p8.setNum(10);
        p9.setNum(10);
        p10.setNum(80);

        c1.setNum(50);
        c1.setNum(60);
        c1.setNum(20);

        c1.start();
        c2.start();
        c3.start();
        c4.start();

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
        p8.start();
        p9.start();
        p10.start();
    }
}
