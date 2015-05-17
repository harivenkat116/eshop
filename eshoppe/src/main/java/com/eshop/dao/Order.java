package com.eshop.dao;

import java.util.Date;
import java.util.List;

public class Order {
	private int id;
	private String orderNumber;
	private Date orderDate;
	private double orderAmount;

	private List<Product> products = null;

	public Order(int id, String orderNumber, Date orderDate,
			double orderAmount, List<Product> products) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderAmount = orderAmount;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public List<Product> getProducts() {
		return products;
	}
}
