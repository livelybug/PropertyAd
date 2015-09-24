<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.Connection" %>
<%@ page import="com.kor.prpt.vldt.PropertyAgentVldt" %>
<%@ page import="com.kor.prpt.vldt.UserVldt" %>
<%@ page import="java.io.PrintWriter" %>    
<%@ page import="com.kor.prpt.dao.DbActionUser" %>
<%@ page import="com.kor.prpt.dao.DbActionPropertyAgent" %>
<%@ page import="com.kor.prpt.domain.PropertyAgent" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>property agent register form</title>

<style type="text/css">
table, th, td {
    border: 1px solid black;
}
</style>

</head>
<body>

<jsp:useBean id="propertyAgent1" class="com.kor.prpt.domain.PropertyAgent" scope="session"></jsp:useBean>
<jsp:setProperty property="*" name="propertyAgent1"/>
<jsp:useBean id="customer2" class="com.kor.prpt.domain.Customer" scope="session"></jsp:useBean>

<%

	String action = request.getParameter("action");	
	
	if(action != null && action.equals("submitted")){
		
		//request.removeAttribute("action");
		//request.getRequestDispatcher("/PropertyAgtCtrlServlet").forward(request, response);
		//response.sendRedirect("/PropertyAgtCtrlServlet"); //?? not working, why?		
		response.sendRedirect("PropertyAgtCtrlServlet");//?? any security issue? or "/J2EE-APP-04-PropertyAgent/PropertyAgtCtrlServlet" better?
	}
	/*String action = request.getParameter("action");	
	
	if(action != null && action.equals("submitted")){
		PrintWriter outP = response.getWriter();
		UserVldt vldt =  new PropertyAgentVldt(propertyAgent1);
		DbActionPropertyAgent dbAct = new DbActionPropertyAgent(propertyAgent1);
		
		if( vldt.validateUser() == false ){
			outP.println(vldt.getVldtErr());
		}
		else if ( dbAct.insertDb() == false){
			outP.println(dbAct.getDbError());
		}
		else {
			session.removeAttribute("customer2");
			response.sendRedirect("UserAction.jsp");
 			//request.getRequestDispatcher("UserAction.jsp").forward(request, response);
		}
	}
	*/
%>

<%
	String errorMsg = (String) request.getAttribute("errorMsg");	
	if(errorMsg != null && errorMsg.isEmpty() == false){
%>
	<%=errorMsg%>

<%}%>

<p/><a href="Register.jsp" >Original register</a>
<p/>property agent register form
<form action="PropertyAgentRegisterForm.jsp" method="post">
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
		<td>gender</td>
		<td>
			<input type="radio" name="gender" value="Male">Male
			<input type="radio" name="gender" value="Female">Female
		</td>
	</tr>

	<tr>
		<td>address</td>
		<td>
			<input type="text" name="address" size="30">
		</td>
	</tr>
	
	<tr>
		<td>licence</td>
		<td>
			<input type="text" name="licence" size="30">
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
