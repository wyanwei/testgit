/*
 * Chsi
 * Created on 2020-08-10
 */
package com.perfat.boot.security;

import com.perfat.boot.util.BootUtil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Getter
@Setter
public class UserAuthorityModel implements Serializable {
    private static final long serialVersionUID = -8880183703195007504L;
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = BootUtil.REGEX_USERNAME, message = "请输入有效的用户名")
    private String username;
    @NotNull(message = "密码不能为空")
    @Pattern(regexp = BootUtil.REGEX_PASSWORD, message = "请输入有效的密码")
    private String password;
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = BootUtil.REGEX_MOBILE, message = "请输入有效的手机号")
    private String phone;
    @NotNull(message = "邮箱不能为空")
    @Email(message = "请输入有效的邮箱")
    private String email;
    @NotNull(message = "请输入用户角色")
    private String role;
    @Size(max = 60, message = "描述过长！")
    private String desc;
}
