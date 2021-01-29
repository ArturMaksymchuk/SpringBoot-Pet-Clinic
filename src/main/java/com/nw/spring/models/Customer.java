package com.nw.spring.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	@NotBlank(message = "First name required.")
	private String firstName;

	@Column(name = "last_name")
	@NotBlank(message = "Last name required.")
	private String lastName;

	@NotBlank(message = "Email is required.")
	@Email(message = "Please enter valid email address.")
	private String email;

	@NotBlank(message = "Enter your phone number.")
	private String phone;

	@NotBlank(message = "Enter your address.")
	private String address;

	//@NotNull(message = "Please select your city.")
	//@Enumerated(EnumType.STRING)
	//private City city;

	@OneToMany(mappedBy = "customer")
	private List<Pet> pets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*
	 * public City getCity() { return city; }
	 * 
	 * public void setCity(City city) { this.city = city; }
	 */

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

}

/*
 * enum City { YANGON, MAGWAY, MANDALAY, NAYPYITAW, OLD_BAGAN, TAUNGYI }
 */
