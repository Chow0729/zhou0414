package com.zhou.grad.auth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.auth.model.ResourceManageModal;
import com.zhou.grad.auth.service.ResourceService;
import com.zhou.grad.auth.service.RoleResourceService;
import com.zhou.grad.entity.Resource;
import com.zhou.grad.entity.RoleResource;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private RoleResourceService roleResourceService;
    
    @ResponseBody
    @RequestMapping("getResources")
    public List<Resource> getResources(int roleId) {
        //获取所有资源
        List<Resource> resources=resourceService.selectAllResources();
        List<RoleResource> roleResources=roleResourceService.selectRoleResourceByRoleId(roleId);
        for(Resource resource:resources) {
           for (RoleResource roleResource : roleResources) {
              //选中资源已有的角色
              if(resource.getResourceId()==roleResource.getResourceId()) {
                  //复选框设为true
                  resource.setChecked(true);
                  resourceService.updateResource(resource);
              }
            } 
        }
        return  resources;
    }
    
    @ResponseBody
    @RequestMapping("getResourcesTree")
    public List<ResourceManageModal> getResourcesTree() {
        List<ResourceManageModal> list = resourceService.selectResourcesTree();
        return list;
    }
    
    /**
     * 分配资源
     * @param resourceIds 所有的资源id
     * @param roleId 角色id
     * @return
     */
    @RequestMapping("dispatchResources")
    @ResponseBody
    public Map<String, Object> dispatchResources(int[] resourceIds,int roleId) {
        Map<String, Object> returnMap = resourceService.addRoleResourceBatch(resourceIds, roleId);
        return returnMap;
    }
    
    /**
     * 跳转到资源管理页面
     * @return
     */
    @RequestMapping("toResourceManage")
    public String toResourceManage() {
        return "auth/resource_manage";
    }
    
    @ResponseBody
    @RequestMapping("changeIsenable")
    public Map<String, Object> changeIsenable(Integer resourceId, Integer isenable) {
        
        return resourceService.changeIsenable(resourceId, isenable);
    }
    
    @ResponseBody
    @RequestMapping("addResource")
    public Map<String, Object> addResource(Resource resource) {
        System.out.println(resource);
        return resourceService.addRersource(resource);
    }
    
    @ResponseBody
    @RequestMapping("editResource")
    public Map<String, Object> editResource(Resource resource) {
        System.out.println(resource);
        return resourceService.editRersource(resource);
    }
    
    @ResponseBody
    @RequestMapping("getResourceById")
    public Resource getResourceById(Integer resourceId) {
        return resourceService.selectResourceById(resourceId);
    }
    
    @ResponseBody
    @RequestMapping("deleteLeafResource")
    public Map<String, Object> deleteLeafResource(Integer resourceId) {
        return resourceService.delResource(resourceId);
    }
}
