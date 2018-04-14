package com.zhou.grad.business.controller;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.service.CategoryBusinessService;
import com.zhou.grad.business.service.FoodBusinessService;
import com.zhou.grad.entity.Category;
import com.zhou.grad.entity.Food;
import com.zhou.grad.util.GetRequestUrlUtil;
import com.zhou.grad.util.UploadImageUtil;
import com.zhou.grad.wechat.model.FoodDetailModel;

@Controller
@RequestMapping("food")
public class FoodBusinessController {
    
    private static Properties properties;
    
    @Resource(name = "configProperties")
    public  void setProperties(Properties properties) {
        FoodBusinessController.properties = properties;
    }
    
    @Autowired
    private FoodBusinessService foodService;
    
    @Autowired
    private CategoryBusinessService cateService;
    
    @ResponseBody
    @RequestMapping("getFoodsByPage")
    private Map<String, Object> getFoodsByPage(@ModelAttribute("params") QueryParamsModal params) {
        Map<String, Object> map = foodService.selectFoodsByPage(params);
        return map;
    }
    
    /**
     * 添加
     * @param food
     * @param category
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("addFood")
    public Map<String, Object> addFood(Food food, Category category, MultipartFile file, HttpServletRequest request) {
        //保存图片
        setFile(request, file, food, category.getPinyinName());
        //添加食品和食品种类信息
        Map<String, Object> map = foodService.insertFood(food, category);
        return map;
    }
    
    /**
     * 修改
     * @param food
     * @param category
     * @param file
     * @param oldPinyinName 菜单修改前的种类
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("editFood")
    public Map<String, Object> editFood(Food food, Category category, MultipartFile file, HttpServletRequest request) {
        //判断file是否为空，空时表明没有修改图片
        if (file != null && file.getOriginalFilename() != "") {
            //删除菜单原有的图片
            boolean b = deleteFile(food);
            //添加菜单现有的图片
            if(b) {
                setFile(request, file, food, category.getPinyinName());
            }
        }
        
        //修改菜单信息
        Map<String, Object> map = foodService.updataFood(food);
        return map;
        //return null;
    }
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("delFood")
    public Map<String, Object> delFood(Integer[] ids) {
        Map<String, Object> map = null;
        if (ids != null && ids.length >0) {
            
            //删除图片
            FoodDetailModel f = foodService.selectFoodById(ids[0]);
            Food food = new Food();
            food.setImageUrl(f.getImageUrl());
            deleteFile(food);
            //删除菜单信息
            map = foodService.delFood(ids);
            
        }
        return map;
    }
    
    /**
     * 根据foodId查询
     * @param foodId
     * @return
     */
    @ResponseBody
    @RequestMapping("getFoodById")
    public FoodDetailModel getFoodById(Integer foodId) {
        FoodDetailModel food = foodService.selectFoodById(foodId);
        return food;
    }

    /**
     * 跳转到菜单管理页面
     * @return
     * @throws UnknownHostException 
     */
    @RequestMapping("toFoodManage")
    public String toFoodManage(HttpServletRequest request) throws UnknownHostException {
        //获取所有的菜品种类
        List<Category> list = cateService.selectAllCates();
        //获取请求地址
        String url = GetRequestUrlUtil.getRequestUrl(request);
        request.setAttribute("allCates", list);
        request.setAttribute("requestUrl", url);
        return "business/food_manage";
    }
    
    /**
     * 
     * @param request
     * @param file 要保存的图片
     * @param food 
     * @param pinyinName 食品种类的拼音名称
     */
    private void setFile(HttpServletRequest request, MultipartFile file, Food food, String pinyinName) {
        if (food != null && file != null && file.getOriginalFilename() != "") {
            try {
                // 上传图片   pic是图片的路径
                String pic = UploadImageUtil.uploadImg(file, "/wechat/food/" + pinyinName);
                food.setImageUrl(pic);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 删除指定路径的图片
     */
    private boolean deleteFile(Food food) {
        Boolean b = false;
        if(food != null && food.getImageUrl() != null) {
            b = UploadImageUtil.deleteImg(food);
        }
        return b;
    }
}
