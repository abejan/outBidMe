<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app ="login">

<head>

 	<link href="<c:url value="/bootstrap/bootstrap.css" />" rel="stylesheet">
 	<link href="<c:url value="/theme/login.css"/>"  rel="stylesheet" media="screen" type="text/css" />
 	<link href="<c:url value="/theme/usermenu.css"/>"  rel="stylesheet" media="screen" type="text/css" />
	<link href="<c:url value="/theme/home.css"/>"   rel="stylesheet"/>
 	
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>OutBidMe Home Page</title>

</head>

<body>

	<script type="text/javascript" src="<c:url value="/js/framework/angular.min.js"/>"></script>
 	<script type="text/javascript" src="<c:url value="/js/framework/jquery-1.11.1.min.js"/>"></script>
 	<script type="text/javascript" src="<c:url value="/js/controllers/loginControllers.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/ui/loginEvents.js"/>"></script>

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
			<div id="wrap" ng-controller="LoginController as loginCtrl"
						   ng-submit="loginCtrl.sendCredentials()">
						   
				<!-- Sign-in / Registration bar -->
				<div id="regbar" ng-hide = "authenticated">
					<div id="navthing">
					    <div class = "link">
						     <a href="#" id="loginLink">Login</a> 
						       |
						     <a href="#" id="registerLink">Register</a>
					    </div> 															   
						<div class="login">
							<div class="arrow-up"></div>
							<div class="formholder">
								<div class="randompad">
								  <form>
									<fieldset>
										<label name="username">Username</label> 
										<input type="text" ng-model="loginCtrl.credentials.username"/> 
										<label name="password">Password</label>
										<input type="password" ng-model="loginCtrl.credentials.password"/> 
										<input type="submit" value="Login"/>
									</fieldset>
								  </form>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			 	<!-- Authenticated user menu -->
			    <div id="user-menu" class="row" ng-show="authenticated">
			    	<div id="welcome-message" class="col-md-3">
			   		   <label>{{authMessage}}</label>
			   		</div>
			   		<div id="notifications" class="col-md-3">
			   		   <label class="user-option">Notifications</label>
			   		</div>
			   		<div id="cart" class="col-md-3">
			   		   <label class="user-option">Cart</label>
			   		</div>
			   		<div id="account" class="col-md-3">
			   		   <label class="user-option">Account</label>
			   		</div>
			   </div>
			   
		</div>
		
	</div>
	
</body>

</html>