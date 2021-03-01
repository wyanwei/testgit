/*
 * Chsi
 * Created on 2021-02-20
 */
package com.perfat.boot.init;

import com.perfat.boot.init.entity.BootServiceStartLogData;
import com.perfat.boot.init.service.BootServiceLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Component
@Order(value = 1)
public class BootInitDataTask implements CommandLineRunner {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BootServiceLogService bootServiceLogService;

    @Override
    public void run(String... args) throws Exception {
        log.info("服务器启动执行任务- 记录启动时间");
        BootServiceStartLogData startLogData = new BootServiceStartLogData();
        startLogData.setStartTime(Calendar.getInstance());
        bootServiceLogService.saveOrUpdate(startLogData);
    }
}
