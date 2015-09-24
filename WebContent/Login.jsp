<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.kor.prpt.domain.PropertyAgent" %>
<%@ page import="java.io.PrintWriter" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UserAction</title>
</head>
<body>


<jsp:useBean id="customer1" class="com.kor.prpt.domain.Customer" scope="session"></jsp:useBean>
<jsp:useBean id="propertyAgent1" class="com.kor.prpt.domain.PropertyAgent" scope="session"></jsp:useBean>


<%
	String errorMsg = (String) request.getAttribute("errorMsg");	
	if(errorMsg != null && errorMsg.isEmpty() == false){
%>
	<%=errorMsg%>

<%}%>


<%
	String cstAction = request.getParameter("customerAction");	

	if(cstAction != null && cstAction.equals("submitted")){%>
	
	<jsp:setProperty property="*" name="customer1"/>
	
	<%
		response.sendRedirect("CstmLgServlet");  
		//request.getRequestDispatcher("CstmLgServlet").forward(request, response);
	}
%>


<%
	String agtAction = request.getParameter("agentAction");	
	
	if(agtAction != null && agtAction.equals("submitted")){%>

		<jsp:setProperty property="*" name="propertyAgent1"/>	

		<%
		response.sendRedirect("PrptAgLgServlet");  //?? the method can only be "GET"
				// make the form's action the servlet directly
				//this pattern is not used with cretical data
		//request.getRequestDispatcher("CstmLgServlet").forward(request, response);
	}
%>

<p/>Not register? <a href="Register.jsp" >Click here</a>

<p/> cusomter login 
<form action="Login.jsp" method="post">
<table>

	<tr style="">
		<td>
		<input type="hidden" name="customerAction" value="submitted">
		</td>
	</tr>

	<tr>
		<td>customer name</td>
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
		<td colspan="1">
			<input type="submit" name="btSubmit" value="Sign In">
			<input type="reset" name="btCancel" value="Cancel">
		</td>
	</tr>

</table>
</form>


<p/> agent login 
<form action="Login.jsp" method="post">
<table>

	<tr style="">
		<td>
		<input type="hidden" name="agentAction" value="submitted">
		</td>
	</tr>

	<tr>
		<td>agent name</td>
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
		<td colspan="1">
			<input type="submit" name="btSubmit" value="Sign In">
			<input type="reset" name="btCancel" value="Cancel">
		</td>
	</tr>

</table>
</form>

</body>
</html>