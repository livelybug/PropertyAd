<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page  import="java.sql.ResultSet"%>        
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

</body>
</html>
