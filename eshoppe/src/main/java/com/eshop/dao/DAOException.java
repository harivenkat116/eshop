package com.eshop.dao;

public class DAOException extends RuntimeException {

	public DAOException(String errorMessage, Throwable ex) {
		super(errorMessage, ex);
	}

	public DAOException(String errorMessage) {
		super(errorMessage);
	}
}
