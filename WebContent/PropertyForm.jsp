<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.Connection" %>
<%@ page import="com.kor.prpt.vldt.PropertyVldt" %>
<%@ page import="com.kor.prpt.vldt.UserVldt" %>
<%@ page import="java.io.PrintWriter" %>    
<%-- <%@ page import="com.kor.prpt.dao.DbActionUser" %> --%>
<%@ page import="com.kor.prpt.dao.DbActionProperty" %>
<%@ page import="com.kor.prpt.dao.DbActionPropertyAgent" %>
<%@ page import="com.kor.prpt.domain.PropertyAgent" %>
<%@ page import="javax.servlet.http.Part" %>

<%@ taglib prefix="tags1" tagdir="/WEB-INF/tags" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- <style type="text/css">
table, th, td {
    border: 1px solid black;
}
</style>
 -->
<title>PropertyForm</title>
</head>
<body>

<jsp:useBean id="property1" class="com.kor.prpt.domain.Property" scope="session"></jsp:useBean>
<jsp:useBean id="propertyAgent2" class="com.kor.prpt.domain.PropertyAgent" scope="session"></jsp:useBean>
<jsp:setProperty property="*" name="property1"/>

<%
	String errorMsg = (String) request.getAttribute("errorMsg");	
	if(errorMsg != null && errorMsg.isEmpty() == false){
%>
	<p/> ${requestScope.errorMsg}

<%}%>


<p/>property form
<p/> agent name	<%=propertyAgent2.getUsername() %>
	
<%-- <%
	String action = request.getParameter("propertAction");	
	
	if(action != null && action.equals("submitted")){
		Part part = request.getPart("image");
		PrintWriter outP = response.getWriter();
 		PropertyVldt vldt =  new PropertyVldt(property1, propertyAgent2);
		DbActionProperty dbAct = new DbActionProperty(property1, propertyAgent2, part);
		
		if( vldt.validateProperty() == false ){
			outP.println(vldt.getVldtErr());
		}
		else if ( dbAct.insertDb() == false){
			outP.println(dbAct.getDbError());
		}
		else {
			request.getRequestDispatcher("Good.jsp").forward(request, response);
		}
	}
%>
 --%>
  
 
<!-- <form action="PropertyForm.jsp" method="post" enctype="multipart/form-data">
 -->
<form action="PrptCtlServlet" method="post" enctype="multipart/form-data">
<table>
	<tr style="">
		<td>
		<input type="hidden" name="propertAction" value="submitted">
		</td>
	</tr>

<tags1:PropertyForm/>

</body>
</html>