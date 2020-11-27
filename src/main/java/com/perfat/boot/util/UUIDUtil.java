/*
 * Chsi
 * Created on 2019-03-22
 */
package com.perfat.boot.util;

import org.apache.commons.lang.StringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * 工具类
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: UUIDUtil.java 16 2019-08-07 08:28:05Z 二进制 $
 */
public class UUIDUtil {

    public static String getRandomUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = StringUtils.replace(uuid, "-", "");
        return uuid;
    }

    /**
     * 随机获取基于base字符串组成的length长度的字符串
     *
     * @param length 长度
     */
    public static String getRandomString(int length) {
        String base = "0123456789abcdefghijklmnopqrstuvwxyz";
        int baseLength = base.length();
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for ( int i = 0; i < length; i++ ) {
            int index = random.nextInt(baseLength);
            stringBuilder.append(base.charAt(index));
        }
        return stringBuilder.toString();
    }
}
