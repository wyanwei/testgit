/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.service.impl;

import com.perfat.boot.security.service.SecurityService;
import com.perfat.boot.security.vo.UserInfoVo;
import com.perfat.boot.security.vo.UserRoleVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 将用户权限交给SpringSecurity进行管理
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (StringUtils.isBlank(userName)) {
            return null;
        }
        UserInfoVo userInfoVo = securityService.getUserInfoByUsername(userName);
        if (null == userInfoVo) {
            return null;
        }
        if (null == userInfoVo.getRoleVoList() || userInfoVo.getRoleVoList().isEmpty()) {
            return new User(userInfoVo.getUserName(), userInfoVo.getPassword(), null);
        }
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (UserRoleVo userRoleVo : userInfoVo.getRoleVoList()) {
            authorityList.add(new SimpleGrantedAuthority(userRoleVo.getName()));
        }
        return new User(userInfoVo.getUserName(), userInfoVo.getPassword(), authorityList);
    }
}
