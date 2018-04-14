package com.zhou.grad.auth.dao;

import java.util.List;
import java.util.Map;

import com.zhou.grad.auth.model.UserRoleModal;
import com.zhou.grad.entity.UserRole;

public interface UserRoleDao {
    int deleteByPrimaryKey(Integer userRoleId);
    
    /**
     * 根据userId删除
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);
    
    /**
     * 根据userId批量删除
     * @param userIds
     * @return
     */
    int deleteBatchByUserIds(Integer[] userIds);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer userRoleId);

    /**
     * 分页查询用户角色信息
     * @param map
     * @return
     */
    List<UserRoleModal> selectUserRoleByPage(Map<String, Object> map);
    
    /**
     * 统计用户角色数量
     * @param map
     * @return
     */
    int countUsers(Map<String, Object> map);
    
    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    /**
     * 单个修改用户的角色
     * @param map
     * @return
     */
    int updateByUserId(Map<String, Object> map);
    
    /**
     * 批量修改用户的角色
     * @param map
     * @return
     */
    int updateByUserIds(Map<String, Object> map);
}