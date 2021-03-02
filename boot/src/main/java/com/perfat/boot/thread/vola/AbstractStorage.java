/*
 * Chsi
 * Created on 2020-08-04
 */
package com.perfat.boot.thread.vola;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface AbstractStorage {
    void consume(int num);
    void produce(int num);
}
