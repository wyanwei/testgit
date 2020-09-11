/*
 * Chsi
 * Created on 2020-08-07
 */
package com.perfat.boot.thread;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class ReentantLockTest {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ReentantLockTest test = new ReentantLockTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Thread1");
                test.test();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Thread2");
                test.test1();
            }
        }).start();

    }

    public void test1() {
        try {
            /*System.out.println("是否可以直接获取到锁：" + lock.tryLock());
            System.out.println("是否获取到锁："+lock.tryLock(1, TimeUnit.SECONDS));*/
            Thread.sleep(10000);
            lock.lock();
            System.out.println("拿到锁了");
            condition.signal();
            System.out.println("唤醒线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void test() {
        try {
            lock.lock();
            System.out.println("加锁！！");
            System.out.println("开始等待！！！");
            condition.await();
            System.out.println("休眠结束！！！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
