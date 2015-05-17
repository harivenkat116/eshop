package com.eshop.dao;

import java.util.List;

public interface OrdersDAO {
	
	void createOrder(int customerId, Order order) throws DAOException;
	List<Order> getOrdersForCustomer(int customerId) throws DAOException;
}
