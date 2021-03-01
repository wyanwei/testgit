/*
 * Chsi
 * Created on 2021-02-20
 */
package com.perfat.boot.aspect;

import org.apache.commons.lang.time.DateFormatUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 记录正在运行的定时任务
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
//@Aspect
//@Component
public class RecordScheduledTaskAspect {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();

        // 打印正在执行的方法到控制台
        String redisMember = String.format("%s %s", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"), methodName);
        log.info("正在执行的定时任务：" + redisMember);

        return joinPoint.proceed();
    }
}
