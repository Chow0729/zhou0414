package com.zhou.grad.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.business.dao.RoomDao;
import com.zhou.grad.business.dao.RoomTypeDao;
import com.zhou.grad.business.service.RoomService;
import com.zhou.grad.entity.Room;
import com.zhou.grad.entity.RoomType;

@Service
public class RoomServiceImpl implements RoomService{
	SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private RoomTypeDao roomTypeDao;

	@Override
	public Map<String, Object> saveRoom(Room room) {
		 Map<String, Object>  map=new HashMap<>();
		try {
			room.setCreatedTime(new Date());
			roomDao.insert(room);
			map.put("message", "success");
			map.put("value", "1");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "fail");
			map.put("value", "-1");
		}
		return map;
	}
	
	@Override
	public Map<String, Object> selectAllRoomByPage(QueryParamsModal params) {

        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("start", params.getStart());
        paramsMap.put("pageSize", params.getPageSize());
        paramsMap.put("condition", params.getCondition());
        paramsMap.put("status", 1);
        switch (params.getSelectedFiled()) {
        case "全部":
            paramsMap.put("selectedFiled", "1/0");
            break;
        case "类型":
            paramsMap.put("selectedFiled", "room_name");
            break;
        default:
            paramsMap.put("selectedFiled", "");
            break;
        }
        
        
        List<Room> list = roomDao.selectByPage(paramsMap);
        for (Room room : list) {
			room.setRoomType(roomTypeDao.selectByPrimaryKey(room.getRoomTypeId()).getRoomTypeName());
		}
        int total = roomDao.countAllRoom(paramsMap);
        returnMap.put("rows", list);
        returnMap.put("total", total);
        return returnMap;
    
	}

	@Override
	public void updateRoom(Room room) {
		try {
			roomDao.updateByPrimaryKey(room);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRoom(int[] ids) {
		try {
			if(ids!=null&&ids.length>0){
				for (int id : ids) {
					roomDao.deleteByPrimaryKey(id);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
