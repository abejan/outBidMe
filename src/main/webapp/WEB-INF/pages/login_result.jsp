<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <link href="<c:url value="css/bootstrap/css/bootstrap.css" />" rel="stylesheet">
  <title>Login result Page</title>
</head>

<body>
	<p>
        <div>
            <h2>Welcome, ${userName}</h2>
            <a href="${logoutLink}?username=${userName}">Logout</a>
        </div>
	</p>
</body>
</html>