/*
 * Chsi
 * Created on 2021-01-26
 */
package com.perfat.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * 视图配置解析器
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Configuration
public class ViewResolverConfig {

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setCache(false);
        viewResolver.setViewNames("jsp/*");
        viewResolver.setOrder(2);
        return viewResolver;
    }
}
