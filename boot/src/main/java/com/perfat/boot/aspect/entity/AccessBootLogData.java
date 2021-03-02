/*
 * Chsi
 * Created on 2019-07-09
 */
package com.perfat.boot.aspect.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: AccessBootLogData.java 16 2019-08-07 08:28:05Z 二进制 $
 */
@Entity
@Table(name = "ACCESS_BOOT_LOG")
public class AccessBootLogData {
    @Id
    @GenericGenerator(name = "uu_id", strategy = "com.perfat.boot.util.StringRandomGenerator")
    @GeneratedValue(generator = "uu_id")
    @Column(name = "ID")
    private String id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "SPEND_TIME")
    private long spendTime;
    @Column(name = "START_TIME")
    private Calendar startTime;
    @Column(name = "END_TIME")
    private Calendar endTime;
    @Column(name = "CLASS_NAME")
    private String className;
    @Column(name = "METHOD_NAME")
    private String methodName;
    @Column(name = "PARAMS")
    private String params;

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(long spendTime) {
        this.spendTime = spendTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
