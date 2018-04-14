package com.zhou.grad.business.dao;

import java.util.List;
import java.util.Map;

import com.zhou.grad.entity.RoomType;

public interface RoomTypeDao {
    int deleteByPrimaryKey(Integer roomTypeId);

    /**
     * 添加房间类型
     * @param record
     * @return
     */
    int insert(RoomType record);

    int insertSelective(RoomType record);

    RoomType selectByPrimaryKey(Integer roomTypeId);
    
    /**
     * 分页查询所有的房间类型
     * @param params
     * @return
     */
    List<RoomType> selectByPage(Map<String, Object> map);
    
    /**
     * 统计所有的房间类型数量
     * @param params
     * @return
     */
    int countAllRoomType(Map<String, Object> map);
    

    int updateByPrimaryKeySelective(RoomType record);

    /**
     * 修改房间类型
     * @param record
     * @return
     */
    int updateByPrimaryKey(RoomType record);
    
    int deleteTypeByIds(Integer [] ids);
}