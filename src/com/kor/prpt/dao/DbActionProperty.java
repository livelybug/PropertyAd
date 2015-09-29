package com.kor.prpt.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.apache.tomcat.dbcp.dbcp.DelegatingConnection;
import org.postgresql.PGConnection;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import com.kor.prpt.domain.Property;
import com.kor.prpt.domain.PropertyAgent;

public class DbActionProperty extends DbActionUser{

	private Property property;
	private PropertyAgent propertyAgent;
	private Part imgPart;
	
	public DbActionProperty(Property property) {
		super();
		this.property = property;
	}

	public DbActionProperty(Property property, PropertyAgent propertyAgent) {
		super();
		this.property = property;
		this.propertyAgent = propertyAgent;
	}
	
	public DbActionProperty(Property property, PropertyAgent propertyAgent, Part imgPart) {
		super();
		this.property = property;
		this.propertyAgent = propertyAgent;
		this.imgPart = imgPart; 
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
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, property.getName());
			stmt.setString(2, property.getAddress());
			stmt.setString(3, property.getRent_sale());
			//stmt.setString(4, property.getImage());
			
			
			 
			 
			stmt.setString(4, property.getComment());
			//stmt.setString(4, property.getImage());
			stmt.setInt(5, propertyAgent.getAgentID());
			//stmt.setString(6, property.getImage());
			stmt.setLong(6, 0);
			
			 if (imgPart != null) {
		            // prints out some information for debugging
		            System.out.println(imgPart.getName());
		            System.out.println(imgPart.getSize());
		            System.out.println(imgPart.getContentType());
		         
		            PGConnection pgCon =
		            		(PGConnection)((DelegatingConnection)conn).getInnermostDelegate();
		            
		            LargeObjectManager lobj = pgCon.getLargeObjectAPI();
		            long oid = lobj.createLO(LargeObjectManager.READ | LargeObjectManager.WRITE);
		            LargeObject obj = lobj.open(oid, LargeObjectManager.WRITE);
		            
		            // obtains input stream of the upload file
		            FileInputStream  inputStream = (FileInputStream) imgPart.getInputStream();
		            byte buf[] = new byte[2048];
		            int s, tl = 0;
		            while ((s = inputStream.read(buf, 0, 2048)) > 0) {
		            	obj.write(buf, 0, s);
		                tl += s;
		            }
		            stmt.setLong(6, oid);
		            inputStream.close();
		        }
			
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException | IOException e) {
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
				else{
					strBl = new StringBuilder(stmt.toString());
					strBl.append(" and name like ?");
				}
				
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
