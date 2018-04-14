package com.zhou.grad.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.auth.dao.RoleResourceDao;
import com.zhou.grad.auth.service.RoleResourceService;
import com.zhou.grad.entity.RoleResource;

@Service
public class RoleResourceImpl implements RoleResourceService {

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<RoleResource> selectRoleResourceByRoleId(int roleId) {
        
        return roleResourceDao.selectRoleResourceByRoleId(roleId);
    }
    
}
