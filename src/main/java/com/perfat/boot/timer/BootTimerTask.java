/*
 * Chsi
 * Created on 2021-02-20
 */
package com.perfat.boot.timer;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
//@Component
public class BootTimerTask {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 每隔一秒执行下
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void timerTask() {
        log.info("测试：" + DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
    }
}
