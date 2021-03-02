/*
 * Chsi
 * Created on 2019-03-22
 */
package com.perfat.boot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: GoodsData.java 16 2019-08-07 08:28:05Z 二进制 $
 */
@Entity
@Table(name = "GOODS")
@Getter
@Setter
public class GoodsData {
    @Id
    @GenericGenerator(name = "uu_id", strategy = "com.perfat.boot.util.StringRandomGenerator")
    @GeneratedValue(generator = "uu_id")
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "DESC_")
    private String desc;
    @Column(name = "PRICE")
    private String price;
    @Column(name = "NUMBER")
    private String number;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATETIME")
    private Calendar updateTime;
}
