<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>

<fieldset name="Login">
    <legend><h2>Sign In</h2></legend>
    <form method="POST" action="sign-in"">
        <p>
            <label for="username">Username</label>
            <input type="text" name="username" required="required" placeholder="">
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" name="password" required="required" placeholder="password">
        </p>
        <p>
            <input type="checkbox" name="remember_me" id="remember_me">
            <label for="remeber_me">Remember me</label>
        </p>
        <p>
            <input type="submit" name="login" value="Login">
        </p> <a href="#">Forgot your password?</a>

    </form>
</fieldset>

</body>
</html>