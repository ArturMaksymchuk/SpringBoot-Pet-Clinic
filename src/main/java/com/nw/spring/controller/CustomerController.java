package com.nw.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nw.spring.models.Customer;
import com.nw.spring.models.Pet;
import com.nw.spring.service.imp.CustomerServiceImp;
import com.nw.spring.service.imp.PetServiceImp;

@Controller
public class CustomerController {
	@Autowired
	public CustomerServiceImp customerService;
	
	@Autowired
	public PetServiceImp petService;
	
	@GetMapping("/customers")
	public String getAllCustomers(Map<String, Object> map) {
		List<Customer> customers = customerService.findAll();
		map.put("title", "Customers");
		map.put("customers", customers);
		return "customer/customers";
	}
	
	@GetMapping("/customer/reg")
	public String showCustomerRegistrationForm(Map<String, Object> map) {
		map.put("title", "Customer Reistration");
		map.put("customer", new Customer());
		//map.put("citys", new ArrayList<City>(Arrays.asList(City.values())))
		return "customer/customer_registration";
	}
	
	@PostMapping("/customer/create")
	public String createCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model, Map<String, Object> map) {
		//map.put("customer", new Customer());
		map.put("title", "Customer Registration");
		System.out.println("Binding result : " + result);
		if(result.hasErrors()) {
			return "customer/customer_registration";
		} else {
			Boolean control = customerService.save(customer);
			if(control == false) {
				map.put("message", "A Problem Occured..");
			} else {
				map.put("message", "Registered Successfully.");
			}
		}
		return "customer/customer_registration";	
	}
	
	@GetMapping("customer/detail/{id}")
	public String detailCustomer(@PathVariable("id") long id, Map<String, Object> map) {
		Optional<Customer> customer = customerService.findById(id);
		if(customer.isPresent()) {
			map.put("title", "Customer Detail");
			map.put("customer", customer.get());
			return "customer/customer_detail";
		} else {
			List<Customer> customers = customerService.findAll();
			map.put("title", "Customers");
			map.put("customers", customers);
			map.put("message", "No record found.!!!");
		}
		return "customer/customers";
	}
	
	@GetMapping("customers/delete/{id}")
	public String deleteCustomerById(@PathVariable ("id") long id, Map<String, Object> map) {
		Optional<Customer> customer = customerService.findById(id);
		List<Pet> pets = petService.findByCustomer(customer.get());
		if(customer.isPresent()) {
			Boolean control = customerService.delete(customer.get(), pets);
			if(control == true) {
				map.put("message", "Deleted successfully!");
			} else {
				map.put("message", "Delete fail!");
			}
		} else {
			map.put("message", "No record found.");
		}
		List<Customer> customers = customerService.findAll();
		map.put("title", "Customers");
		map.put("customers", customers);
		return "customer/customers";
	}
	
	@GetMapping("/customers/edit/{id}")
	public String showUpdateCustomerForm(@PathVariable("id") long id, Map<String, Object> map) {
		 Optional<Customer> customer = customerService.findById(id);
		 if(customer.isPresent()) {
			 map.put("title", "Update Customer");
			 map.put("customer", customer.get());
			// map.put("message", "Updated successfully!");
			// model.addAttribute("citys", new ArrayList<Citys>(Arrays.asList(Citys.values())));
			 return "customer/customer_update";
		 } else {
			 List<Customer> customers = customerService.findAll();
			 map.put("title", "Customers");
			 map.put("message", "Customers");
			 map.put("customers", customers);
			 return "customer/customers";
		 }
	}
	
	@PostMapping("/customers/update/{id}")
	public String processUpdateCustomerForm(@PathVariable("id") long id, Customer customer, Map<String, Object> map, BindingResult result) {
		if (result.hasErrors()) {
			map.put("title", "Add Customer");
			return "customer/customer_registration";
		} else {
			Boolean control = customerService.save(customer);
			if (control == false) {
				map.put("message", "Problem occured.");
			} else {
				map.put("message", "Updated successfully.");
			}
		}
		List<Customer> customers = customerService.findAll();
		map.put("title", "Customers");
		map.put("customers", customers);
		
		/*
		Customer c = customerService.findById(id).orElseThrow(() -> new IllegalArgumentException(id + " is invalid."));
		c.setAddress(customer.getAddress());
		c.setEmail(customer.getEmail());
		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		c.setPhone(customer.getPhone());
		Boolean check = customerService.save(c);
		if (check == true) {
			map.put("message", "Update successfully.");
		} else {
			map.put("message", "Problem occured.");
		}
		*/
		return "customer/customers";
	}
	
	@GetMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("name") String name, Map<String, Object> map) {
		map.put("title", "Customers");
		List<Customer> customers = null;
		//map.put("customers", customers);
		customers = customerService.findByFirstName(name);
		if(customers.size() > 0) {
			map.put("customers", customers);
			//map.put("message", "Found customers.");
		} else {
			customers = customerService.findByLastName(name);
			if(customers.size() > 0) {
				map.put("customers", customers);
			} else {
				customers = customerService.findAll();
				map.put("customers", customers);
				//map.put("message", "No record customer.");
			}
		}
		return "customer/customers";
	}
}
