/*
 * Chsi
 * Created on 2019-07-18
 */
package com.perfat.boot.test;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class demo {

    public demo() {
        System.out.println("demo");
    }

    {
        System.out.println("demo1");
    }

    static {
        System.out.println("demo2");
    }
}
