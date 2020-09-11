/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.dao;

import com.perfat.boot.security.entity.RoleInfoData;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface RoleDao extends CrudRepository<RoleInfoData, String> {

    /**
     * 根据角色名称查询角色信息
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    @Query(value = "select r from RoleInfoData r where r.name = :roleName")
    RoleInfoData getRoleInfoDataByRoleName(@Param("roleName") String roleName);
}
