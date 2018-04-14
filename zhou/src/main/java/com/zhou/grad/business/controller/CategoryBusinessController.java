package com.zhou.grad.business.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.service.CategoryBusinessService;
import com.zhou.grad.entity.Category;

@Controller
@RequestMapping("category")
public class CategoryBusinessController {
    
    @Autowired
    private CategoryBusinessService cateService;
    
    /**
     * 分页查询
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("getCatesByPage")
    public Map<String, Object> getCatesByPage(@ModelAttribute("params") QueryParamsModal params) {
        
        return  cateService.selectByPage(params);
    }
    
    /**
     * 添加
     * @return
     */
    @ResponseBody
    @RequestMapping("addCate")
    public Map<String, Object> addCate(Category cate) {
        return cateService.insertCate(cate);
    }
    
    /**
     * 修改
     * @return
     */
    @ResponseBody
    @RequestMapping("editCate")
    public Map<String, Object> editCate(Category cate) {
        return cateService.updateCate(cate);
    }
    
    /**
     * 批量删除
     * @return
     */
    @ResponseBody
    @RequestMapping("delBatch")
    public Map<String, Object> delBatch(Long[] ids) {
        return cateService.delBatchCates(ids); 
    }
    
    @ResponseBody
    @RequestMapping("getCateById")
    public Category getCateById(Long id) {
        return cateService.selectCateById(id);
    }

    /**
     * 跳转到管理页面
     * @return
     */
    @RequestMapping("toCategoryManage")
    public String toCategoryManage() {
        
        return "business/category_manage";
    }
}
