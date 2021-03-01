/*
 * Chsi
 * Created on 2021-02-20
 */
package com.perfat.boot.init.service;

import com.perfat.boot.init.entity.BootServiceStartLogData;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface BootServiceLogService {

    /**
     * 记录服务启动日志记录
     *
     * @param bootServiceStartLogData 日志记录
     */
    void saveOrUpdate(BootServiceStartLogData bootServiceStartLogData);
}
