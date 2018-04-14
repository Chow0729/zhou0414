package com.zhou.grad.auth.service;

import java.util.List;
import java.util.Map;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.entity.Role;

public interface RoleService {

    /**
     * 根据条件查询角色
     * @param params 查询参数
     * @return
     */
    Map<String, Object> selectRolesByPage(QueryParamsModal params);
    
    /**
     * 新增角色
     * @param role
     * @return
     */
    Map<String, Object> addRole(Role role);
    
    /**
     * 修改角色
     * @param role
     * @return
     */
    Map<String, Object> editRole(Role role);
    
    /**
     * 根据id查询角色
     * @param roleId
     * @return
     */
    Role selectRoleById(int roleId);
    
    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    Map<String, Object> delRoles(Integer[] ids);
    
    /**
     * 查询所有的角色
     * @return
     */
    List<Role> selectAllRoles();
}
