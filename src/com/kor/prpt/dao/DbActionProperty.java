package com.kor.prpt.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kor.prpt.domain.Property;
import com.kor.prpt.domain.PropertyAgent;

public class DbActionProperty extends DbActionUser{

	private Property property;
	private PropertyAgent propertyAgent;

	public DbActionProperty(Property property) {
		super();
		this.property = property;
	}

	
	public DbActionProperty(Property property, PropertyAgent propertyAgent) {
		super();
		this.property = property;
		this.propertyAgent = propertyAgent;
	}

	
	@Override
	public boolean insertDb() {
		DbConn dbObj = new DbConnPropertyAgent();

		if(dbObj.dbInit() == false){
			setDbError("Fail to init to propertyAgentDb"); 
			return false;
		}

		Connection conn = dbObj.dbConnect();

		String sql = "select agentid from propertyagent where username=? and password=? limit 1";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, propertyAgent.getUsername());
			stmt.setString(2, propertyAgent.getPassword());
			
			ResultSet rs = stmt.executeQuery();
			propertyAgent.setAgentID(0);

			if(rs.next()) {
				propertyAgent.setAgentID(rs.getInt("agentid"));
			}

			rs.close();

			if(propertyAgent.getAgentID() == 0)
				return false;
			
		} catch (SQLException e) {
			this.setDbError("Fail to get property agent ID");
			e.printStackTrace();
			return false;
		}

		sql = "insert into property values(DEFAULT, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, property.getName());
			stmt.setString(2, property.getAddress());
			stmt.setString(3, property.getRent_sale());
			stmt.setString(4, property.getImage());
			
			stmt.setString(5, property.getComment());
			stmt.setInt(6, propertyAgent.getAgentID());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			this.setDbError("Fail to insert property agent ID");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public ResultSet selectDb() {
		DbConn dbObj = new DbConnCustomer();
		
		if(dbObj.dbInit() == false){
			setDbError("Fail to init DbConnCustomer"); 
			return null;
		}

		Connection conn = dbObj.dbConnect();

		PreparedStatement stmt = null;
		StringBuilder strBl = new StringBuilder();
		strBl.append("select * from property where");

		try {			
			
			if(property.getAddress() != null && property.getAddress().isEmpty() == false){
				strBl.append(" address like ?");
				stmt = conn.prepareStatement(strBl.toString());
				stmt.setString(1, "%"+property.getAddress()+"%");
			}
			
			if(property.getName() != null && property.getName().isEmpty() == false){
				if(stmt  == null)
					strBl.append(" name like ?");
				else
					strBl = new StringBuilder(stmt.toString());
					strBl.append(" and name like ?");

				stmt = conn.prepareStatement(strBl.toString());
				stmt.setString(1, "%" + property.getName() + "%");
			}
			
			if(stmt == null){
				this.setDbError("No criterial found in search");
				return null;
			}
			
			ResultSet rs = stmt.executeQuery();
			return rs;			
		} catch (SQLException e) {
			this.setDbError("Fail to enquiry property");
			e.printStackTrace();
			return null;
		}
	}

}
