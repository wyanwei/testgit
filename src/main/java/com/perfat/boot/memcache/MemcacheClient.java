/*
 * Chsi
 * Created on 2019-07-11
 */
package com.perfat.boot.memcache;

import com.perfat.boot.memcache.util.MemcachedUtils;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: MemcacheClient.java 16 2019-08-07 08:28:05Z 二进制 $
 */
@Component
public class MemcacheClient {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    //@Autowired
    MemcachedUtils memcachedUtils;

    /**
     * 设置缓存
     *
     * @param key    key
     * @param value  缓存的值
     * @param expire 缓存的过期日期
     * @return 是否设置成功
     */
    public boolean set(String key, Object value, int expire) {
        return false;
        /*try {
            return memcachedUtils.getMemcachedClient().set(key, expire, value);
        } catch (Exception e) {
            log.info("set memcache error, errMsg : {}", e);
            return false;
        }*/
    }

    /**
     * 获取缓存
     *
     * @param key 缓存key
     * @return 缓存内容
     */
    public <T> T get(String key) {
        return null;
        /*try {
            return memcachedUtils.getMemcachedClient().get(key);
        } catch (Exception e) {
            log.info("get memcache error, errMsg : {}", e);
            return null;
        }*/
    }
}
