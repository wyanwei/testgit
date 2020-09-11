/*
 * Chsi
 * Created on 2020-01-19
 */
package com.perfat.boot.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Getter
@Setter
public class GoodsInfo {
    private String id;
    private String name;
    private String number;
    private Calendar updateTime;
    private String desc;
    private String brand;
    private String price;
}
