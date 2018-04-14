package com.zhou.grad.auth.service;

import java.util.Map;

import com.zhou.grad.auth.model.QueryParamsModal;

public interface UserRoleService {

    /**
     * 分页查询所有的用户角色信息
     * @param params
     * @return
     */
    Map<String, Object> selectUserRoleByPage(QueryParamsModal params);
    
    /**
     * 根据userId删除用户角色关系
     * @param userId
     * @return
     */
    Map<String, Object> delUserRoleByUserId(int userId);
    
    /**
     * 单个修改用户角色
     * @param userId
     * @param roleId
     * @return
     */
    Map<String, Object> editUserRoleByUserId(int userId, int roleId);
    
    /**
     * 批量修改用户角色
     * @param userIds
     * @param roleId
     * @return
     */
    Map<String, Object> editUserRoleBatch(int[] userIds, int roleId);
}
