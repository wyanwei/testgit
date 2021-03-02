/*
 * Chsi
 * Created on 2020-08-04
 */
package com.perfat.boot.thread.vola;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class Consumer extends Thread{
    private int num;
    private AbstractStorage abstractStorage;

    public Consumer(AbstractStorage abstractStorage) {
        this.abstractStorage = abstractStorage;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        abstractStorage.consume(num);
    }
}
