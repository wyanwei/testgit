/*
 * Chsi
 * Created on 2021-01-26
 */
package com.perfat.boot.transaction.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Data
@Entity
@Table(name = "GOODS_DETAIL")
public class GoodDetailInfoData {
    @Id
    @GenericGenerator(name = "uu_id", strategy = "com.perfat.boot.util.StringRandomGenerator")
    @GeneratedValue(generator = "uu_id")
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESC_")
    private String desc;
    @Column(name = "NUMBER")
    private String number;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATETIME")
    private Calendar updateTime;
}
