package com.zhou.grad.wechat.dao;

import java.util.List;
import java.util.Map;

import com.zhou.grad.entity.Category;

public interface CategoryDao {
    int deleteByPrimaryKey(Long categoryId);
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(Long[] ids);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long categoryId);
    
    /**
     * 查询所有的种类
     * @return
     */
    List<Category> selectAll();
    
    /**
     * 分页查询所有的种类
     * @param map 参数
     * @return
     */
    List<Category> selectByPage(Map<String, Object> map);
    
    /**
     * 统计所有的种类
     * @param map
     * @return
     */
    int countByCondition(Map<String, Object> map);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}