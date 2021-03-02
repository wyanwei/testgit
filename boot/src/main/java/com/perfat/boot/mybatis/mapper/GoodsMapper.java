/*
 * Chsi
 * Created on 2020-01-19
 */
package com.perfat.boot.mybatis.mapper;

import com.perfat.boot.mybatis.entity.GoodsInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Repository
public interface GoodsMapper {

    List<GoodsInfo> getGoodsInfo();
}
