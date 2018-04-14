package com.zhou.grad.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.service.FoodBusinessService;
import com.zhou.grad.entity.Category;
import com.zhou.grad.entity.Food;
import com.zhou.grad.entity.FoodCategory;
import com.zhou.grad.wechat.dao.FoodCategoryDao;
import com.zhou.grad.wechat.dao.FoodDao;
import com.zhou.grad.wechat.model.FoodDetailModel;

@Service
public class FoodBusinessServiceImpl implements FoodBusinessService{

    @Autowired
    private FoodDao foodDao;
    
    @Autowired
    private FoodCategoryDao fcDao;

    @Override
    public Map<String, Object> selectFoodsByPage(QueryParamsModal params) {
        Map<String, Object> returnMap = new HashMap<String, Object>(2);
        Map<String, Object> paramsMap = new HashMap<String, Object>(4);
        List<FoodDetailModel> list = new ArrayList<FoodDetailModel>();
        int total = 0;
        paramsMap.put("start", params.getStart());
        paramsMap.put("pageSize", params.getPageSize());
        paramsMap.put("condition", params.getCondition());
        switch (params.getSelectedFiled()) {
        case "菜名":
            paramsMap.put("selectedFiled", "name");
            break;
        case "种类":
            paramsMap.put("selectedFiled", "category_name");
            break;
        case "菜价":
            paramsMap.put("selectedFiled", "price");
            break;
        case "库存":
            paramsMap.put("selectedFiled", "stock");
            break;
        case "全部":
            paramsMap.put("selectedFiled", "1/0");
            break;
        default:
            paramsMap.put("selectedFiled", "");
            break;
        }
        // 获取查询到的数据
        list = foodDao.selectFoodsByPage(paramsMap);
        // 获取记录总数
        total = foodDao.countByCondition(paramsMap);
        returnMap.put("rows", list);
        returnMap.put("total", total);
        return returnMap;
    }

    @Override
    public Map<String, Object> insertFood(Food food, Category cate) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            //添加菜单
            int result = foodDao.insert(food);
            
            //添加菜单种类的关联信息
            FoodCategory fc = new FoodCategory();
            fc.setFoodId(food.getFoodId());
            fc.setCategoryId(cate.getCategoryId());
            fc.setCreatedTime(new Date());
            fcDao.insert(fc);
            returnMap.put("data", result);
            if (result>0) {
                returnMap.put("message", "添加成功");
            }else {
                returnMap.put("message", "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("data", -1);
            returnMap.put("message", "添加时异常");
            return returnMap;
        }
        
        return returnMap;
    }

    @Override
    public FoodDetailModel selectFoodById(Integer foodId) {
        return foodDao.selectByFoodId(foodId);
    }

    @Override
    public Map<String, Object> updataFood(Food food) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        int result = 0;
        try {
            result = foodDao.updateByPrimaryKey(food);
            returnMap.put("data", result);
            if(result > 0) {
                returnMap.put("message", "修改成功");
            }else {
                returnMap.put("message", "修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("date", -1);
            returnMap.put("message", "修改时异常");
            return returnMap;
        }
        return returnMap;
    }

    @Override
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public Map<String, Object> delFood(Integer[] ids) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            //删除菜单前先删除关联信息
            fcDao.deleteBatchByFoodId(ids);
            //再删除菜单的信息
            int result = foodDao.deleteBatchByFooId(ids);
            returnMap.put("data", result);
            if(result > 0) {
                returnMap.put("message", "删除成功"); 
            } else {
                returnMap.put("message", "删除失败"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("data", -1);
            returnMap.put("message", "删除时异常");
            return returnMap;
        }
        
        return returnMap;
    }
}
