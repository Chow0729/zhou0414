package com.zhou.grad.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhou.grad.auth.dao.RoleDao;
import com.zhou.grad.auth.dao.RoleResourceDao;
import com.zhou.grad.auth.dao.UserRoleDao;
import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.auth.service.RoleService;
import com.zhou.grad.entity.Role;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private UserRoleDao urDao;
    
    @Autowired
    private RoleResourceDao rrDao;

    @Override
    public Map<String, Object> selectRolesByPage(QueryParamsModal params) {
        Map<String, Object> returnMap = new HashMap<String, Object>(2);
        Map<String, Object> paramsMap = new HashMap<String, Object>(4);
        List<Role> list = new ArrayList<Role>();
        int total = 0;
        paramsMap.put("start", params.getStart());
        paramsMap.put("pageSize", params.getPageSize());
        paramsMap.put("condition", params.getCondition());
        switch (params.getSelectedFiled()) {
        case "角色名称":
            paramsMap.put("selectedFiled", "role_name");
            break;
        case "角色代码":
            paramsMap.put("selectedFiled", "role_code");
            break;
        case "全部":
            paramsMap.put("selectedFiled", "1/0");
            break;
        default:
            paramsMap.put("selectedFiled", "");
            break;
        }
        // 获取查询到的数据
        list = roleDao.selectRolesByPage(paramsMap);
        // 获取记录总数
        total = roleDao.countByCondition(paramsMap);
        returnMap.put("rows", list);
        returnMap.put("total", total);
        return returnMap;
    }

    @Override
    public Map<String, Object> addRole(Role role) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        role.setCreatedTime(new Date());
        int result = roleDao.insert(role);
        returnMap.put("data", result);
        if (result > 0) {
            returnMap.put("message", "添加成功");
        } else {
            returnMap.put("message", "添加失败");
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> editRole(Role role) {
        Map<String, Object> returnMap = new HashMap<String,Object>();
        role.setEditTime(new Date());
        int result = roleDao.updateByPrimaryKey(role);
        returnMap.put("data", result);
        if (result > 0) {
            returnMap.put("message", "修改成功");
        } else {
            returnMap.put("message", "修改失败");
        }
        return returnMap;
    }

    @Override
    public Role selectRoleById(int roleId) {
        Role role = roleDao.selectByPrimaryKey(roleId);
        return role;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Map<String, Object> delRoles(Integer[] ids) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            int result = roleDao.deleteRoleBatch(ids);
            returnMap.put("data", result);
            if (result > 0) {
                returnMap.put("message", "删除成功");
            } else {
                returnMap.put("message", "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("data", -1);
            returnMap.put("message", "该角色已被使用，不能删除");
            return returnMap;
        }
        
        return returnMap;
    }

    @Override
    public List<Role> selectAllRoles() {
        List<Role> list = roleDao.selectAllRoles();
        return list;
    }
    
}
