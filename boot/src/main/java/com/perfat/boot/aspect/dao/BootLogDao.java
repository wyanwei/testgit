/*
 * Chsi
 * Created on 2019-07-09
 */
package com.perfat.boot.aspect.dao;

import com.perfat.boot.aspect.entity.AccessBootLogData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: BootLogDao.java 16 2019-08-07 08:28:05Z 二进制 $
 */
@Repository
public interface BootLogDao extends CrudRepository<AccessBootLogData, String> {
}
