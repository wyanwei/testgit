/*
 * Chsi
 * Created on 2020-08-07
 */
package com.perfat.boot.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class ListTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        List<String> list = new ArrayList<>(1000);
        Vector vector = new Vector();

        for ( int i = 0; i < 10; i++ ) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("Thread"+num);
                    for ( int j = 0 ; j < 100; j++ ) {
                        String key = num + "ha" + j;
                        list.add(key);
                        vector.add(key);
                    }
                    System.out.println(Thread.currentThread().getName()+"put结束！");
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
        System.out.println(vector.size());
    }
}
