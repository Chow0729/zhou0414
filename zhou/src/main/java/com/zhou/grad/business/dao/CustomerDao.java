package com.zhou.grad.business.dao;

import org.springframework.stereotype.Repository;

import com.zhou.grad.entity.Customer;

@Repository
public interface CustomerDao {

	int saveCustomer(Customer customer);

}
