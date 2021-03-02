/*
 * Chsi
 * Created on 2020-08-10
 */
package com.perfat.boot.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class FanxTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        FanxTest fanxTest = FanxTest.class.newInstance();

        List<String> list1 = new ArrayList<>();
        list1.add("哈哈！");
        list1.add("呵呵！");
        list1.add("喝喝！");
        fanxTest.say(list1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        fanxTest.say(list2);
    }

    private <E> void say(List<E> list) {
        list.forEach(e -> System.out.println(e.toString()));
    }

}
