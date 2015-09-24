package com.kor.prpt.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class DbConn {

	private DataSource ds = null;
	private Connection conn = null;

	public DbConn() {
	}
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public abstract boolean dbInit();
	
	public Connection dbConnect() {
		
		if(ds == null)
			return null;

		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return conn;
	}
		
	public boolean dbDisconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
