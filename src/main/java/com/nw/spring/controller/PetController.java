package com.nw.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nw.spring.models.Customer;
import com.nw.spring.models.Pet;
import com.nw.spring.service.imp.CustomerServiceImp;
import com.nw.spring.service.imp.PetServiceImp;

@Controller
public class PetController {

	@Autowired
	private PetServiceImp petService;

	@Autowired
	private CustomerServiceImp customerService;

	@GetMapping("pet/create/{customerId}")
	public String showPetForm(@PathVariable("customerId") long customerId, Model model) {
		Optional<Customer> customer = customerService.findById(customerId);
		model.addAttribute("customer", customer.get());
		model.addAttribute("pet", new Pet());
		List<Pet> pets = petService.findByCustomer(customer.get());
		model.addAttribute("pets", pets);
		return "pet/pet_create";
	}

	@PostMapping("/pet/create/{customerId}")
	public String createPet(@PathVariable("customerId") long customerId, @Valid Pet pet, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		Optional<Customer> customer = customerService.findById(customerId);
		if (result.hasErrors()) {
			model.addAttribute("customer", customer.get());
			return "pet/pet_create";
		}
		List<Pet> pets = petService.findByCustomer(customer.get());
		model.addAttribute("pets", pets);
		model.addAttribute("customer", customer.get());
		pet.setCustomer(customer.get());
		petService.save(pet);
		redirectAttributes.addFlashAttribute("message", "Pet = " + pet.getName() + " have been created.");
		return "redirect:/showPet/{customerId}";
	}

	@GetMapping("/showPet/{customerId}")
	public String showPetPanel(@PathVariable long customerId, Model model) {
		Optional<Customer> customer = customerService.findById(customerId);
		model.addAttribute("customer", customer.get());
		List<Pet> pets = petService.findByCustomer(customer.get());
		model.addAttribute("pets", pets);
		model.addAttribute("title", "Customer and Pets");
		return "pet/pets";
	}

	@GetMapping("/deletePet/{id}")
	public String deletePetByCustomerId(@PathVariable("id") long id, Map<String, Object> map) {
		Optional<Pet> pet = petService.findById(id);
		Customer customer = pet.get().getCustomer();
		if (pet.isPresent()) {
			Boolean control = petService.delete(pet.get(), customer);
			if (control == true) {
				map.put("message", "Deleted successfully.");
			} else {
				map.put("message", "Delete fail.");
			}
		} else {
			map.put("message", "No record found.");
		}
		List<Pet> pets = petService.findByCustomer(customer);
		map.put("title", "Customer And Pets");
		map.put("pets", pets);
		map.put("customer", customer);
		return "pet/pets";
	}
	
	@GetMapping("/searchPet/{customerId}")
	public String searchPet(@PathVariable ("customerId") long id, @RequestParam("name") String name, Map<String, Object> map) {
		Optional<Customer> customer = customerService.findById(id);
		List<Pet> mypets = new ArrayList<>();
		if(customer.isPresent()) {
			List<Pet> pets = petService.findByCustomer(customer.get());
			pets.stream().forEach(item -> {
				if(item.getName().toLowerCase().equals(name.toLowerCase())) {
					mypets.add(item);
				}
			});
			if(mypets.size() > 0) {
				map.put("pets", mypets);
				map.put("message", name + " found.");
			} else {
				if (name.isEmpty()) {
					map.put("pets", pets);
				} else {
					map.put("pets", pets);
					map.put("message", name + " record not found.");
				}
			}
			map.put("customer", customer.get());
		}
		return "pet/pets";
	}
}
