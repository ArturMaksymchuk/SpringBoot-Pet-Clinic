package com.nw.spring.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nw.spring.models.Role;
import com.nw.spring.models.User;
import com.nw.spring.repository.RoleRepository;
import com.nw.spring.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping
	public String index(Map<String, Object> map) {
		map.put("title", "Pet Clinic");
		return "index";
	}
	
	@GetMapping("/register")
	public String registerUser(Model model, Map<String, Object> map) {
		List<Role> roles = roleRepository.findAll();
		map.put("title", "Register");
		map.put("roles", roles);
		model.addAttribute("user", new User());
		return "register";
	}
	
	@SuppressWarnings("serial")
	@PostMapping("/register")
	public String processRegisterUser(@ModelAttribute("user") User user, BindingResult result, Model model, Map<String, Object> map, HttpServletRequest request) {
		map.put("title", "Register");
		model.addAttribute("user", user);
		List<User> users = userRepository.getUserByEmail(user.getEmail());
		if(users.size() > 0) {
			map.put("message", "Email is available...");
		} else {
			if(result.hasErrors()) {
				map.put("message", "Error occured...");
				return "register";
			} else {
				long roleId = Integer.valueOf(request.getParameter("roles"));
				Role role = roleRepository.findById(roleId).get();
				user.setRoles(new HashSet<Role>() {
					{add(role);}
				});
				user.setReelPassword(user.getReelPassword());
				String pwd = user.getPassword();
				String encryptPwd = passwordEncoder.encode(pwd);
				user.setPassword(encryptPwd);
				map.put("message", "Registered successfully.");
				userRepository.save(user);
			}
		}
		return "register";
	}
}
