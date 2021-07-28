/*
 * Chsi
 * Created on 2021-06-16
 */
package com.perfat.boot.thread;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class TestUtils {
    private static Object lock = new Object();

    public static void say() throws InterruptedException {
        synchronized ( lock ) {
            for ( int i = 0; i < 10; i++ ) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                Thread.sleep(1000);
            }
        }
    }


}