/*
 * Chsi
 * Created on 2019-07-18
 */
package com.perfat.boot.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class test extends demo {

    //构造函数
    public test() {
        System.out.println("test");
    }

    //构造函数代码块
    {
        System.out.println("test1");
    }

    //静态代码块
    static {
        System.out.println("test2");
    }

    public static void main(String[] args) {
        new test();
    }

   /* String a = new  String("test");
    String b = new String("b");
    public static void main(String[] args) {
        test test = new test();
        test.change(test.a,test.b);
        System.out.println(test.a+"==" +test.b);


    }

    private void change(String a,String b) {
        a = "test.ok";
        b = a ;
    }*/
}
