/*
 * Chsi
 * Created on 2020-08-04
 */
package com.perfat.boot.thread.vola;

import java.util.LinkedList;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class StorageImpl implements AbstractStorage{
    private final int MAX_SIZE = 100;
    private LinkedList list = new LinkedList();

    @Override
    public void produce(int num) {
        synchronized (list) {
            while ((list.size() + num) > MAX_SIZE) {
                System.out.println("【要生产的数量】："+ num + "\t【库存量】"+ list.size() + "\t暂时不能执行生产任务");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            for (int i = 0; i < num; i++) {
                list.add(new Object());
            }

            System.out.println("【已经生产产品数】：" + num + "\t【现库存量】：" + list.size());

            list.notifyAll();
        }

    }

    @Override
    public void consume(int num)  {
        synchronized (list) {
            while (list.size() < num) {
                System.out.println("【要消费的数量】："+ num + "\t【库存量】"+ list.size() + "\t暂时无法消费");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            for (int i = 0; i < num; i++) {
                if ( i < list.size() ) {
                    list.remove(i);
                }
            }

            System.out.println("【已经消费产品数】：" + num + "\t【现库存量】：" + list.size());

            list.notifyAll();
        }

    }
}
