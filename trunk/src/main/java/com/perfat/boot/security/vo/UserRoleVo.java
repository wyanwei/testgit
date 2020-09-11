/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户角色
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Setter
@Getter
public class UserRoleVo implements Serializable {
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 权限描述
     */
    private String desc;
}
