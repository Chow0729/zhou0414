package com.zhou.grad.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhou.grad.business.service.CustomerRoomService;

@Controller
@RequestMapping("/customerRoom")
public class CustomerRoomComtroller {
	@Autowired
	private CustomerRoomService customerRoomService;
	@RequestMapping("/deleteCustomerRoom")
	public void deleteCustomerRoom(Integer roomId) {
		customerRoomService.deleteCustomerRoom(roomId);
	}
}
