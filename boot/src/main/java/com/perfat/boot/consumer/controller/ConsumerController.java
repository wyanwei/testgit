/*
 * Chsi
 * Created on 2021-03-02
 */
package com.perfat.boot.consumer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {
    /*private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private LoadBalancerClient balancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/hi")
    public String hello() {
        List<String> serverList = client.getServices();

        List<ServiceInstance> instanceList = client.getInstances("eureka-client");

        ServiceInstance instance2 = balancerClient.choose("eureka-client");

       *//* instanceList.forEach(instance -> {
            String url = "http://" + instance.get + ":" + instance.getPort() + "/boot/remote/say/hi";

            log.info("测试：" + restTemplate.getForObject(url, String.class));
        });*//*

        String url = "http://eureka-client/say/hi";

        return restTemplate.getForObject(url, String.class);
    }*/

}
