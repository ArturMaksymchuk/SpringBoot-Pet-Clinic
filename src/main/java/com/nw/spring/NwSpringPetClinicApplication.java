package com.nw.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nw.spring.models.Role;
import com.nw.spring.repository.RoleRepository;

@SpringBootApplication
public class NwSpringPetClinicApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(NwSpringPetClinicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role r1 = new Role();
		r1.setRole("ADMIN");
		Role r2 = new Role();
		r2.setRole("USER");
		Role r3 = new Role();
		r3.setRole("EDITOR");
		roleRepository.save(r1);
		roleRepository.save(r2);
		roleRepository.save(r3);
	}

}
