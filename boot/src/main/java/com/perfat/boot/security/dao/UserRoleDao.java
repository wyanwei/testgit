/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.dao;

import com.perfat.boot.security.entity.UserRoleInfoData;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface UserRoleDao extends CrudRepository<UserRoleInfoData, String> {

    /**
     * 根据用户信息ID查询用户与角色的关联关系
     *
     * @param userId 用户信息ID
     * @return 用户与角色的关联关系
     */
    @Query(value = "select u from UserRoleInfoData u where u.userId = :userId")
    List<UserRoleInfoData> getUserRoleInfoDataByUserId(@Param("userId") String userId);
}
