package com.zhou.grad.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.regex.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.service.CategoryBusinessService;
import com.zhou.grad.entity.Category;
import com.zhou.grad.wechat.dao.CategoryDao;

@Service
public class CategoryBusinessServiceImpl implements CategoryBusinessService{

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> selectAllCates() {
        List<Category> list = categoryDao.selectAll();
        return list;
    }

    @Override
    public Map<String, Object> selectByPage(QueryParamsModal params) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        paramsMap.put("condition", params.getCondition());
        paramsMap.put("start", params.getStart());
        paramsMap.put("pageSize", params.getPageSize());
        //分页查询所有的种类
        List<Category> list = categoryDao.selectByPage(paramsMap);
        //统计种类的数量
        int result = categoryDao.countByCondition(paramsMap);
        returnMap.put("rows", list);
        returnMap.put("total", result);
        return returnMap;
    }

    @Override
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public Map<String, Object> insertCate(Category cate) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = categoryDao.insert(cate);
            map.put("data", result);
            if(result > 0) {
                map.put("message", "添加成功");
            } else {
                map.put("message", "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", -1);
            map.put("message", "添加时异常");
            return map;
        }
        
        return map;
    }

    @Override
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public Map<String, Object> updateCate(Category cate) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = categoryDao.updateByPrimaryKey(cate);
            map.put("data", result);
            if(result > 0) {
                map.put("message", "修改成功");
            } else {
                map.put("message", "修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", -1);
            map.put("message", "修改时异常");
            return map;
        }
        
        return map;
    }

    @Override
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public Map<String, Object> delBatchCates(Long[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = categoryDao.deleteBatch(ids);
            map.put("data", result);
            if(result > 0) {
                map.put("message", "删除成功");
            } else {
                map.put("message", "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", -1);
            map.put("message", "删除时异常");
            return map;
        }
        
        return map;
    }

    @Override
    public Category selectCateById(Long id) {
        Category category = categoryDao.selectByPrimaryKey(id);
        return category;
    }
}
