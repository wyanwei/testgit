/*
 * Chsi
 * Created on 2020-01-19
 */
package com.perfat.boot.mybatis.service;

import com.perfat.boot.mybatis.entity.GoodsInfo;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface GoodsService {

    List<GoodsInfo> getGoodsInfo();
}
