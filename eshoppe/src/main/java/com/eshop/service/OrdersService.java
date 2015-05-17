package com.eshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eshop.dao.Order;
import com.eshop.dao.OrdersDAO;
import com.eshop.dao.OrdersDAOJDBCImpl;

public class OrdersService {

	public void placeOrder(int customerId, Order order) {
		OrdersDAO dao = new OrdersDAOJDBCImpl();
		dao.createOrder(customerId, order);
	}

	public List<Order> getOrders(int customerId) {
		OrdersDAO dao = new OrdersDAOJDBCImpl();
		return dao.getOrdersForCustomer(customerId);
	}

	public List<Order> getOrdersForTheMonth(int customerId) {
		OrdersDAO dao = new OrdersDAOJDBCImpl();
		List<Order> orders = dao.getOrdersForCustomer(customerId);

		List<Order> ordersForTheMonth = new ArrayList<Order>();
		int currentMonth = new Date().getMonth();
		
		for (Order order : orders) {
			if (order.getOrderDate().getMonth() == currentMonth) {
				ordersForTheMonth.add(order);
			}
		}
		
		return ordersForTheMonth;
	}
}
