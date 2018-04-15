package com.zhou.grad.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.business.dao.CustomerRoomDao;
import com.zhou.grad.business.service.CustomerRoomService;
@Service
public class CustomerRoomServiceImpl implements CustomerRoomService {
	@Autowired
	private CustomerRoomDao customerRoomDao;
	@Override
	public void deleteCustomerRoom(Integer roomId) {
		
		customerRoomDao.deleteCustomerRoom(roomId);
	}

}
