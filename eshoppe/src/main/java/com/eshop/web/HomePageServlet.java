package com.eshop.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshop.dao.Product;
import com.eshop.dao.ProductsDAO;
import com.eshop.dao.ProductsDAOJDBCImpl;

/**
 * Servlet implementation class HomePageServlet
 */
public class HomePageServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello");
		ProductsDAO dao = new ProductsDAOJDBCImpl();
		List<Product> products = dao.getAllProducts();
		
		request.setAttribute("AllProducts", products);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
