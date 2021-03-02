/*
 * Chsi
 * Created on 2020-08-07
 */
package com.perfat.boot.thread;


import javax.websocket.Session;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class ThreadLocalTest {
    private static final ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Thread1");
                threadLocal.set("12");
                threadLocal.set("34");
                threadLocal.remove();
                String value = (String) threadLocal.get();
                System.out.println(Thread.currentThread().getName()+"值:" + value);
                //threadLocal.remove();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Thread2");
                String value = (String)threadLocal.get();
                System.out.println(Thread.currentThread().getName()+"值:" + value);
            }
        }).start();

    }
}
