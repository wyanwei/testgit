/*
 * Chsi
 * Created on 2021-03-02
 */
package com.perfat.boot.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Configuration
public class RestTemplateConfig {


    /**
     * loadBalance这个注解加上之后，这个注解有3件事情要处理。
     *
     * 1. 从负载均衡器中选一个对应的服务实例，那有的人就会问为什么从负载均衡器中挑选，原因很明显就是，所有的服务名实例都放在负载均衡器中的serverlist。
     * 2.从第一件事情挑选的实例中去请求内容。
     * 3. 由服务名转为真正使用的ip地址
     * @return
     */
    @Bean
    //@LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        return factory;
    }*/
}
