<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page trimDirectiveWhitespaces="true" %>

<%@ page  import="java.sql.ResultSet"%>
<%@ page  import="com.kor.prpt.dao.DbConn"%>
<%@ page  import="com.kor.prpt.dao.DbConnPropertyAgent"%>
<%@ page  import="java.sql.Connection"%>
<%@ page  import="org.apache.tomcat.dbcp.dbcp.DelegatingConnection"%>


<%@ page  import="org.postgresql.PGConnection"%>
<%@ page  import="org.postgresql.largeobject.LargeObject"%>
<%@ page  import="org.postgresql.largeobject.LargeObjectManager"%>

<%@ page  import="java.sql.SQLException"%>

<%@ page  import="java.io.ByteArrayInputStream"%>

<%@ page  import="java.io.InputStream"%>
<%@ page  import="java.io.PrintWriter"%>
<%@ page  import="java.util.Iterator"%>

<%@ page  import="javax.imageio.ImageIO"%>
<%@ page  import="javax.imageio.ImageReader"%>

<%@ page  import="javax.imageio.stream.ImageInputStream"%>

<%@ page  import="java.io.OutputStream"%>
<%@ page  import="java.io.FileOutputStream"%>"

<%@ page  import="java.nio.file.Path"%>
<%@ page  import="java.nio.file.Paths"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PrptList</title>
<style type="text/css">
table, th, td {
    border: 1px solid black;
}
</style>

</head>
<body>

<jsp:useBean id="propertyAgent2" class="com.kor.prpt.domain.PropertyAgent" scope="session"></jsp:useBean>
<%-- <jsp:useBean id="customer2" class="com.kor.prpt.domain.Customer" scope="session"></jsp:useBean> --%>

<%
	String loggedInUserName = (String) request.getAttribute("loggedInUserName");
	if(loggedInUserName != null){
%>
	<p/>wellcome <%=loggedInUserName %>
<%} %>
<p/> property list


<table>

	<tr>
		<th>Property Name</th>
		<th>Address</th>
		<th>Type</th>
		<th>AgentID</th>
		<th>ImageID</th>
		<th>Delete</th>
	</tr>

<%
	ResultSet rs = (ResultSet)request.getAttribute("prptRs");

	while(rs.next()) {
%>

	<tr>
		<td><%=rs.getString("name") %></td>
		<td><%=rs.getString("address") %></td>
		<td><%=rs.getString("rent_sale") %></td>
		<td><%=rs.getInt("agentid") %></td>
		<td><%
			long oid = rs.getLong(7);
	       LargeObject obj = null;
	       Connection conn = null;
	       StringBuilder imgName = new StringBuilder("tmpImg.");
	       if(rs.getLong(7) > 0) {
				try{
					DbConn dbObj = new DbConnPropertyAgent();
					dbObj.dbInit();
					conn = dbObj.dbConnect();
					conn.setAutoCommit(false);
					PGConnection pgCon = (PGConnection)((DelegatingConnection)conn).getInnermostDelegate();
			        LargeObjectManager lobj = pgCon.getLargeObjectAPI();
	
		        	obj = lobj.open(rs.getLong(7), LargeObjectManager.READ);
				    
	 				byte buf[] = new byte[obj.size()];
				    obj.read(buf, 0, obj.size());
				    
					ImageInputStream myInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(buf));
					Iterator<ImageReader> readers = ImageIO.getImageReaders(myInputStream);
					ImageReader reader;
					String formatName;
				    if (readers.hasNext()) {
				    	reader = readers.next();
						formatName = reader.getFormatName();
						imgName.append(formatName);
//write to local img file
						OutputStream outputStream = new FileOutputStream(imgName.toString());
						outputStream.write(buf, 0, buf.length);
						outputStream.close();
						Path p1 = Paths.get(imgName.toString());
						System.out.println(p1.toAbsolutePath());
//write to local img file
						
						PrintWriter outP = response.getWriter();
						String picUrl = "<img src=\"PicDispServlet?imgID=" + String.valueOf(rs.getLong(7)) + "\">";%>
						<%=picUrl %>
						<%
						//outP.println(picUrl);
						
/* 						response.reset();
						response.setContentType("image/" + formatName);
						response.addHeader("Content-Disposition","filename=logo." + formatName);
						response.getOutputStream().write(buf,0,buf.length);
						response.flushBuffer();
 */				    }
					
					myInputStream.close();
					;
				}
				 catch (SQLException e) {
					 ;// to log
				 }
 				finally{
/* 			        if(obj == null || obj.size() <= 0)
			        	continue;
 */
 					if(obj != null)
					    obj.close();
 					if(conn != null)
					    conn.close();
			    }
			}
			
		%>
		</td>
		<td>
		<% if( propertyAgent2 != null && propertyAgent2.getAgentID() == rs.getInt("agentid")){
		%>
				<p/><a href="DeleteProperty.jsp">Delete Property</a>
		<%	
		}%>
		</td>
	</tr>

<%
	}
	
	rs.close();
%>	
</table>

<p/><img src="images/tmpImg.png"></img>
</body>
</html>
