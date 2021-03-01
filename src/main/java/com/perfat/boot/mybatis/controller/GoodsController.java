/*
 * Chsi
 * Created on 2020-01-19
 */
package com.perfat.boot.mybatis.controller;

import com.perfat.boot.mybatis.entity.GoodsInfo;
import com.perfat.boot.mybatis.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/gs")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/all")
    public List<GoodsInfo> getGoodsInfo() {
        return goodsService.getGoodsInfo();
    }
}
