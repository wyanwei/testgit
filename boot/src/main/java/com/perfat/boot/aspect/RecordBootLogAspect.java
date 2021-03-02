/*
 * Chsi
 * Created on 2019-07-09
 */
package com.perfat.boot.aspect;

import com.perfat.boot.aspect.entity.AccessBootLogData;
import com.perfat.boot.aspect.service.BootLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Aspect
@Component
public class RecordBootLogAspect {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BootLogService bootLogService;

    public RecordBootLogAspect() {
    }


    @Around("@annotation(com.perfat.boot.aspect.Log)&& @annotation(Log)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        log.info("USER_NAME : {}, URL : {}, HTTP_METHOD : {}, IP : {}, CLASS_METHOD : {}, ARGS : {}", new Object[]{userName, request.getRequestURL().toString(), request.getMethod()
                , request.getRemoteAddr(), (joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()), Arrays.toString(joinPoint.getArgs())});

        Calendar startTime = Calendar.getInstance();
        AccessBootLogData logData = new AccessBootLogData();
        if (null != joinPoint.getArgs() && joinPoint.getArgs().length != 0) {
            logData.setParams(Arrays.toString(joinPoint.getArgs()));
        }
        Object result = joinPoint.proceed();
        logData.setUserName(userName);
        Calendar endTime = Calendar.getInstance();
        long time = endTime.getTimeInMillis() - startTime.getTimeInMillis();
        logData.setSpendTime(time);
        logData.setStartTime(startTime);
        logData.setEndTime(endTime);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logData.setMethodName(methodSignature.getName());
        logData.setClassName(joinPoint.getTarget().getClass().getName());

        bootLogService.saveOrUpdate(logData);

        //TODO 记录用户详细信息
        return result;
    }
}
