package com.zhou.grad.business.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.service.RoomTypeService;
import com.zhou.grad.entity.RoomType;

@Controller
@RequestMapping("roomType")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;
    
    @ResponseBody
    @RequestMapping("getAllRoomTypeByPage")
    public Map<String, Object> getAllRoomTypeByPage(@ModelAttribute("params") QueryParamsModal params) {
        System.out.println(params+"     params");
        return roomTypeService.selectAllRoomTypeByPage(params);
    }
    
    @ResponseBody
    @RequestMapping("addRoomType")
    public Map<String, Object> addRoomType(RoomType roomType){
        Map<String, Object> map=roomTypeService.addRoomType(roomType);
        return map;
    }
   
    @ResponseBody
    @RequestMapping("getRoomType")
    public RoomType getRoomType(int id){
        RoomType roomType=roomTypeService.geRoomTypeById(id);
        return roomType;
    }
    
    @ResponseBody
    @RequestMapping("editRoomType")
    public Map<String, Object> editRoomType(RoomType roomType){
        Map<String, Object> map=roomTypeService.updateRoomType(roomType);
        return map;
    }
    
    @ResponseBody
    @RequestMapping("delRoomType")
    public Map<String, Object> delRoomType(@RequestBody Integer [] ids){
        Map<String, Object> map=roomTypeService.delRoomTypes(ids);
        return map;
    }
}
