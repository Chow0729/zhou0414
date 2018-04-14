package com.zhou.grad.auth.dao;

import java.util.List;
import java.util.Map;

import com.zhou.grad.entity.Role;

public interface RoleDao {
    int deleteByPrimaryKey(Integer roleId);
    
    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    int deleteRoleBatch(Integer[] ids);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);
    
    /**
     * 根据条件分页查询角色
     * @param map 参数
     * @return
     */
    List<Role> selectRolesByPage(Map<String, Object> map);
    
    /**
     * 根据角色代码查询
     * @param roleCode
     * @return
     */
    Role selectByRoleCode(String roleCode);
    
    /**
     * 根据条件统计相应的角色数量
     * @param map
     * @return
     */
    int countByCondition(Map<String,Object> map);
    
    /**
     * 查询所有的角色
     * @return
     */
    List<Role> selectAllRoles();

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}