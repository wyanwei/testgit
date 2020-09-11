/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.service;

import com.perfat.boot.security.entity.RoleInfoData;
import com.perfat.boot.security.entity.UserInfoData;
import com.perfat.boot.security.vo.UserInfoVo;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface SecurityService {

    /**
     * 根据用户名称获取用户信息、角色信息
     *
     * @param username 用户名称
     * @return 用户信息、角色信息
     */
    UserInfoVo getUserInfoByUsername(String username);

    /**
     * 根据用户名称获取用户信息、角色信息
     *
     * @param id 用户信息Id
     * @return 用户信息、角色信息
     */
    UserInfoVo getUserInfoById(String id);

    /**
     * 查询所有的用户信息、角色信息
     *
     * @return 用户信息、角色信息
     */
    List<UserInfoVo> getAllUserInfoList();

    /**
     * 根据用户名称获取用户信息
     *
     * @param username 用户名称
     * @return 用户信息
     */
    UserInfoData getUserInfoDataByUsername(String username);

    /**
     * 根据用户信息ID称获取用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    UserInfoData getUserInfoDataById(String id);

    /**
     * 删除用户信息以及与角色关联关系
     *
     * @param userInfoData 用户信息
     */
    void deleteUserInfoData(UserInfoData userInfoData);

    /**
     * 删除用户信息以及与角色关联关系
     *
     * @param id 用户信息ID
     */
    void deleteUserInfoDataById(String id);

    /**
     * 保存用户信息、角色信息
     *
     * @param userInfoData 用户信息
     * @param roleName     角色名称
     * @param desc         角色描述
     */
    void saveOrUpdate(UserInfoData userInfoData, String roleName, String desc);

    /**
     * 根据角色名称查询角色信息
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    RoleInfoData getRoleInfoDataByRoleName(String roleName);
}
