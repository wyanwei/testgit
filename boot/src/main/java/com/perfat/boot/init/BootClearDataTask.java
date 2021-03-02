/*
 * Chsi
 * Created on 2021-02-20
 */
package com.perfat.boot.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Component
@Order(value = 2)
public class BootClearDataTask implements CommandLineRunner {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {
        if ( log.isInfoEnabled() ) {
            log.info("服务器启动执行任务 - 清除数据");
        }
    }
}
