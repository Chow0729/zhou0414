package com.zhou.grad.business.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.service.RoomService;
import com.zhou.grad.entity.Room;

@Controller
@RequestMapping("room")
public class RoomController {
	private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;
    
    @RequestMapping("toRoomManage")
    public String toRoomManage() {
        return "business/room_manage";
    }
    
    
    
    @RequestMapping("saveRoom")
    public void saveRoom(Room room) {
    	try {
    		roomService.saveRoom(room);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("【RoomController.saveRoom()】 ERROR：" + "\n【参数值】：", e);
		}
    	
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
