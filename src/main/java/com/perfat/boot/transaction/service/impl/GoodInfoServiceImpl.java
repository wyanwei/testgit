/*
 * Chsi
 * Created on 2021-01-26
 */
package com.perfat.boot.transaction.service.impl;

import com.perfat.boot.entity.GoodsData;
import com.perfat.boot.service.GoodsService;
import com.perfat.boot.transaction.entity.GoodDetailInfoData;
import com.perfat.boot.transaction.repository.GoodInfoRepository;
import com.perfat.boot.transaction.service.GoodInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Service
public class GoodInfoServiceImpl implements GoodInfoService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodInfoRepository goodInfoRepository;

    @Autowired
    private GoodsService goodsService;

    @Transactional
    @Override
    public void saveOrUpdate(GoodDetailInfoData detailInfoData) {
        goodInfoRepository.save(detailInfoData);

        GoodsData goodsData = new GoodsData();
        goodsData.setBrand("brand");
        goodsData.setDesc("测试111");
        goodsData.setName("测试1");
        goodsData.setNumber("12");
        goodsData.setPrice("12");
        goodsData.setUpdateTime(Calendar.getInstance());
        goodsService.saveOrUpdateGoods(goodsData);
    }
}
