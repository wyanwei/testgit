/*
 * Chsi
 * Created on 2019-05-30
 */
package com.perfat.boot.dao;

import com.perfat.boot.entity.GoodsData;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Repository
public interface GoodsDao extends CrudRepository<GoodsData, String> {

    /**
     * 获取所有的商品信息
     *
     * @return 商品信息
     */
    @Query("select t from GoodsData t order by t.updateTime DESC ")
    List<GoodsData> queryAllGoddsData();

    /**
     * 根据商品名称获取商品信息
     *
     * @param name 商品名称
     * @return 商品信息
     */
    @Query(value = "select t from GoodsData t where t.name = :name")
    GoodsData getGoodsDataByName(@Param("name") String name);
}
