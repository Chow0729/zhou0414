package com.zhou.grad.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.auth.service.RoleService;
import com.zhou.grad.entity.Role;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    
    /**
     * @author ChaoQun Zhou
     * @param params 查询时的参数，封装在model中
     * @return
     */
    @ResponseBody
    @RequestMapping("getRolesByPage")
    public Map<String,Object> getRolesByPage(@ModelAttribute("params") QueryParamsModal params){
        Map<String,Object> map = new HashMap<String,Object>(1);
        map = roleService.selectRolesByPage(params);
        return map;
    }
    
    @ResponseBody
    @RequestMapping("addRole")
    public Map<String, Object> addRole(Role role) {
        Map<String, Object> returnMap = roleService.addRole(role);
        return returnMap;
    }
    
    @ResponseBody
    @RequestMapping("editRole")
    public Map<String, Object> editRole(Role role){
        return roleService.editRole(role);
    }
    
    @ResponseBody
    @RequestMapping("delRoles")
    public Map<String, Object> delRole(Integer[] ids) {
        return roleService.delRoles(ids);
    }
    
    @ResponseBody
    @RequestMapping("getRoleById")
    public Role getRoleById(int roleId) {
        
        return roleService.selectRoleById(roleId);
    }
    
    @ResponseBody
    @RequestMapping("getAllRoles")
    public Map<String, Object> getAllRoles() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Role> roles = roleService.selectAllRoles();
        map.put("roles", roles);
        return map;
    }
    
    /**
     * 跳转到角色管理页面
     * @return
     */
    @RequestMapping("toRoleManage")
    public String toRoleManage() {
        return "auth/role_manage";
    }
    
    @RequestMapping("toWeb")
    public String toWebSocket(){
        return "webSocket";
    }
}
