package com.kor.prpt.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConnCustomer extends DbConn {

	public DbConnCustomer() {
		super();
	}

	@Override
	public boolean dbInit() {
		try {
			InitialContext initContext = new InitialContext();
			Context env = (Context) initContext.lookup("java:comp/env");
			super.setDs((DataSource) env.lookup("jdbc/postgres"));
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
