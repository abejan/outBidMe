<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login result Page</title>
</head>
<body>
	<p>
        <div>
            <h1>Welcome, ${userName}</h1>
            <a href="${logout}?username=${userName}" id="logout">Logout</a>
        </div>

        <p> <a href="${homePageLink}">Return to home page</a> </p>
	</p>
</body>
</html>