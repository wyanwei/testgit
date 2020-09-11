/*
 * Chsi
 * Created on 2019-04-16
 */
package com.perfat.boot.support.bean;

import lombok.Getter;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Getter
@Setter
public class GoodBean implements Serializable {
    @Field(value = "id")
    private String id;
    @Field(value = "name")
    private String name;
    @Field(value = "number")
    private String number;
    @Field(value = "updateTime")
    private Date updateTime;
    @Field(value = "price")
    private String price;
    @Field(value = "desc")
    private String desc;
    @Field(value = "brand")
    private String brand;
}