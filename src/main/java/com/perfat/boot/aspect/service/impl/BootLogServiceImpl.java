/*
 * Chsi
 * Created on 2019-07-09
 */
package com.perfat.boot.aspect.service.impl;

import com.perfat.boot.aspect.dao.BootLogDao;
import com.perfat.boot.aspect.entity.AccessBootLogData;
import com.perfat.boot.aspect.service.BootLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: BootLogServiceImpl.java 16 2019-08-07 08:28:05Z 二进制 $
 */
@Service
public class BootLogServiceImpl implements BootLogService {

    @Autowired
    BootLogDao bootLogDao;

    @Override
    public void saveOrUpdate(AccessBootLogData bootLogData) {
        if ( null != bootLogData ) {
            bootLogDao.save(bootLogData);
        }

    }
}
