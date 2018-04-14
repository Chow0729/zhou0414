package com.zhou.grad.auth.service;

import java.util.List;

import com.zhou.grad.entity.RoleResource;

public interface RoleResourceService {

    /**
     * 根据roleId查询
     * @param roleId
     * @return
     */
    List<RoleResource> selectRoleResourceByRoleId(int roleId);
}
