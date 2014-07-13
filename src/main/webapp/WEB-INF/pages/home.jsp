<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app ="login">

<head>

 	<link href="<c:url value="/bootstrap/bootstrap.css" />" rel="stylesheet">
 	<link href="<c:url value="/theme/login.css"/>"  rel="stylesheet" media="screen" type="text/css" />
	<link href="<c:url value="/theme/home.css"/>"    rel="stylesheet"/>
 	
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>OutBidMe Home Page</title>
</head>

<body>

	<script type="text/javascript" src="<c:url value="/js/framework/angular.min.js"/>"></script>
 	<script type="text/javascript" src="<c:url value="/js/framework/jquery-1.11.1.min.js"/>"></script>
 	<script type="text/javascript  src="<c:url value="/js/loginModule.js"/>"></script>
	<script src="<c:url value="/js/login.js"/>"></script>

	<div id="header" class="row">
	
	    <div id="logo" class="col-md-3">
	       <img src="<c:url value="/images/logo.bmp"/>"  />
	    </div>
	    
	 	<div id="search_text" class="col-md-4 logo_neighbours">
       	   <input type="text" name="search_text">
       	</div>
       	
      	<div id="search_button" class="col-md-1 logo_neighbours">
       	   <button type="button">Search</button>
       	</div>
       	
		<div id="sign_in_link"  class="col-md-4 logo_neighbours" >
			<div id="wrap">
				<div id="regbar">
					<div id="navthing">
					    <div class = "link">
						     <a href="#" id="loginLink">Login</a> 
						       |
						     <a href="#" id="registerLink">Register</a>
					    </div> 															   
						<div class="login" ng-controller="LoginController as loginController"
										   ng-submit="loginController.sendCredentials()">
							<div class="arrow-up"></div>
							<div class="formholder">
								<div class="randompad">
									<fieldset>
										<label name="email">Email</label> 
										<input type="email" value="example@example.com" ng-model="loginController.credentials.username"/> 
										<label name="password">Password</label>
										<input type="password" ng-model="loginController.credentials.password"/> 
										<input type="submit" value="Login" />
									</fieldset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
</body>

</html>