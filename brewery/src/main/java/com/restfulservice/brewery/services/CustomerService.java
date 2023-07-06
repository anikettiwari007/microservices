package com.restfulservice.brewery.services;

import java.util.Collection;
import java.util.UUID;

import com.restfulservice.brewery.web.model.CustomerDto;

public interface CustomerService {

	CustomerDto getCustomerById(UUID customerId);
	CustomerDto saveCustomer(CustomerDto customer);
	CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto);
	void deleteCustomerById(UUID customerId);
	Collection<CustomerDto> listAllCustomer();
}
