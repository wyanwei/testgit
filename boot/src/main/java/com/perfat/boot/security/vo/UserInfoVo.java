/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Getter
@Setter
public class UserInfoVo implements Serializable {
    private String id;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户的角色
     */
    private List<UserRoleVo> roleVoList;

}
