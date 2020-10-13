package com.nw.spring.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int age;

	@Column(name = "health_problem")
	private String healthProblem;

	@Enumerated(EnumType.STRING)
	private AnimalType type;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Pet() {
		// TODO Auto-generated constructor stub
	}

	public Pet(String name, int age, String healthProblem, AnimalType type, Customer customer) {
		super();
		this.name = name;
		this.age = age;
		this.healthProblem = healthProblem;
		this.type = type;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHealthProblem() {
		return healthProblem;
	}

	public void setHealthProblem(String healthProblem) {
		this.healthProblem = healthProblem;
	}

	public AnimalType getType() {
		return type;
	}

	public void setType(AnimalType type) {
		this.type = type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}

enum AnimalType {
	BIRD,
	WILD,
	CAT
}
