package com.zhou.grad.wechat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.wechat.model.FoodReturnModel;
import com.zhou.grad.wechat.service.FoodService;

@Controller
@RequestMapping("wechat")
public class FoodController {

    @Autowired
    private FoodService foodService;
    
    @ResponseBody
    @RequestMapping("getAllFoodsByCategory")
    public List<FoodReturnModel> getAllFoodsByCategory() {
        
        List<FoodReturnModel> list = foodService.selectFoodsOrderByCatgid();
        return list;
    }
}
