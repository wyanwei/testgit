/*
 * Chsi
 * Created on 2021-01-26
 */
package com.perfat.boot.transaction.repository;

import com.perfat.boot.transaction.entity.GoodDetailInfoData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface GoodInfoRepository extends JpaRepository<GoodDetailInfoData, String> {
}
