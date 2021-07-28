/*
 * Chsi
 * Created on 2021-06-16
 */
package com.perfat.boot.thread;

import lombok.SneakyThrows;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class XcTest {
    public static void main(String[] args) {
        XcTest test = new XcTest();
        Thread thread1 = test.new Thread1();
        Thread thread2 = test.new Thread2();
        thread1.start();
        thread2.start();
    }

    public class Thread1 extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            TestUtils.say();
        }
    }


    public class Thread2 extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            TestUtils.say();
        }
    }
}
