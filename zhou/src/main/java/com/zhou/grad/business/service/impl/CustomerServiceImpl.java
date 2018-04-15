package com.zhou.grad.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.grad.business.dao.CustomerDao;
import com.zhou.grad.business.dao.CustomerRoomDao;
import com.zhou.grad.business.service.CustomerService;
import com.zhou.grad.entity.Customer;
import com.zhou.grad.entity.CustomerRoom;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerRoomDao customerRoomDao;
	@Override
	public Map<String, Object> saveCutomer(Customer customer,Integer roomId) {
		try {
		 customerDao.saveCustomer(customer);
			CustomerRoom customerRoom=new CustomerRoom();
					customerRoom.setCustomerId(customer.getCustomerId());
					customerRoom.setRoomId(roomId);
			customerRoomDao.saveRoomCutomer(customerRoom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
