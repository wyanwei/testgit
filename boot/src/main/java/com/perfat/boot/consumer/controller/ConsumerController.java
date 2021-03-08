/*
 * Chsi
 * Created on 2021-03-02
 */
package com.perfat.boot.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private LoadBalancerClient balancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/hi")
    public String hello() {
        List<String> serverList = client.getServices();

        List<ServiceInstance> instanceList = client.getInstances("EUREKA-CLIENT");

        ServiceInstance instance2 = balancerClient.choose("EUREKA-CLIENT");

        instanceList.forEach(instance -> {
            String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/boot/remote/say/hi";

            log.info("测试：" + restTemplate.getForObject(url, String.class));
        });

        String url = "http://EUREKA-CLIENT/say/hi";



        return "测试成功";
    }

}
