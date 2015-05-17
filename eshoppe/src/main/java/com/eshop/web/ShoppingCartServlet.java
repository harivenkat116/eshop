package com.eshop.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshop.dao.Product;
import com.eshop.service.ProductsService;

/**
 * Servlet implementation class ShoppingCartServlet
 */
public class ShoppingCartServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productIdStr = request.getParameter("productId");
		int productId = Integer.parseInt(productIdStr);
		
		Product product = new ProductsService().getById(productId);
		
		HttpSession session = request.getSession();
		
		//any tab exists with this name
		if(session.getAttribute("ShoppingCart") == null) {
			
			//creating a tab
			List<Product> products = new ArrayList<Product>();
			
			//Adding this product to the tab
			products.add(product);
			
			//Adding this tab to the session
			session.setAttribute("ShoppingCart", products);
		} else {
			List<Product> products = (List<Product>) session.getAttribute("ShoppingCart");
			products.add(product);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ShoppingCart.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
