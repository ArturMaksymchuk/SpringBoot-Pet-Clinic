package com.nw.spring.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nw.spring.models.Customer;
import com.nw.spring.models.Pet;
import com.nw.spring.repository.CustomerRepository;
import com.nw.spring.repository.PetRepository;
import com.nw.spring.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {

	public final CustomerRepository customerRepository;
	public final PetRepository petRepository;
	
	public CustomerServiceImp(CustomerRepository customerRepository, PetRepository petRepository) {
		this.customerRepository = customerRepository;
		this.petRepository = petRepository;
	}
	
	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return customerRepository.findByFirstName(firstName);
	}

	@Override
	public List<Customer> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return customerRepository.findByLastName(lastName);
	}

	@Override
	public Boolean save(Customer customer) {
		// TODO Auto-generated method stub
		try {
			customerRepository.save(customer);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Optional<Customer> findById(long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id);
	}

	@Override
	public Boolean delete(Customer customer, List<Pet> pets) {
		// TODO Auto-generated method stub
		try {
			petRepository.deleteAll(pets);
			customerRepository.delete(customer);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
