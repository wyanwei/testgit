/*
 * Chsi
 * Created on 2019-07-09
 */
package com.perfat.boot.aspect.service;

import com.perfat.boot.aspect.entity.AccessBootLogData;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: BootLogService.java 10 2019-07-09 08:48:20Z 二进制 $
 */
public interface BootLogService {

    /**
     * 记录访问项目的日志记录
     *
     * @param bootLogData 日志记录
     */
    void saveOrUpdate(AccessBootLogData bootLogData);
}
