/*
 * Chsi
 * Created on 2019-07-09
 */
package com.perfat.boot.memcache.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Component
//以memcache开头的配置文件加载到属性中
@ConfigurationProperties(prefix = "memcache", ignoreInvalidFields = true)
@Getter
@Setter
public class MemcacheConfig {
    private String servers;
    private boolean hashConsistent;
    private int maxClient;
    private int poolSize;
    private long opTimeout;
}
