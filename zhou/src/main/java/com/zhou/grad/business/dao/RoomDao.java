package com.zhou.grad.business.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhou.grad.entity.Room;
import com.zhou.grad.entity.RoomType;
@Repository
public interface RoomDao {
    int deleteByPrimaryKey(Integer roomId);

    int insert(Room room);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer roomId);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);
    /**
     * 分页查询所有的房间
     * @param paramsMap
     * @return
     */
	List<Room> selectByPage(Map<String, Object> paramsMap);
	/**
	 * 分页查询所有的房间数量
	 * @param paramsMap
	 * @return
	 */
	int countAllRoom(Map<String, Object> paramsMap);

}