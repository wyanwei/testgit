/*
 * Chsi
 * Created on 2019-08-13
 */
package com.perfat.boot.memcache.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class Test {
    /**
     * volatile 的好处，禁止指令重排，可见性
     */
    private volatile static Test test;

    public static Test getInstance() {
        if ( null == test ) {
            synchronized ( Test.class ) {
                if ( null == test ) {
                    //正确流程：分配内容、内存中创建对象，变量指向刚分配的内容地址(分配地址就会test != null)，由于指向重排，可以造成 内容中创建对象与变量指向分配的内容地址
                    test = new Test();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Calendar nowDate = Calendar.getInstance();
        nowDate.add(Calendar.HOUR_OF_DAY, -2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(nowDate.getTime()));
    }
}
