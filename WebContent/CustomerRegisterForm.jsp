<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.io.PrintWriter" %>    
<%@ page import="com.kor.prpt.vldt.CustomerVldt" %>
<%@ page import="com.kor.prpt.vldt.UserVldt" %>
<%@ page import="com.kor.prpt.dao.DbActionUser" %>
<%@ page import="com.kor.prpt.dao.DbActionCustomer" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
table, th, td {
    border: 1px solid black;
}
</style>

</head>
<body>

<%-- <jsp:useBean id="customer1" class="com.kor.prpt.domain.Customer" scope="session"></jsp:useBean>
<jsp:setProperty property="*" name="customer1"/>
<jsp:useBean id="customer2" class="com.kor.prpt.domain.Customer" scope="session"></jsp:useBean>
<jsp:setProperty property="*" name="customer2"/>
<jsp:useBean id="propertyAgent2" class="com.kor.prpt.domain.PropertyAgent" scope="session"></jsp:useBean>
--%>

<%-- <%
	String action = request.getParameter("action");	
	
	if(action != null && action.equals("submitted"))
		response.sendRedirect("CstmRgstCtrlServlet");
%>
 --%>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");	
	if(errorMsg != null && errorMsg.isEmpty() == false){
%>
	<%=errorMsg%>

<%}%>

<p/><a href="Register.jsp" >Original register</a>
<p/>customer register form
<!-- <form action="CustomerRegisterForm.jsp" method="post"> -->
<form action="CstmRgstCtrlServlet" method="post">
<table>

	<tr style="">
		<td>
		<input type="hidden" name="action" value="submitted">
		</td>
	</tr>

	<tr>
		<td>username</td>
		<td>
			<input type="text" name="username" size="30">
		</td>
	</tr>

	<tr>
		<td>password</td>
		<td>
			<input type="password" name="password" size="30">
		</td>
	</tr>

	<tr>
		<td>mobile</td>
		<td>
			<input type="text" name="mobile" size="30">
		</td>
	</tr>

	<tr>
		<td>email</td>
		<td>
			<input type="text" name="email" size="30">
		</td>
	</tr>

	<tr>
		<td>address</td>
		<td>
			<input type="text" name="address" size="30">
		</td>
	</tr>
	
	<tr>
		<td colspan="1">
			<input type="submit" name="btSubmit" value="Submit">
			<input type="reset" name="btCancel" value="Cancel">
		</td>
	</tr>

</table>
</form>
</body>
</html>
