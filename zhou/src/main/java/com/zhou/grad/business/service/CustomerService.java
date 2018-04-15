package com.zhou.grad.business.service;

import java.util.Map;

import com.zhou.grad.entity.Customer;

public interface CustomerService {
	Map<String, Object> saveCutomer(Customer customer, Integer roomId);
}
