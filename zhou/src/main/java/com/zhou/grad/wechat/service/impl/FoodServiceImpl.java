package com.zhou.grad.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.wechat.dao.FoodDao;
import com.zhou.grad.wechat.model.FoodReturnModel;
import com.zhou.grad.wechat.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodDao foodDao;

    @Override
    public List<FoodReturnModel> selectFoodsOrderByCatgid() {
        List<FoodReturnModel> list = foodDao.selectFoodsOrderByCatgid();
        return list;
    }
}
