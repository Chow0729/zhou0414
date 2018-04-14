package com.zhou.grad.business.service;

import java.util.List;
import java.util.Map;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.entity.Room;
import com.zhou.grad.entity.RoomType;

public interface RoomService {
	/**
	 * 新增房间
	 * @author Liming
	 * @param room
	 */
	 Map<String, Object> saveRoom(Room room);
	/**
	 * 获取所有的房间
	 * @param params 
	 * @return
	 */
	Map<String, Object> selectAllRoomByPage(QueryParamsModal params);
	/**
	 * 更新
	 * @param room
	 */
	void updateRoom(Room room);
	/**
	 * 删除
	 * @param room
	 */
	void deleteRoom(int[] ids);
	

}
