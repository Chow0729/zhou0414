package com.zhou.grad.auth.dao;

import java.util.List;

import com.zhou.grad.auth.model.ResourceManageModal;
import com.zhou.grad.auth.model.ResourceModal;
import com.zhou.grad.entity.Resource;

public interface ResourceDao {
    int deleteByPrimaryKey(Integer resourceId);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer resourceId);
    
    /**
     * 查询所有的资源
     * @return
     */
    List<Resource> selectAllResources();
    
    /**
     * 查询所有的资源(子资源)
     * @return
     */
    List<ResourceModal> selectAllResourceWithChild();
    
    /**
     * 查询用户对应的角色拥有的资源
     * @param userId 用户id
     * @return
     */
    List<ResourceModal> selectResourceByUserId(Integer userId);
    
    /**
     * 资源管理中的查询资源
     * @return
     */
    List<ResourceManageModal> selectResourceTree();
    
    /**
     * 获取pid
     * @param pid
     * @return
     */
    Integer getPid(Integer pid);
    
    /**
     * 查询用户的资源url
     * @param userId
     * @return
     */
    List<String> selectResourceUrlByUserId(int userId);
    
    /**
     * 查询所有的资源url
     * @return
     */
    List<String> selectAllResourceUrls();

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}