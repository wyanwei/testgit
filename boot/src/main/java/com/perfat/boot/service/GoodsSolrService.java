/*
 * Chsi
 * Created on 2019-04-12
 */
package com.perfat.boot.service;

import com.perfat.boot.entity.GoodsData;
import com.perfat.boot.support.bean.GoodBean;

import java.util.List;

/**
 * solr服务
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: GoodsSolrService.java 8 2019-06-06 10:27:09Z 二进制 $
 */
public interface GoodsSolrService {
    /**
     * 保存商品信息到solr
     *
     * @param goodsData 商品信息
     */
    void saveGoodsData(GoodsData goodsData);

    /**
     * 保存商品信息到solr
     *
     * @param goodsData 商品信息
     */
    void saveGoodsDataByBean(GoodsData goodsData);

    /**
     * 保存商品信息到solr
     *
     * @param goodsData 商品信息
     */
    void saveGoodsDataByBeanList(List<GoodsData> goodsData);

    /**
     * 查询商品信息
     *
     * @return 商品信息
     */
    List<GoodBean> queryGoodBean(String keywords);

    /**
     * 统计商品信息
     */
    void statisticsGoodsInfo();
}
