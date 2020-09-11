/*
 * Chsi
 * Created on 2020-08-04
 */
package com.perfat.boot.thread.vola;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class Producer extends Thread{
    private int num;

    private AbstractStorage storage;

    public Producer(AbstractStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        storage.produce(num);
    }

    public void setNum(int num) {
        this.num = num;
    }
}
