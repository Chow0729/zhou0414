package com.zhou.grad.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.auth.dao.ResourceDao;
import com.zhou.grad.auth.dao.RoleResourceDao;
import com.zhou.grad.auth.model.ResourceManageModal;
import com.zhou.grad.auth.model.ResourceModal;
import com.zhou.grad.auth.service.ResourceService;
import com.zhou.grad.entity.Resource;
import com.zhou.grad.util.ResourceTreeUtil;

@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceDao resourceDao;
    
    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<Resource> selectAllResources() {
        return resourceDao.selectAllResources();
    }

    @Override
    public List<ResourceModal> selectResourceByUserId(int userId) {
        List<ResourceModal> list = resourceDao.selectResourceByUserId(userId);
        return ResourceTreeUtil.getChildPerms(list, 0);
    }

    @Override
    public int updateResource(Resource resource) {
        int result = resourceDao.updateByPrimaryKey(resource);
        return result;
    }

    @Override
    public Map<String, Object> addRoleResourceBatch(int[] resourceIds, int roleId) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        //先删除相应的数据
        int result = roleResourceDao.deleteByRoleId(roleId);
        //添加数据
        if (result >= 0 && resourceIds.length > 0) {
            paramsMap.put("resourceIds", resourceIds);
            paramsMap.put("roleId", roleId);
            paramsMap.put("createdTime", new Date());
            result = roleResourceDao.insertBatch(paramsMap);
        }
        returnMap.put("data", result);
        if (result > 0) {
            returnMap.put("message", "资源分配成功");
        } else {
            returnMap.put("message", "资源分配失败");
        }
        return returnMap;
    }

    @Override
    public List<ResourceManageModal> selectResourcesTree() {
        List<ResourceManageModal> lists = resourceDao.selectResourceTree();
        for (ResourceManageModal resourceManageModal : lists) {
            if (resourceManageModal.getParentId() != null && resourceManageModal.getParentId() > 0) {
                Integer paraentId = resourceDao.getPid(resourceManageModal.getParentId());
                for (int i = 2; i < 100; i++) {
                    if (paraentId == null) {
                        resourceManageModal.setLevel(i);
                        break;
                    } else {
                        paraentId = resourceDao.getPid(paraentId);
                        continue;
                    }
                }
            }
        }
        return lists;
    }

    @Override
    public Map<String, Object> changeIsenable(Integer resourceId, Integer isenable) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        String result = "";
        try {
            Resource resource = new Resource();
            resource.setResourceId(resourceId);
            resource.setIsenable(isenable);
            resourceDao.updateByPrimaryKeySelective(resource);
            if (isenable == 1) {
                result = "资源启用成功！";
            } else {
                result = "资源停用成功";
            }
            returnMap.put("success", result);
        } catch (Exception e) {
            returnMap.put("error", "操作失败！");
            e.printStackTrace();
            return returnMap;
        }
        return returnMap;
        
    }

    @Override
    public Map<String, Object> addRersource(Resource resource) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (resource.getPid() == null) {
            resource.setPid(0);
        }
        resource.setCreatedTime(new Date());
        int result = resourceDao.insert(resource);
        returnMap.put("data", result);
        if (result > 0) {
            returnMap.put("message", "添加成功");
        } else {
            returnMap.put("message", "添加失败");
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> editRersource(Resource resource) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        resource.setEditTime(new Date());
        int result = resourceDao.updateByPrimaryKeySelective(resource);
        returnMap.put("data", result);
        if (result > 0) {
            returnMap.put("message", "修改成功");
        } else {
            returnMap.put("message", "修改失败");
        }
        return returnMap;
    }

    @Override
    public Resource selectResourceById(Integer resourceId) {
        return resourceDao.selectByPrimaryKey(resourceId);
    }

    @Override
    public Map<String, Object> delResource(Integer resourceId) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
        try {
            //删除资源前先删除角色资源的关联信息
            roleResourceDao.deleteByResourceId(resourceId);
            
            int result = resourceDao.deleteByPrimaryKey(resourceId);
            returnMap.put("data", result);
            if (result > 0) {
                returnMap.put("message", "删除成功");
            } else {
                returnMap.put("message", "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("data", -1);
            returnMap.put("message", "删除资源时异常");
            return returnMap;
        }
        
        return returnMap;
    }
    
}
