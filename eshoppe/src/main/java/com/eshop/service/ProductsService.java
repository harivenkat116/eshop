package com.eshop.service;

import com.eshop.dao.Product;
import com.eshop.dao.ProductsDAO;
import com.eshop.dao.ProductsDAOJDBCImpl;

public class ProductsService {
	
	public Product getById(int productId) {
		ProductsDAO dao = new ProductsDAOJDBCImpl();
		return dao.findByPrimaryKey(productId);
	}

}
