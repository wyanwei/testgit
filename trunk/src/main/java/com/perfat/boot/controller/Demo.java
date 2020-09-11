/*
 * Chsi
 * Created on 2019-03-21
 */
package com.perfat.boot.controller;

import com.perfat.boot.aspect.Log;
import com.perfat.boot.entity.GoodsData;
import com.perfat.boot.service.GoodsService;
import com.perfat.boot.service.GoodsSolrService;
import com.perfat.boot.support.bean.GoodBean;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */

//@RestController //同时包含了Controller、ResponseBody
@Controller
@EnableAutoConfiguration
public class Demo {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsSolrService goodsSolrService;

    @RequestMapping(value = "/receive/sj", method = RequestMethod.POST)
    @ResponseBody
    public GoodsData receiveJsonData(@RequestBody GoodsData goodsData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("index", "index");
        return goodsData;
    }

    @RequestMapping(value = "/receive/sj1", method = RequestMethod.POST)
    @ResponseBody
    public String receiveJsonData1(@RequestBody Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("index", "index");
        return jsonObject.toString();
    }


    @RequestMapping(value = "/show/{name}/add")
    public String showTest(@PathVariable(value = "name") String name) {
        return "index";
    }

    @RequestMapping(value = "/show/good/add")
    public String showTest1() {
        return "index";
    }

    /**
     * 刷新商品信息
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public String refresh(Model model) {
        List<GoodsData> goodsDataList = goodsService.queryAllGoddsData();
        goodsSolrService.saveGoodsDataByBeanList(goodsDataList);
        return "index";
    }

    @RequestMapping(value = "/print/{name}", method = RequestMethod.GET)
    public String print(@PathVariable(value = "name") String name, Model model) {
        model.addAttribute("username", StringUtils.isBlank(name) ? "未知" : name);
        return "index";
    }

    @Log
    @RequestMapping(value = "/good/show", method = {RequestMethod.GET, RequestMethod.POST})
    public String showGood(String name, String minPrice, String maxPrice, Model model) {
        List<GoodBean> goodsDataList = goodsSolrService.queryGoodBean(name);
        model.addAttribute("goodsList", goodsDataList);
        model.addAttribute("name", name);
        return "good";
    }

    @RequestMapping(value = "/good/add", method = RequestMethod.POST)
    public String saveGoods(String name, String number, Model model) {
        GoodsData goodsData = new GoodsData();
        goodsData.setName(name);
        goodsData.setName(String.valueOf(number));

        goodsService.saveOrUpdateGoods(goodsData);
        return "redirect:/good/show";
    }

    @RequestMapping(value = "/good/tj", method = RequestMethod.GET)
    public String tjGoodsInfo(String name, String number, Model model) {
        goodsSolrService.statisticsGoodsInfo();
        return "redirect:/good/show";
    }
}
