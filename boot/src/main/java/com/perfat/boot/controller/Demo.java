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
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
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
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsSolrService goodsSolrService;

    @RequestMapping("/ces")
    @ResponseBody
    public String ceshi(String applyCode, HttpServletRequest request) {
       // String content = parseRequestInfoFromRequest(request);
        return "ces";
    }

    /**
     * 从request中获取请求信息
     *
     * @return 请求信息
     */
    private String parseRequestInfoFromRequest(HttpServletRequest request) {
        String content = "";
        BufferedReader requestBodyReader = null;
        try {
            requestBodyReader = request.getReader();
            StringBuilder requestBodyBuffer = new StringBuilder();
            String readLine;
            while ( (readLine = requestBodyReader.readLine()) != null ) {
                requestBodyBuffer.append(readLine);
            }
            content = requestBodyBuffer.toString();
        } catch ( IOException e ) {
        } finally {
            IOUtils.closeQuietly(requestBodyReader);
        }
        return content;
    }


    @RequestMapping(value = "/upload", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String multiUpload(@RequestParam("file") MultipartFile file,
                              HttpServletRequest request) throws UnsupportedEncodingException {

        log.info("系统默认编码：" + System.getProperty("file.encoding"));


        String authorization = request.getHeader("Authorization");

        //String schoolInfo = request.getHeader("institutionName_cn");

        String schoolInfoEn = request.getHeader("institutionName_en");

        String message = URLDecoder.decode(schoolInfoEn,"UTF-8");

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        return "success";
    }

    /**
     * 解码
     *
     * @param base64
     * @return
     */
    public String decode(final String base64) {
        if (StringUtils.isEmpty(base64)) {
            return null;
        }
        String str = null;
        /*try {*/
            str = new String(Base64.decodeBase64(base64));
        /*} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return str;
    }



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
