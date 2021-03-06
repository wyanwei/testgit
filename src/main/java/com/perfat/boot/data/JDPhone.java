/*
 * Chsi
 * Created on 2019-05-30
 */
package com.perfat.boot.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.perfat.boot.entity.GoodsData;
import com.perfat.boot.service.GoodsService;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class JDPhone {
    private static GoodsService goodService;

    public static ConfigurableApplicationContext ac;


    //创建线程池
    static ExecutorService threadPool = Executors.newFixedThreadPool(20);
    //创建原生阻塞队列  队列最大容量为1000
    static BlockingQueue<String> queue = new ArrayBlockingQueue<>(1000);

    public static void main(String[] args) throws IOException, InterruptedException {
        JDPhone.ac = SpringApplication.run(JDPhone.class, args);
        goodService = ac.getBean(GoodsService.class);

        //监视队列大小的线程
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //获得队列当前的大小
                    int size = queue.size();
                    System.out.println("当前队列中有" + size + "个pid");
                }
            }
        });

        //开启10个线程去解析手机列表页获得的pids
        for (int i = 1; i <= 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String pid = null;

                        try {
                            //从队列中取出pid
                            pid = queue.take();
                            GoodsData product = parsePid(pid);
                            System.out.println(product.toString());
                            //存入数据库
                            goodService.saveOrUpdateGoods(product);
                        } catch (Exception e) {
                            e.printStackTrace();
                            try {
                                //出现异常则放回队列
                                queue.put(pid);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            });
        }


        //分页查找手机数据 共100页
        for (int i = 1; i <= 100; i++) {
            //京东分页page为 1 3 5 7 .....
            //         对应第一页 第二页....
            String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&page=" + (2 * i - 1);
            String html = HttpClientUtils.doGet(url);
            parseIndex(html);
        }

    }

    //解析手机列表页
    private static void parseIndex(String html) throws IOException, InterruptedException {
        Document document = Jsoup.parse(html);
        //手机列表
        Elements elements = document.select("#J_goodsList>ul>li");

        if (elements != null || elements.size() != 0) {
            for (Element element : elements) {
                //获得每个li的pid
                String pid = element.attr("data-pid");
                //将pid放入队列中
                queue.put(pid);
            }
        }
    }

    //解析每个手机的页面 获得某个手机的详细数据
    private static GoodsData parsePid(String pid) throws IOException {
        //拼接url 进入手机详情页
        String productUrl = "https://item.jd.com/" + pid + ".html";
        String productHtml = HttpClientUtils.doGet(productUrl);
        org.jsoup.nodes.Document document = Jsoup.parse(productHtml);

        GoodsData product = new GoodsData();

        //获得手机标题
        if (document.select("div.sku-name").size() > 0) {
            String title = document.select("div.sku-name").get(0).text();
            product.setDesc(title);
        }

        //获得手机品牌
        String brand = document.select("#parameter-brand li").attr("title");
        product.setBrand(brand);

        //获得手机名称
        String pname = document.select("[class=parameter2 p-parameter-list] li:first-child").attr("title");
        product.setName(pname);

        /*  此方案无法获取到价格
            jd的价格采用异步刷新,price不在返回的html文档中,需要我们去请求价格页面
            Elements select = document.select("span[class=price J-p-" + pid + "]");
            System.out.println(select);
        */

        //拼接价格页面url 经过测试 返回Json数据  jd对IP进行了限制,加入pduid为随机数,是为了可以获取更多数据,但是依然只能爬取部分
        String priceUrl = "https://p.3.cn/prices/mgets?pduid=" + Math.random() + "&skuIds=J_" + pid;
        String priceJson = HttpClientUtils.doGet(priceUrl);
        Gson gson = new GsonBuilder().create();
        List<Map<String, String>> list = gson.fromJson(priceJson, List.class);
        String price = list.get(0).get("p");
        product.setPrice(price);
        product.setNumber("1");
        product.setUpdateTime(Calendar.getInstance());
        return product;
    }
}
