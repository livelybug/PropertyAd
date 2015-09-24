<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.kor.prpt.domain.PropertyAgent" %>
<%@ page import="java.io.PrintWriter" %>    
<!DOCTYPE html>
<%@ taglib prefix="tags1" tagdir="/WEB-INF/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UserAction</title>
</head>
<body>


<jsp:useBean id="customer2" class="com.kor.prpt.domain.Customer" scope="session"></jsp:useBean>
<jsp:useBean id="propertyAgent2" class="com.kor.prpt.domain.PropertyAgent" scope="session"></jsp:useBean>
<%
	//?? how to move the wellcome page to the tag to be reused in many jsp?
	PrintWriter outP = response.getWriter();

	if(propertyAgent2 != null && propertyAgent2.getUsername() != null) {
		outP.println("wellcome agent " + propertyAgent2.getUsername());
	}
	else if(customer2 != null && customer2.getUsername() != null){
		outP.println("wellcome customer " + customer2.getUsername());
	}
%>


<jsp:useBean id="property1" class="com.kor.prpt.domain.Property" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="property1"/>

<p/> search for a property

<%
	String action = request.getParameter("userAction");	
	
	if(action != null && action.equals("submitted")){
		request.setAttribute("property1", property1);
		request.getRequestDispatcher("/PrptSrchServlet").forward(request, response);
	}
%>

<form action="UserAction.jsp" method="get">
<table>

	<tr style="">
		<td>
		<input type="hidden" name="userAction" value="submitted">
		</td>
	</tr>

<tags1:PropertyForm/>


<% 
	if(propertyAgent2 != null && propertyAgent2.getUsername() != null){
%>
	<p/><a href="PropertyForm.jsp">Add Property</a>
<%	
	}
%>

</body>
</html>