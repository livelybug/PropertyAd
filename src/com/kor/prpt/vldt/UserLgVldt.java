package com.kor.prpt.vldt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kor.prpt.dao.DbConn;
import com.kor.prpt.dao.DbConnCustomer;
import com.kor.prpt.domain.Customer;
import com.kor.prpt.domain.PropertyAgent;
import com.kor.prpt.domain.User;

public class UserLgVldt {

	User user;
	String vldtError;
	
	public UserLgVldt() {
	}

	public UserLgVldt(User user) {
		this.user = user;
	}

	public boolean vldt() {
		
		if(user.getUsername() == null || user.getUsername().isEmpty() == true) {
			vldtError = "user name is empty!";
			return false;
		}
		
		if(user.getPassword() == null || user.getPassword().isEmpty() == true) {
			vldtError = "password is empty!";
			return false;
		}
		
		DbConn dbObj = new DbConnCustomer();
		
		if(dbObj.dbInit() == false){
			vldtError = "Fail to init to customerDb!"; 
			return false;
		}
		
		Connection conn = dbObj.dbConnect();
		
		String sql;
		
		if(user instanceof Customer)
			sql = "select username from customer where username=? and password=?";
		else if (user instanceof PropertyAgent)
			sql = "select username from propertyagent where username=? and password=?";
		else {
			vldtError = "abnormal error in usr login validator!";
			return false;
		}
			
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, user.getUsername());  
			stmt.setString(2, user.getPassword());

			ResultSet rs = stmt.executeQuery();

			if(rs.next() == false) {
				vldtError = "fail to find user!";
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return true;
	}

	public String getVldtError() {
		return vldtError;
	}

	public void setVldtError(String vldtError) {
		this.vldtError = vldtError;
	}

	
}
