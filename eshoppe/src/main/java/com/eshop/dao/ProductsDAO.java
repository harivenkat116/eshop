package com.eshop.dao;

import java.util.List;

public interface ProductsDAO {
	
	List<Product> getByOrderId(int orderId) throws DAOException;

	List<Product> getAllProducts() throws DAOException;

	Product findByPrimaryKey(int productId);
}
