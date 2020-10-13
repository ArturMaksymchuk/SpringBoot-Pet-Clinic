package com.nw.spring.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nw.spring.models.Customer;
import com.nw.spring.models.Pet;
import com.nw.spring.repository.PetRepository;
import com.nw.spring.service.PetService;

@Service
public class PetServiceImp implements PetService {

	//public final CustomerRepository customerRepository;
	public final PetRepository petRepository;

	public PetServiceImp(PetRepository petRepository) {
		//this.customerRepository = customerRepository;
		this.petRepository = petRepository;
	}

	@Override
	public List<Pet> findAll() {
		// TODO Auto-generated method stub
		return petRepository.findAll();
	}

	@Override
	public List<Pet> findByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return petRepository.findByCustomer(customer);
	}

	@Override
	public Boolean save(Pet pet) {
		// TODO Auto-generated method stub
		try {
			petRepository.save(pet);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	@Override
	public Optional<Pet> findById(long id) {
		// TODO Auto-generated method stub
		return petRepository.findById(id);
	}

	@Override
	public Boolean delete(Pet pet, Customer customer) {
		// TODO Auto-generated method stub
		try {
			pet.setCustomer(customer);
			petRepository.delete(pet);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

}
