package com.zhou.grad.business.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.grad.business.service.CustomerService;
import com.zhou.grad.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/saveCutomer")
	@ResponseBody
	public Map<String, Object> saveCustomer(Customer customer,Integer roomId) {
		try {
			customerService.saveCutomer(customer,roomId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
