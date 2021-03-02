/*
 * Chsi
 * Created on 2020-01-19
 */
package com.perfat.boot.mybatis.service.impl;

import com.perfat.boot.mybatis.entity.GoodsInfo;
import com.perfat.boot.mybatis.mapper.GoodsMapper;
import com.perfat.boot.mybatis.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<GoodsInfo> getGoodsInfo() {
        return goodsMapper.getGoodsInfo();
    }
}
