<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

 	<link href="<c:url value="/bootstrap/bootstrap.css" />" rel="stylesheet">
 	<link href="<c:url value="/theme/home.css"/>"  rel="stylesheet"/>
 	
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>OutBidMe Home Pagee</title>
</head>

<body>

	<div id="header" class="row">
	
	    <div id="logo" class="col-md-2">
	       <img src="<c:url value="/images/logo.bmp"/>"  />
	    </div>
	    
	 	<div id="search_text" class="col-md-5">
       	   <input type="text" name="search_text">
       	</div>
       	
      	<div id="search_button" class="col-md-2">
       	   <button type="button">Search</button>
       	</div>
       	
		<div id="sign_in_link" class="col-md-3">
			<a href = ${loginPageLink}> Sign in </a>
		</div>
		
	</div>
	
</body>

</html>