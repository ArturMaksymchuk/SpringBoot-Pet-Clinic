package com.nw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nw.spring.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	User findByEmail(String email);
	@Query("select u from User u where u.email = :email")
	List<User> getUserByEmail(@Param("email") String email);
}
