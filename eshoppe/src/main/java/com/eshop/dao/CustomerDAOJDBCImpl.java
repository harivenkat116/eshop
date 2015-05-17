package com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOJDBCImpl extends BaseDAO implements CustomerDAO {

	@Override
	public List<Customer> getAllCustomers() throws DAOException {
		
		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		
		List<Customer> customers = new ArrayList<Customer>();
		
		try
		{
			con = getConnection();
			statement = con.createStatement();
			
			rs = statement.executeQuery("select * from customers");
			
			while(rs.next())
			{
				int custId = rs.getInt("customer_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String address = rs.getString("address");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				
				Customer customer = new Customer(custId, firstName, lastName, address, city, state, zip);
				customers.add(customer);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new DAOException("Error occured while selecting", ex);
		} finally {
			closeResources(rs, statement, con);
		}
		
		return customers;
	}

	@Override
	public void createCustomer(Customer customer) throws DAOException {
		
		Connection con = null;
		PreparedStatement statement = null;
		
		try
		{
			con = getConnection();
			
			String sql = "insert into customers values (customers_seq.nextval,?,?,?,?,?,?)";
			
			statement = con.prepareStatement(sql);
			
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getAddress());
			statement.setString(4, customer.getCity());
			statement.setString(5, customer.getState());
			statement.setString(6, customer.getZipCode());
			
			System.out.println(sql.toString());
			
			int rowsInserted = statement.executeUpdate();
			
			System.out.println("rowsInserted = " + rowsInserted);
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new DAOException("Error occured while creating the customer", ex);
		} finally {
			closeResources(null, statement, con);
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws DAOException {
		Connection con = null;
		PreparedStatement statement = null;
		
		try
		{
			con = getConnection();
			
			String sql = "update 	customers " +
			             "set 	 	first_name = ?, " +
			             "			last_name = ?, " +
			             "			address = ?, " +
			             "			city = ?, "+
			             "			state = ?, " +
			             "			zip = ? " +
			             "where 	customer_id = ?";	
			
			statement = con.prepareStatement(sql);
			
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getAddress());
			statement.setString(4, customer.getCity());
			statement.setString(5, customer.getState());
			statement.setString(6, customer.getZipCode());
			statement.setInt(7, customer.getId());
			
			int rowsUpdated = statement.executeUpdate();
			
			System.out.println("rowsUpdated = " + rowsUpdated);
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new DAOException("Error occured while updating", ex);
		} finally {
			closeResources(null, statement, con);
		}
	}

	@Override
	public void deleteCustomer(int customerId) throws DAOException {
		Connection con = null;
		PreparedStatement statement = null;
		
		try
		{
			con = getConnection();
			statement = con.prepareStatement("delete from customers where customer_id = ?");
			
			statement.setInt(1, customerId);
			
			int rowsDeleted = statement.executeUpdate();
			
			System.out.println("rowsDeleted = " + rowsDeleted);
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new DAOException("Error occured while deleting", ex);
		} finally {
			closeResources(null, statement, con);
		}
	}

	@Override
	public Customer findByPrimaryKey(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
