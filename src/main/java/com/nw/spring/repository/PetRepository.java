package com.nw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nw.spring.models.Customer;
import com.nw.spring.models.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	List<Pet> findByCustomer(Customer customer);
	List<Pet> findByType(String type);
	List<Pet> findByName(String name);
}
