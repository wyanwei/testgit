/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.dao;

import com.perfat.boot.security.entity.UserInfoData;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface UserDao extends CrudRepository<UserInfoData, String> {

    /**
     * 根据用户名称获取用户信息
     *
     * @param username 用户姓名
     * @return 用户信息
     */
    @Query(value = "select u from UserInfoData u where u.username = :username")
    UserInfoData getUserInfoByUsername(@Param("username") String username);

    /**
     * 查询所有用户信息
     *
     * @return 用户信息
     */
    @Query(value = "select u from UserInfoData u order by u.id desc")
    List<UserInfoData> getAllUserInfo();

}
