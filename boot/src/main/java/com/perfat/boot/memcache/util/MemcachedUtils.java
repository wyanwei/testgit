/*
 * Chsi
 * Created on 2019-07-10
 */
package com.perfat.boot.memcache.util;

import com.perfat.boot.memcache.config.MemcacheConfig;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.CompressionMode;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: MemcachedUtils.java 16 2019-08-07 08:28:05Z 二进制 $
 */

/**
 * CommandLineRunner springBoot初始化就会加载
 */
//@Component
public class MemcachedUtils implements CommandLineRunner {
    private static Logger log = LoggerFactory.getLogger(MemcachedUtils.class);


    //@Autowired
    MemcacheConfig memcacheConfig;

    private MemcachedClient memcachedClient;

    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    @Override
    public void run(String... args) throws Exception {
        MemcachedClientBuilder memcachedClientBuilder = new XMemcachedClientBuilder(AddrUtil.getAddressMap(memcacheConfig.getServers()));
        //每个客户端池子的连接数
        memcachedClientBuilder.setConnectionPoolSize(memcacheConfig.getPoolSize());
        //使用一致性hash算法
        memcachedClientBuilder.setSessionLocator(new KetamaMemcachedSessionLocator(memcacheConfig.isHashConsistent()));

        //序列化转换器，指定最大的数据大小1M
        SerializingTranscoder transcoder = new SerializingTranscoder(1024 * 1024);
        //默认为UTF-8，这里可去掉
        transcoder.setCharset("UTF-8");
        //单位：字节，压缩边界值，任何一个大于该边界值（这里是：1M）的数据都要进行压缩
        transcoder.setCompressionThreshold(1024 * 1024);
        //压缩算法
        transcoder.setCompressionMode(CompressionMode.GZIP);

        memcachedClientBuilder.setTranscoder(transcoder);

        memcachedClient = memcachedClientBuilder.build();
        memcachedClient.setOpTimeout(memcacheConfig.getOpTimeout());
    }
}
