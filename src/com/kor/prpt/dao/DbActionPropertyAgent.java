package com.kor.prpt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kor.prpt.domain.PropertyAgent;

public class DbActionPropertyAgent extends DbActionUser{

	private PropertyAgent propertyAgent;
	
	/*public DbActionPropertyAgent() {
		super();
	}*/
	
	public DbActionPropertyAgent(PropertyAgent propertyAgent) {
		super();
		this.propertyAgent = propertyAgent;
	}

	@Override
	public boolean insertDb() {
		DbConn dbObj = new DbConnPropertyAgent();

		if(dbObj.dbInit() == false){
			setDbError("Fail to init to propertyAgent Db"); 
			return false;
		}

		Connection conn = dbObj.dbConnect();

		String sql = "insert into propertyagent values(DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, propertyAgent.getUsername());
			stmt.setString(2, propertyAgent.getMobile());  
			stmt.setString(3, propertyAgent.getEmail());
			stmt.setString(4, propertyAgent.getGender());
			stmt.setString(5, propertyAgent.getAddress());
			stmt.setString(6, propertyAgent.getLicence());
			stmt.setString(7, propertyAgent.getPassword());
			
			stmt.executeUpdate();

			
		} catch (SQLException e) {
			this.setDbError("Fail to insert to propertyagent Db");
			e.printStackTrace();
			return false;
		}

		sql = "select agentid from propertyagent where username=? and password=? limit 1";

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
		
		return true;
	}

}
