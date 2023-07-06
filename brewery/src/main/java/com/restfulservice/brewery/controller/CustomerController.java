package com.restfulservice.brewery.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfulservice.brewery.services.CustomerService;
import com.restfulservice.brewery.web.model.CustomerDto;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId){
		return new ResponseEntity<CustomerDto>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto){
		return new ResponseEntity<CustomerDto>(customerService.saveCustomer(customerDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable UUID customerId, @RequestBody CustomerDto customerDto){
		if(customerService.updateCustomer(customerId, customerDto)==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {			
			return new ResponseEntity<CustomerDto>(customerService.updateCustomer(customerId, customerDto), HttpStatus.ACCEPTED);
		}
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity deleteCustomer(@PathVariable UUID customerId) {
		customerService.deleteCustomerById(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping()
	public ResponseEntity<Collection<CustomerDto>> findAll(){
		return new ResponseEntity<Collection<CustomerDto>>(customerService.listAllCustomer(), HttpStatus.OK);
	}
}
