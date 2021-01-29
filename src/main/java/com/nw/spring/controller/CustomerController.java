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
public class CustomerController {
	@Autowired
	public CustomerServiceImp customerService;

	@Autowired
	public PetServiceImp petService;

	@GetMapping("/customers")
	public String getAllCustomers(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "customer/customers";
	}

	@GetMapping("/customer/reg")
	public String showCustomerRegistrationForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer/customer_registration";
	}

	@PostMapping("/customer/create")
	public String createCustomer(@Valid Customer customer, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "customer/customer_registration";
		}
		customerService.save(customer);
		redirectAttributes.addFlashAttribute("message", "Customer = " + customer.getFirstName() + " " + customer.getLastName() + " have been created.");
		return "redirect:/customers";
	}

	@GetMapping("customers/delete/{id}")
	public String deleteCustomerById(@PathVariable("id") long id, Map<String, Object> map) {
		Optional<Customer> customer = customerService.findById(id);
		List<Pet> pets = petService.findByCustomer(customer.get());
		if (customer.isPresent()) {
			Boolean control = customerService.delete(customer.get(), pets);
			if (control == true) {
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
	public String showUpdateCustomerForm(@PathVariable long id, Model model) {
		Optional<Customer> customer = customerService.findById(id);
		model.addAttribute("customer", customer.get());
		return "customer/customer_update";
	}

	@PostMapping("/customers/edit/{id}")
	public String processUpdateCustomerForm(@PathVariable long id, @Valid Customer customer, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "customer/customer_registration";
		}
		Customer c = customerService.findById(id).get();
		c.setId(customer.getId());
		c.setAddress(customer.getAddress());
		c.setEmail(customer.getEmail());
		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		c.setPhone(customer.getPhone());
		customerService.save(c);
		redirectAttributes.addFlashAttribute("message", "Customer = " + customer.getFirstName() + " " + customer.getLastName() + " have been updated.");
		return "redirect:/customers";
	}

	@GetMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("name") String name, Model model, RedirectAttributes redirectAttributes) {
		List<Customer> customers = new ArrayList<>();
		customers = customerService.findByFirstName(name);
		if (customers.size() > 0) {
			redirectAttributes.addFlashAttribute("message", name + " found.");
		} else {
			customers = customerService.findAll();
			if (name.isEmpty()) {
				//customers = customerService.findAll();
			} else {
				redirectAttributes.addFlashAttribute("message", name + " not found.");
			}
		}
		model.addAttribute("customers", customers);
		return "customer/customers";
	}
}
