package com.zhou.grad.business.service;

import java.util.Map;

import com.zhou.grad.auth.model.QueryParamsModal;
import com.zhou.grad.entity.RoomType;

public interface RoomTypeService {

    /**
     * 分页查询所有的房间类型
     * @param parmas
     * @return
     */
    Map<String, Object> selectAllRoomTypeByPage(QueryParamsModal params);
    
    /**
     * 添加房间类型
     * @param roomType
     * @return
     */
    Map<String, Object> addRoomType(RoomType roomType);
    
    /**
     * 修改房间类型
     * @param roomType
     * @return
     */
    Map<String, Object> updateRoomType(RoomType roomType);
    
    /**
     * 批量删除房间类型
     * @param ids
     * @return
     */
    Map<String, Object> delRoomTypes(Integer [] ids);
    
    /**
     * 根据id查看房间类型详情
     * @param id
     * @return
     */
    RoomType geRoomTypeById(int id);
    
    
}
