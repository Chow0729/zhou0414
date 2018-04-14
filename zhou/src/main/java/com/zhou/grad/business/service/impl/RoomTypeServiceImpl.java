package com.zhou.grad.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.dao.RoomTypeDao;
import com.zhou.grad.business.service.RoomTypeService;
import com.zhou.grad.entity.RoomType;

@Service
public class RoomTypeServiceImpl implements RoomTypeService{

    @Autowired
    private RoomTypeDao roomTypeDao;

    @Override
    public Map<String, Object> selectAllRoomTypeByPage(QueryParamsModal params) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("start", params.getStart());
        paramsMap.put("pageSize", params.getPageSize());
        paramsMap.put("condition", params.getCondition());
        paramsMap.put("status", params.getStatus());
        switch (params.getSelectedFiled()) {
        case "全部":
            paramsMap.put("selectedFiled", "1/0");
            break;
        case "类型":
            paramsMap.put("selectedFiled", "room_type_name");
            break;
        default:
            paramsMap.put("selectedFiled", "");
            break;
        }
        
        
        List<RoomType> list = roomTypeDao.selectByPage(paramsMap);
        int total = roomTypeDao.countAllRoomType(paramsMap);
        returnMap.put("rows", list);
        returnMap.put("total", total);
        return returnMap;
    }


    @Override
    public Map<String, Object> delRoomTypes(Integer[] ids) {
        Map<String, Object> map=new HashMap<String,Object>();
        int result=roomTypeDao.deleteTypeByIds(ids);
        map.put("result", result);
        if(result>0) {
            map.put("message", "删除成功");
        }else {
            map.put("message", "删除失败");
        }
        return map;
    }



    @Override
    public Map<String, Object> addRoomType(RoomType roomType) {
        Map<String, Object> map=new HashMap<String,Object>();
        int result=roomTypeDao.insert(roomType);
        map.put("result", result);
        if(result>0) {
            map.put("message", "添加成功");
        }else {
            map.put("message", "添加失败");
        }
        return map;
    }



    @Override
    public Map<String, Object> updateRoomType(RoomType roomType) {
        Map<String, Object> map=new HashMap<String,Object>();
        int result=roomTypeDao.updateByPrimaryKey(roomType);
        map.put("result", result);
        if(result>0) {
            map.put("message", "更新成功");
        }else {
            map.put("message", "更新失败");
        }
        return map;
    }


    @Override
    public RoomType geRoomTypeById(int id) {
        RoomType roomType=roomTypeDao.selectByPrimaryKey(id);
        return roomType;
    }
}
