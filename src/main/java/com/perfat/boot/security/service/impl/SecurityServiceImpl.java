/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.service.impl;

import com.perfat.boot.memcache.MemcacheClient;
import com.perfat.boot.security.dao.RoleDao;
import com.perfat.boot.security.dao.UserDao;
import com.perfat.boot.security.dao.UserRoleDao;
import com.perfat.boot.security.entity.RoleInfoData;
import com.perfat.boot.security.entity.UserInfoData;
import com.perfat.boot.security.entity.UserRoleInfoData;
import com.perfat.boot.security.service.SecurityService;
import com.perfat.boot.security.service.support.SecurityMemcachKey;
import com.perfat.boot.security.vo.UserInfoVo;
import com.perfat.boot.security.vo.UserRoleVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private MemcacheClient memcacheClient;

    @Override
    public UserInfoVo getUserInfoByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        String key = SecurityMemcachKey.getUserInfoByUserNameKey(username);
        UserInfoVo userInfoVo = memcacheClient.get(key);
        if (null != userInfoVo) {
            return userInfoVo;
        }
        UserInfoData userInfoData = userDao.getUserInfoByUsername(username);
        userInfoVo = getUserInfoVo(userInfoData);
        memcacheClient.set(key, userInfoVo, 1800);
        return userInfoVo;
    }

    @Override
    public UserInfoVo getUserInfoById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return getUserInfoVo(userDao.findById(id).get());
    }

    @Override
    public UserInfoData getUserInfoDataByUsername(String username) {
        return userDao.getUserInfoByUsername(username);
    }

    private UserInfoVo getUserInfoVo(UserInfoData userInfoData) {
        if (null == userInfoData) {
            return null;
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setId(userInfoData.getId());
        userInfoVo.setPassword(userInfoData.getPassword());
        userInfoVo.setUserName(userInfoData.getUsername());
        userInfoVo.setEmail(userInfoData.getEmail());
        userInfoVo.setPhone(userInfoData.getPhone());
        List<UserRoleInfoData> userRoleInfoDataList = userRoleDao.getUserRoleInfoDataByUserId(userInfoData.getId());
        if (null == userRoleInfoDataList || userRoleInfoDataList.isEmpty()) {
            return userInfoVo;
        }
        List<UserRoleVo> roleVoList = new ArrayList<>();
        for (UserRoleInfoData userRoleInfoData : userRoleInfoDataList) {
            RoleInfoData roleInfoData = roleDao.findById(userRoleInfoData.getRoleId()).get();
            if (null == roleInfoData) {
                continue;
            }
            UserRoleVo roleVo = new UserRoleVo();
            roleVo.setId(roleInfoData.getId());
            roleVo.setName(roleInfoData.getName());
            roleVo.setDesc(roleInfoData.getDesc());
            roleVoList.add(roleVo);
        }
        userInfoVo.setRoleVoList(roleVoList);
        return userInfoVo;
    }

    @Override
    public List<UserInfoVo> getAllUserInfoList() {
        List<UserInfoData> userInfoDataList = userDao.getAllUserInfo();
        if (userInfoDataList.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserInfoVo> userInfoVoList = new ArrayList<>();
        for (UserInfoData userInfoData : userInfoDataList) {
            userInfoVoList.add(getUserInfoVo(userInfoData));
        }
        return userInfoVoList;
    }

    @Transactional
    @Override
    public void saveOrUpdate(UserInfoData userInfoData, String roleName, String desc) {
        if (null == userInfoData) {
            return;
        }
        userDao.save(userInfoData);
        RoleInfoData roleInfoData = this.getRoleInfoDataByRoleName(roleName);
        if (null == roleInfoData) {
            roleInfoData = new RoleInfoData();
            roleInfoData.setDesc(desc);
            roleInfoData.setName(roleName);
            roleDao.save(roleInfoData);
        }
        UserRoleInfoData userRoleInfoData = new UserRoleInfoData();
        userRoleInfoData.setRoleId(roleInfoData.getId());
        userRoleInfoData.setUserId(userInfoData.getId());
        userRoleDao.save(userRoleInfoData);
    }

    @Override
    public UserInfoData getUserInfoDataById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return userDao.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteUserInfoData(UserInfoData userInfoData) {
        if (null == userInfoData) {
            return;
        }
        List<UserRoleInfoData> userRoleInfoDataList = userRoleDao.getUserRoleInfoDataByUserId(userInfoData.getId());
        if (null != userRoleInfoDataList && !userRoleInfoDataList.isEmpty()) {
            userRoleInfoDataList.forEach(userRoleInfoData -> userRoleDao.deleteById(userRoleInfoData.getId()));
        }
        userDao.delete(userInfoData);
    }

    @Override
    public void deleteUserInfoDataById(String id) {
        if (StringUtils.isBlank(id)) {
            return;
        }
        List<UserRoleInfoData> userRoleInfoDataList = userRoleDao.getUserRoleInfoDataByUserId(id);
        if (null != userRoleInfoDataList && !userRoleInfoDataList.isEmpty()) {
            userRoleInfoDataList.forEach(userRoleInfoData -> userRoleDao.deleteById(userRoleInfoData.getId()));
        }
        userDao.deleteById(id);
    }

    @Override
    public RoleInfoData getRoleInfoDataByRoleName(String roleName) {
        if (StringUtils.isBlank(roleName)) {
            return null;
        }
        return roleDao.getRoleInfoDataByRoleName(roleName);
    }
}
