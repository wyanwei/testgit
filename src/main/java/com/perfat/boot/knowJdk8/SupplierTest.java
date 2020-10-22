/*
 * Chsi
 * Created on 2020-10-21
 */
package com.perfat.boot.knowJdk8;

import java.util.function.Supplier;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class SupplierTest {
    public static void main(String[] args) {
        //创建Supplier容器，声明为SupplierTest类型，此时并不会调用对象的构造方法，即不会创建对象
        Supplier<SupplierTest> test = SupplierTest::new;

        //get的时候才会创建对象
        test.get().say(() -> test.get());

        //System.out.println(test.get().hashCode());
        //System.out.println(test.get().hashCode());
        //System.out.println(test.get().hashCode());


    }

    private void say() {
        System.out.println("Test Success！");
    }


    private void say(Supplier<SupplierTest> test) {
        test.get().say();
    }
}
