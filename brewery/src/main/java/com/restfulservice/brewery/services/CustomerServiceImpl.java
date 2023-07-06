package com.restfulservice.brewery.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.restfulservice.brewery.repository.CustomerRepository;
import com.restfulservice.brewery.web.model.Customer;
import com.restfulservice.brewery.web.model.CustomerDto;

public class CustomerServiceImpl implements CustomerService {


	private final CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerDto getCustomerById(UUID CustomerId) {
		Optional<Customer> b = customerRepository.findById(CustomerId);
		if(b.isPresent()) {			
			CustomerDto customer = new CustomerDto();
			BeanUtils.copyProperties(b, customer);
			return customer;
		}
		else {
			return null;
		}
	}

	@Override
	public CustomerDto saveCustomer(CustomerDto customerDto) {
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDto, customer);
			customerRepository.save(customer);
			return customerDto;
	}
	
	@Override
	public CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto) {
		Optional<Customer> b = customerRepository.findById(customerId);
		if(b.isPresent()) {			
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDto, customer);
			customerRepository.save(customer);
			return customerDto;
		}
		else {			
			return null;
		}
	}

	@Override
	public void deleteCustomerById(UUID customerId) {
		customerRepository.deleteById(customerId);		
	}

	@Override
	public Collection<CustomerDto> listAllCustomer() {
		return customerRepository.findAll().stream().map(customer -> new CustomerDto(customer.getId(), customer.getName())).toList();
	}

}
