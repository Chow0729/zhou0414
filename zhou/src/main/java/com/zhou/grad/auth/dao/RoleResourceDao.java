package com.zhou.grad.auth.dao;

import java.util.List;
import java.util.Map;

import com.zhou.grad.entity.RoleResource;

public interface RoleResourceDao {
    int deleteByPrimaryKey(Integer roleResourceId);

    /**
     * 根据roleId删除
     * @param roleId
     * @return
     */
    int deleteByRoleId(int roleId);
    
    /**
     * 根据resourceId删除
     * @param resourceId
     * @return
     */
    int deleteByResourceId(Integer resourceId);
    
    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    /**
     * 批量添加
     * @param map
     * @return
     */
    int insertBatch(Map<String, Object> map);

    RoleResource selectByPrimaryKey(Integer roleResourceId);
    
    /**
     * 根据roleId查询
     * @param roleId
     * @return
     */
    List<RoleResource> selectRoleResourceByRoleId(int roleId);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
}