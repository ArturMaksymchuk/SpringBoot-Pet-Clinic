package com.nw.spring.service;

import java.util.List;
import java.util.Optional;

import com.nw.spring.models.Customer;
import com.nw.spring.models.Pet;

public interface CustomerService {
	
	public List<Customer> findAll();
	public List<Customer> findByFirstName(String firstName);
	public List<Customer> findByLastName(String lastName);
	public Boolean save(Customer customer);
	public Optional<Customer> findById(long id);
	public Boolean delete(Customer customer, List<Pet> pets);

}
