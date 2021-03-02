/*
 * Chsi
 * Created on 2019-04-17
 */
package com.perfat.boot.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 用户信息
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Entity
@Table(name = "USER_INFO")
@Getter
@Setter
public class UserInfoData {
    @Id
    @GenericGenerator(name = "uu_id", strategy = "com.perfat.boot.util.StringRandomGenerator")
    @GeneratedValue(generator = "uu_id")
    @Column(name = "ID")
    private String id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    /**
     * 手机号
     */
    @Column(name = "PHONE")
    private String phone;
    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;
}
