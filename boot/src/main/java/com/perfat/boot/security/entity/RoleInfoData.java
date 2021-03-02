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
 * 角色信息
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Entity
@Table(name = "ROLE_INFO")
@Getter
@Setter
public class RoleInfoData {
    @Id
    @GenericGenerator(name = "uu_id", strategy = "com.perfat.boot.util.StringRandomGenerator")
    @GeneratedValue(generator = "uu_id")
    @Column(name = "ID")
    private String id;
    @Column(name = "ROLE_NAME")
    private String name;
    /**
     * 权限描述
     */
    @Column(name = "ROLE_DESC")
    private String desc;
}
