package com.zhou.grad.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.service.RoomService;
import com.zhou.grad.business.service.RoomTypeService;
import com.zhou.grad.entity.Room;
import com.zhou.grad.entity.RoomType;

@Controller
@RequestMapping("room")
public class RoomController {
	private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;
    
//    @RequestMapping("toRoomManage")
//    public String toRoomManage() {
//        return "business/room_manage";
//    }
    
    @RequestMapping("toRoomManage")
    public ModelAndView getAllRoomType(){
    	ModelAndView mv=new ModelAndView("/business/room_manage");
    	List<RoomType> roomTypes=new ArrayList<>();
    	try {
    		roomTypes= roomTypeService.getAllRoomType();
    		if(roomTypes!=null&&roomTypes.size()>0){
    			mv.addObject("roomTypes", roomTypes);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
    	
    }
    
    @RequestMapping("saveRoom")
    public  Map<String, Object>  saveRoom(Room room) {
    	 Map<String, Object>  map=new HashMap<>();
    	try {
    		map=roomService.saveRoom(room);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("【RoomController.saveRoom()】 ERROR：" + "\n【参数值】：", e);
		}
    	return map;
    }
    
    @ResponseBody
    @RequestMapping("getAllRoomByPage")
    public Map<String, Object> getAllRoomTypeByPage(@ModelAttribute("params") QueryParamsModal params) {
        System.out.println(params+"     params");
        return roomService.selectAllRoomByPage(params);
    }
    
    @RequestMapping("updateRoom")
    public void updateRoom(Room room) {
    	roomService.updateRoom(room);
    }
    @RequestMapping("deleteRoom")
    @ResponseBody
    public void deleteRoom(int[] ids) {
    	try {
    		roomService.deleteRoom(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
