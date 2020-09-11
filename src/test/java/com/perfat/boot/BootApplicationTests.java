package com.perfat.boot;

import com.perfat.boot.memcache.util.MemcachedUtils;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTests {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    //@Autowired
    MemcachedUtils memcachedUtils;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 测试连接memcached是否成功
     */
    @Ignore
    @Test
    public void testMemcachedClient() {
        MemcachedClient memcachedClient = memcachedUtils.getMemcachedClient();
        try {
            boolean falg = memcachedClient.set("hello1", 0, "hello world");
            log.info("插入数据：{}", falg);
            String value = memcachedClient.get("hello1");
            log.info("查询的数据：{}", value);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("redis-test", "success");
        System.out.println(redisTemplate.opsForValue().get("redis-test"));
    }

}
