package com.perfat.boot;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * https://www.cnblogs.com/momoweiduan/p/9454140.html 解决springboot 与activiti整合启动报错的问题
 * <p>
 * 因为GlobalAuthenticationConfigurerAdapter
 * 是spring-boot-starter-security 依赖中的属于安全配置类,
 * 而 引入的activiti-spring-boot-starter-basic 依赖中存在了一个自动安全配置类,
 * 两个安全配置,  所以排除掉 activiti-spring-boot-starter-basic中的安全配置类  SecurityAutoConfiguration
 *
 * @author wangyw
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
/*@EnableEurekaClient*/
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
