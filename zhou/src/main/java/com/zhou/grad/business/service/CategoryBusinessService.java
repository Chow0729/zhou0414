package com.zhou.grad.business.service;

import java.util.List;
import java.util.Map;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.entity.Category;

public interface CategoryBusinessService {

    /**
     * 获取所有的种类
     * @return
     */
    List<Category> selectAllCates();
    
    /**
     * 分页查询所有的种类
     * @param params
     * @return
     */
    Map<String, Object> selectByPage(QueryParamsModal params);
    
    /**
     * 新增
     * @param cate
     * @return
     */
    Map<String, Object> insertCate(Category cate);
    
    /**
     * 修改
     * @param cate
     * @return
     */
    Map<String, Object> updateCate(Category cate);
    
    /**
     * 批量删除
     * @param ids 要删除的种类的id
     * @return
     */
    Map<String, Object> delBatchCates(Long[] ids);
    
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Category selectCateById(Long id);
}
