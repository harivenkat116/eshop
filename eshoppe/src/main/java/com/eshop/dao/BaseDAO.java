package com.eshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {

	protected Connection getConnection() throws SQLException {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", "oms", "oms");
	}

	protected void closeResources(ResultSet rs, Statement statement,
			Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {
			// Nothing to be done, ignore it.
		}
	}

	protected int getNextSequenceValue(String sequenceName) throws DAOException {

		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			statement = con.createStatement();
			String sql = "select " + sequenceName + ".nextval from dual";

			rs = statement.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("nextval");
			}

			throw new DAOException("Invalid SQL for sequence next val");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DAOException("Error occured while selecting", ex);
		} finally {
			closeResources(rs, statement, con);
		}
	}

}
