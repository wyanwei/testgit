/*
 * Chsi
 * Created on 2021-01-26
 */
package com.perfat.boot.transaction.service;

import com.perfat.boot.transaction.entity.GoodDetailInfoData;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface GoodInfoService {

    /**
     * 保存商品详细信息
     *
     * @param detailInfoData 详细信息
     */
    void saveOrUpdate(GoodDetailInfoData detailInfoData);
}
