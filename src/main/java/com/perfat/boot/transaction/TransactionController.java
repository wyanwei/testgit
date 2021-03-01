/*
 * Chsi
 * Created on 2021-01-26
 */
package com.perfat.boot.transaction;

import com.perfat.boot.transaction.entity.GoodDetailInfoData;
import com.perfat.boot.transaction.service.GoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@RequestMapping(value = "/anonymous/trans")
@Controller
public class TransactionController {
    @Autowired
    private GoodInfoService goodInfoService;

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save() {
        GoodDetailInfoData detailInfoData = new GoodDetailInfoData();
        detailInfoData.setDesc("测试");
        detailInfoData.setName("测试");
        detailInfoData.setNumber("2");
        detailInfoData.setUpdateTime(Calendar.getInstance());
        goodInfoService.saveOrUpdate(detailInfoData);
        return "success";
    }
}
