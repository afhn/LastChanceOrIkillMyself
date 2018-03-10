<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

	<head>
	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">   
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <title>ERRATICA | Home</title>
		<spring:url value="/resources" var="urlPublic"/>
		<spring:url value="/" var="urlRoot"/>
		<spring:url value="/home" var="urlHome"/>
		<spring:url value="/account/profile" var="urlProfile"/>
		<spring:url value="/account/play" var="urlGame"/>
		<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
		<link href="${urlPublic}/css/myStyle.css" rel="stylesheet"> 
		
    </head>
    
	<body>
	
		<div id="header">
			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container">
				    <div class="navbar-header">
				    	<a class="navbar-brand" href="${urlHome}">ERRATICA</a>
				    </div>
				    <div id="navbar" class="navbar-collapse collapse">
					    <ul class="nav navbar-nav">  
						    <li><a href="${urlProfile}">Play</a></li>
						    <li><a href="${urlProfile}">${user.nameAccount}</a></li>
						    <li><a href="${urlRoot}">Salir</a></li>            
					    </ul>
				    </div>
				</div>
			</nav>
		</div>
		
		<div class="container" id="pContainer">
		
			
			
			      	
		</div>
		
		<div id="footer"></div>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
	    <script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script> 
	
	</body>
	
</html>