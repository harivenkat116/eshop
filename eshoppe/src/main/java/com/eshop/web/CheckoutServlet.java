package com.eshop.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshop.dao.Order;
import com.eshop.dao.OrdersDAO;
import com.eshop.dao.OrdersDAOJDBCImpl;
import com.eshop.dao.Product;

/**
 * Servlet implementation class CheckoutServlet
 */
public class CheckoutServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Product> products = (List<Product>) session.getAttribute("ShoppingCart");
		
		double orderAmount = calculateOrderAmount(products);
		
		String orderNumber = UUID.randomUUID().toString();
		
		Order order = new Order(0, orderNumber, new Date(), orderAmount, products);
		
		OrdersDAO dao = new OrdersDAOJDBCImpl();
		dao.createOrder(1, order);
		
		session.removeAttribute("ShoppingCart");
		
		request.setAttribute("Order", order);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/OrderConfirmation.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private double calculateOrderAmount(List<Product> products) {
		double orderAmount = 0;
		
		for(Product product : products) {
			orderAmount = orderAmount + product.getPrice();
		}
		
		return orderAmount;
	}	

}
