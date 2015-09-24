package com.kor.prpt.dao;

import com.kor.prpt.domain.Customer;
import com.kor.prpt.domain.User;

public abstract class DbActionUser {

	private String dbError;
	
	public DbActionUser() {
	}
	
	public String getDbError() {
		return dbError;
	}

	public void setDbError(String dbError) {
		this.dbError = dbError;
	}

	public abstract boolean insertDb();

}
