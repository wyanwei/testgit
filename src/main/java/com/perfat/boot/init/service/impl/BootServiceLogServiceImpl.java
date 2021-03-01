/*
 * Chsi
 * Created on 2021-02-20
 */
package com.perfat.boot.init.service.impl;

import com.perfat.boot.init.dao.BootServiceStartLogDao;
import com.perfat.boot.init.entity.BootServiceStartLogData;
import com.perfat.boot.init.service.BootServiceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Service
@Transactional
public class BootServiceLogServiceImpl implements BootServiceLogService {

    @Autowired
    private BootServiceStartLogDao bootServiceStartLogDao;

    @Override
    public void saveOrUpdate(BootServiceStartLogData bootServiceStartLogData) {
        if ( null != bootServiceStartLogData ) {
            bootServiceStartLogDao.save(bootServiceStartLogData);
        }
    }
}
