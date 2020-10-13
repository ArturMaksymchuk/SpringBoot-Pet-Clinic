package com.nw.spring.service;

import java.util.List;
import java.util.Optional;

import com.nw.spring.models.Customer;
import com.nw.spring.models.Pet;

public interface PetService {
	
	public List<Pet> findAll();
	public List<Pet> findByCustomer(Customer customer);
	public Boolean save(Pet pet);
	public Optional<Pet> findById(long id);
	public Boolean delete(Pet pet, Customer customer);

}
