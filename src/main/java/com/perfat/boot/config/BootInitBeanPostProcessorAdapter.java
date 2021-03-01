/*
 * Chsi
 * Created on 2021-02-25
 */
package com.perfat.boot.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 *
 * 获取bean的流程：
 * 1、准备获取bean
 * 2、实例化bean
 * 3、配置bean(例如：设置属性值)
 * 4、
 *
 *
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
//@Component
//@Order(value = 1)
public class BootInitBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

    /**
     * 实例化bean之前的操作
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("实例化bean之前：" + beanName);
        return super.postProcessBeforeInitialization(bean, beanName);
    }

    /**
     * 实例化bean之后的操作
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("实例化bean之后" + beanName);
        return super.postProcessAfterInitialization(bean, beanName);
    }

    /**
     * 设置属性值的操作
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {

        System.out.println("设置bean的属性"+ beanName);

        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("设置bean的属性"+ beanName);
        return super.postProcessProperties(pvs, bean, beanName);
    }


}
