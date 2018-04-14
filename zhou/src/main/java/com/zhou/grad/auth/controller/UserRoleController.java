package com.zhou.grad.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.auth.service.UserRoleService;

@Controller
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService urService;
    
    @ResponseBody
    @RequestMapping("getUserRoleByPage")
    public Map<String, Object> getUserRoleByPage(QueryParamsModal params) {
        //System.out.println(params);
        Map<String, Object> map = urService.selectUserRoleByPage(params);
        return map;
    }
    
    @ResponseBody
    @RequestMapping("delUserRoleByUserId")
    public Map<String, Object> delUserRoleByUserId(int userId) {
        return urService.delUserRoleByUserId(userId);
    }
    
    /**
     * 修改单个用户的角色
     * @return
     */
    @ResponseBody
    @RequestMapping("editUserRoleByUserId")
    public Map<String, Object> editUserRoleByUserId(int userId, int roleId) {
        return urService.editUserRoleByUserId(userId, roleId);
    }
    
    /**
     * 批量修改用户角色
     * @return
     */
    @ResponseBody
    @RequestMapping("editUserRoleBatch")
    public Map<String, Object> editUserRoleBatch(int[] userIds, int roleId) {
        return urService.editUserRoleBatch(userIds, roleId);
    }
    
    @RequestMapping("toUserRoleManage")
    public String toUserRoleManage() {
        return "auth/user_role_manage";
    }
}
