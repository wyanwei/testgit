/*
 * Chsi
 * Created on 2019-07-11
 */
package com.perfat.boot.security.service.support;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: SecurityMemcachKey.java 16 2019-08-07 08:28:05Z 二进制 $
 */
public class SecurityMemcachKey {

    public static String getUserInfoByUserNameKey(String userName) {
        return String.format("userinfoByUserName_%s_memcachekey", userName);
    }
}
