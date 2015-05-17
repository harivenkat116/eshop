package com.eshop.dao;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private List<Order> orders = new ArrayList<Order>();

	public Customer(int id, String firstName, String lastName, String address,
			String city, String state, String zipCode) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void addOrder(Order order) {
		this.orders.add(order);
	}

	public List<Order> getOrders() {
		return orders;
	}
}
