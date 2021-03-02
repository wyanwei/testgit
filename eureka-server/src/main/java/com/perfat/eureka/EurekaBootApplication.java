/*
 * Chsi
 * Created on 2021-03-02
 */
package com.perfat.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaBootApplication.class, args);
    }
}
