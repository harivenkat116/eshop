package com.eshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOJDBCImpl extends BaseDAO implements OrdersDAO {

	@Override
	public void createOrder(int customerId, Order order) throws DAOException {

		// step1 : insert into orders table
		// step2 : insert into order_details table with the same order id that
		// we got from the above step

		Connection con = null;
		PreparedStatement statement = null;

		int orderId = 0;

		try {
			con = getConnection();

			String sql = "insert into orders values (?,?,?,?,?)";

			statement = con.prepareStatement(sql);

			orderId = getNextSequenceValue("orders_seq");

			statement.setInt(1, orderId);
			statement.setInt(2, customerId);
			statement.setString(3, order.getOrderNumber());
			statement.setDate(4, new Date(order.getOrderDate().getTime()));
			statement.setDouble(5, order.getOrderAmount());

			int rowsInserted = statement.executeUpdate();

			System.out.println("rowsInserted = " + rowsInserted);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DAOException("Error occured while creating an order",
					ex);
		} finally {
			closeResources(null, statement, con);
		}

		insertIntoOrderDetails(orderId, order);
	}

	private void insertIntoOrderDetails(int orderId, Order order)
			throws DAOException {

		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = getConnection();

			String sql = "insert into order_details values (order_details_seq.nextval,?,?)";

			List<Product> products = order.getProducts();

			for (Product product : products) {
				statement = con.prepareStatement(sql);

				statement.setInt(1, orderId);
				statement.setInt(2, product.getId());

				statement.executeUpdate();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DAOException("Error occured while inserting the order",
					ex);
		} finally {
			closeResources(null, statement, con);
		}
	}

	@Override
	public List<Order> getOrdersForCustomer(int customerId) throws DAOException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		List<Order> orders = new ArrayList<Order>();
		ProductsDAO dao = new ProductsDAOJDBCImpl();

		try {
			con = getConnection();

			String sql = "select * from orders where customer_id = ?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, customerId);

			rs = statement.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("order_id");
				String order_Num = rs.getString("order_number");
				Date order_Date = rs.getDate("order_date");
				double order_amount = rs.getDouble("order_amount");
				List<Product> products;

				products = dao.getByOrderId(id);

				Order order = new Order(id, order_Num, order_Date,
						order_amount, products);
				orders.add(order);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DAOException("Error occured while selecting", ex);
		} finally {
			closeResources(rs, statement, con);
		}
		return orders;
	}

}
