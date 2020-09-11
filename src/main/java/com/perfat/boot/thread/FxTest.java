/*
 * Chsi
 * Created on 2020-08-10
 */
package com.perfat.boot.thread;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class FxTest {

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.perfat.boot.thread.HelloTest");

        HelloTest helloTest = (HelloTest) clazz.newInstance();
        helloTest.say();

        Constructor constructor = clazz.getDeclaredConstructor();
        HelloTest test1 = (HelloTest) constructor.newInstance();
        System.out.println(helloTest.hashCode() + ":" + test1.hashCode());


        Method method = clazz.getMethod("say");
        method.invoke(helloTest);


        Method[] methods = clazz.getDeclaredMethods();
        Field field1 = clazz.getDeclaredField("username");
        field1.setAccessible(true);
        System.out.println(field1.get(helloTest));
        for (Method met : methods) {
            System.out.println(met.getName());
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName() + field.get(helloTest));
        }
    }

}
