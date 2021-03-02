/*
 * Chsi
 * Created on 2019-03-22
 */
package com.perfat.boot.service;

import com.perfat.boot.entity.GoodsData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: GoodsService.java 16 2019-08-07 08:28:05Z 二进制 $
 */
public interface GoodsService {

    /**
     * 获取所有的商品信息
     *
     * @return 商品信息
     */
    List<GoodsData> queryAllGoddsData();

    /**
     * 根据商品名称获取商品信息
     *
     * @param name 商品名称
     * @return 商品信息
     */
    GoodsData getGoodsDataByName(@Param("name") String name);

    /**
     * 保存商品信息，同时刷新数据到solr
     *
     * @param goodsData 商品信息
     */
    void saveOrUpdateGoods(GoodsData goodsData);
}
