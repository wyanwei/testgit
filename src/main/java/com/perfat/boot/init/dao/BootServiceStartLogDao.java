/*
 * Chsi
 * Created on 2021-02-20
 */
package com.perfat.boot.init.dao;

import com.perfat.boot.init.entity.BootServiceStartLogData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface BootServiceStartLogDao extends JpaRepository<BootServiceStartLogData, String> {
}
