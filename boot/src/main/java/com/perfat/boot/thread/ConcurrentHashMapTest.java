/*
 * Chsi
 * Created on 2020-08-07
 */
package com.perfat.boot.thread;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 内部分成N个HashMap,称之为段(Segment),默认情况下一个ConcurrentHashMap分为16个段，加锁是加在段上的，
 * 先根据hashcode得到该表项应该存放到哪个段中。然后针对该段进行加锁，然后完成put操作。
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Map<String, String> hashTableMap = new Hashtable<>();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        //以下结果可验证除HashMap是线程不安全的！！！
        //concurrentHashMap、hashtable是线程安全的
        Map<String, String> map = new HashMap<>();
        for ( int i = 0; i < 10; i++ ) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("Thread"+num);
                    for ( int j = 0 ; j < 100; j++ ) {
                        String key = num + "ha" + j;
                        map.put(key, key);
                        concurrentHashMap.put(key, key);
                        hashTableMap.put(key, key);
                    }
                    System.out.println(Thread.currentThread().getName()+"put结束！");
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            System.out.println("等待线程执行完成！！！");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程执行完成，主线程执行！！");
        System.exit(0);
    }

}
