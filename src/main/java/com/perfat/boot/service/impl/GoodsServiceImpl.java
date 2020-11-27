/*
 * Chsi
 * Created on 2019-05-30
 */
package com.perfat.boot.service.impl;

import com.perfat.boot.dao.GoodsDao;
import com.perfat.boot.entity.GoodsData;
import com.perfat.boot.service.GoodsService;
import com.perfat.boot.service.GoodsSolrService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsSolrService goodsSolrService;

    @Override
    public List<GoodsData> queryAllGoddsData() {
        return goodsDao.queryAllGoddsData();
    }

    @Override
    public GoodsData getGoodsDataByName(String name) {
        return goodsDao.getGoodsDataByName(name);
    }

    @Transactional
    @Override
    public void saveOrUpdateGoods(GoodsData goodsData) {
        GoodsData goodsInfo = goodsDao.getGoodsDataByName(StringUtils.trim(goodsData.getName()));
        if ( null == goodsInfo ) {
            goodsInfo = new GoodsData();
        }
        int count = 0;
        if ( StringUtils.isBlank(goodsInfo.getNumber()) ) {
            count = StringUtils.isBlank(goodsData.getNumber()) ? 0 : Integer.parseInt(goodsData.getNumber());
        } else {
            count = Integer.parseInt(goodsInfo.getNumber()) + (StringUtils.isBlank(goodsData.getNumber()) ? 0 : Integer.parseInt(goodsData.getNumber()));
        }
        goodsInfo.setNumber(String.valueOf(count));
        goodsInfo.setUpdateTime(Calendar.getInstance());
        goodsInfo.setPrice(goodsData.getPrice());
        goodsInfo.setBrand(goodsData.getBrand());
        goodsInfo.setDesc(goodsData.getDesc());
        goodsInfo.setName(goodsData.getName());
        goodsDao.save(goodsInfo);
        //保存至solr中
        goodsSolrService.saveGoodsDataByBean(goodsInfo);
    }
}
