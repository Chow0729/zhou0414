package com.zhou.grad.auth.service;

import java.util.List;
import java.util.Map;

import com.zhou.grad.auth.model.ResourceManageModal;
import com.zhou.grad.auth.model.ResourceModal;
import com.zhou.grad.entity.Resource;

public interface ResourceService {

    /**
     * 查询所有的资源
     * @return
     */
    List<Resource> selectAllResources();
    
    /**
     * 根据用户id查询相应的资源
     * @param userId
     * @return
     */
    List<ResourceModal> selectResourceByUserId(int userId);
    
    /**
     * 修改资源
     * @param resource
     * @return
     */
    int updateResource(Resource resource);
    
    /**
     * 批量添加角色-资源
     * @return
     */
    Map<String, Object> addRoleResourceBatch(int[] resourceIds, int roleId);
    
    /**
     * 资源管理中的查询资源
     * @return
     */
    List<ResourceManageModal> selectResourcesTree();
    
    /**
     * 修改资源的状态
     * @param resource
     * @return
     */
    Map<String, Object> changeIsenable(Integer resourceId, Integer isenable);
    
    /**
     * 添加资源
     * @param resource
     * @return
     */
    Map<String, Object> addRersource(Resource resource);
    
    /**
     * 修改资源
     * @param resource
     * @return
     */
    Map<String, Object> editRersource(Resource resource);
    
    /**
     * 根据主键查询
     * @param resourceId
     * @return
     */
    Resource selectResourceById(Integer resourceId);
    
    /**
     * 删除资源
     * @param resourceId
     * @return
     */
    Map<String, Object> delResource(Integer resourceId);
}
