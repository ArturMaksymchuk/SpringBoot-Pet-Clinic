package com.nw.spring.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nw.spring.models.User;
import com.nw.spring.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		UserDetails userDetails = null;
		if(user != null) {
			userDetails = new UserDetails();
			userDetails.setUser(user);
		} else {
			throw new UsernameNotFoundException("User not exist with this name : " + username);
		}
		return userDetails;
	}

}
