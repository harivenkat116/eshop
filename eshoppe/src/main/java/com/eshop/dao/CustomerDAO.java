package com.eshop.dao;

import java.util.List;

public interface CustomerDAO {
	
	List<Customer> getAllCustomers() throws DAOException;
	Customer findByPrimaryKey(int id) throws DAOException;
	void createCustomer(Customer customer) throws DAOException;
	void updateCustomer(Customer customer) throws DAOException;
	void deleteCustomer(int customerId) throws DAOException;

}
