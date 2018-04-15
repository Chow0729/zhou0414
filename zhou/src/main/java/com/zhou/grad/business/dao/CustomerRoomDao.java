package com.zhou.grad.business.dao;

import org.springframework.stereotype.Repository;

import com.zhou.grad.entity.CustomerRoom;

@Repository
public interface CustomerRoomDao {
	void saveRoomCutomer(CustomerRoom customerRoom);

	void deleteCustomerRoom(Integer roomId);
}
