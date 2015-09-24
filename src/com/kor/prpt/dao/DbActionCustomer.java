package com.kor.prpt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kor.prpt.domain.Customer;

public class DbActionCustomer extends DbActionUser {
	
	private Customer customer;

/*	public DbActionCustomer() {
		super();
	}
*/	
	public DbActionCustomer(Customer customer) {
		super();
		this.customer = customer;
	}
	
	@Override
	public boolean insertDb() {
		DbConn dbObj = new DbConnCustomer();
		
		if(dbObj.dbInit() == false){
			setDbError("Fail to init to customerDb"); 
			return false;
		}
		
		Connection conn = dbObj.dbConnect();

		String sql = "insert into customer values(DEFAULT, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, customer.getMobile());  
			stmt.setString(2, customer.getEmail());
			stmt.setString(3, customer.getAddress());
			stmt.setString(4, customer.getUsername());
			stmt.setString(5, customer.getPassword());

			stmt.executeUpdate();
		} catch (SQLException e) {
			this.setDbError("Fail to insert to customerDb");
			e.printStackTrace();
			return false;
		}

		dbObj.dbDisconnect();
		
		return true;
	} 
}
