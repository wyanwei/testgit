/*
 * Chsi
 * Created on 2021-03-02
 */
package com.perfat.eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@RestController
@RequestMapping(value = "/say")
public class HelloWorkController {

    @Value("${server.port}")
    String port;

    @Autowired
    DiscoveryClient client;

    @RequestMapping(value = "/ce")
    public String sa() {
        List<ServiceInstance> instanceList = client.getInstances("eurekaServer");

        instanceList.forEach(serviceInstance -> {
              System.out.println(serviceInstance.getInstanceId());
            }
        );

        return "测试";
    }

    @RequestMapping(value = "/hi")
    public String say() {
        return "i am from prot:" + port;
    }
}
