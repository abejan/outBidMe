<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login result Page</title>
</head>
<body>
	<h1>${resultMessage}</h1>
	<p>${homePageLink}
	<%
	   Boolean isSuccesful = (Boolean) session.getAttribute("isAuthenticated");
	   if(!isSuccesful){
	%>
	   ${loginPageLink}
	<%
	   }
	%>
	</p>
</body>
</html>