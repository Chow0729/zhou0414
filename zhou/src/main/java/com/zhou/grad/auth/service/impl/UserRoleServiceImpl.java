package com.zhou.grad.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.auth.dao.UserRoleDao;
import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.auth.model.UserRoleModal;
import com.zhou.grad.auth.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    private UserRoleDao urDao;

    @Override
    public Map<String, Object> selectUserRoleByPage(QueryParamsModal params) {
        Map<String,Object> returnMap = new HashMap<String,Object>(2);
        Map<String,Object> paramsMap = new HashMap<String,Object>(5);
        List<UserRoleModal> list = null;
        int total = 0;
        paramsMap.put("condition", params.getCondition());
        paramsMap.put("isRole", params.getStatus());
        paramsMap.put("start", params.getStart());
        paramsMap.put("pageSize", params.getPageSize());
        switch (params.getSelectedFiled()) {
        case "用户名称":
            paramsMap.put("selectedFiled", "user_role.user_name");
            break;
        case "部门名称":
            paramsMap.put("selectedFiled", "d.dep_name");
            break;
        case "角色名称":
            paramsMap.put("selectedFiled", "user_role.role_name");
            break;
        case "职位":
            paramsMap.put("selectedFiled", "uc.position");
            break;
        case "全部":
            paramsMap.put("selectedFiled", "1/0");
            break;
        default:
            paramsMap.put("selectedFiled", "");
            break;
        }
        //获取查询到的数据
        list = urDao.selectUserRoleByPage(paramsMap);
        //获取查询到的记录总数
        total = urDao.countUsers(paramsMap);
        returnMap.put("rows", list);
        returnMap.put("total", total);
        return returnMap;
    }

    @Override
    public Map<String, Object> delUserRoleByUserId(int userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = urDao.deleteByUserId(userId);
        map.put("data", result);
        if(result > 0) {
            map.put("message", "删除成功");
        } else {
            map.put("message", "删除失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> editUserRoleByUserId(int userId, int roleId) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        paramsMap.put("userId", userId);
        paramsMap.put("roleId", roleId);
        int result = urDao.updateByUserId(paramsMap);
        returnMap.put("data", result);
        if(result > 0) {
            returnMap.put("message", "修改成功");
        } else {
            returnMap.put("message", "修改失败");
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> editUserRoleBatch(int[] userIds, int roleId) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        paramsMap.put("userIds", userIds);
        paramsMap.put("roleId", roleId);
        int result = urDao.updateByUserIds(paramsMap);
        returnMap.put("data", result);
        if(result > 0) {
            returnMap.put("message", "修改成功");
        } else {
            returnMap.put("message", "修改失败");
        }
        return returnMap;
    }
    
}
