/*
 * Chsi
 * Created on 2020-10-22
 */
package com.perfat.boot.knowJdk8;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        //无key赋值，有key则不赋值
        String vaues = map.computeIfAbsent("12", value -> "12");

        map.compute("12", (k, v) -> "24");
        map.compute("16", (k, v) -> "24");

        System.out.println(vaues);

        map.computeIfAbsent("12", value -> "15");

        //
        String newValue = map.putIfAbsent("13", "13");
        System.out.println("putIfAbsent：" + newValue);

        String newValue2 = map.putIfAbsent("13", "14");
        System.out.println("putIfAbsent：" + newValue2);

        //对象有的更细
        String ifValu = map.computeIfPresent("12", (k, v) -> v +"15");
        System.out.println("ifVal：" + ifValu);
        String ifValu1 = map.computeIfPresent("15", (k, v) -> v +"15");
        System.out.println("ifVal：" + ifValu1);

        map.entrySet().forEach(entry -> System.out.println(entry.getValue()));
    }

}
