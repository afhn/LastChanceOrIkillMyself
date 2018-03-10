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
	    <title>ERRATICA | Register</title>
		<spring:url value="/resources" var="urlPublic"></spring:url>
		<spring:url value="/" var="urlRoot"></spring:url>
		<spring:url value="/save" var="urlForm" />
		<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
		<link href="${urlPublic}/css/myStyle.css" rel="stylesheet"> 
    </head>
	<body>
	
		<div id="header">
			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container">
				    <div class="navbar-header">
				    	<a class="navbar-brand" href="${urlRoot}">ERRATICA</a>
				    </div>
				</div>
			</nav>
		</div>
		
		<div class="container" id="pContainer">
		
			<div class="row" id="loggingContainer">
			
				<div class="col-xl-6">
				
					<form class="form-horizontal" action="${urlForm}" method="post">
					
						<div id="loggingStyle">	
						
					        <div class="input-group" style="width:40%;">
						    	 <label for="nameAccount">Account name:</label>
						    	<input id="nameAccount" type="text" class="form-control" name="nameAccount" placeholder="Account" style="border: black 2px solid;" required/>
						    </div>
						    
						    <div class="input-group" style="width:40%;">
						    	<label for="password">Password:</label>
						    	<input id="password" type="password" class="form-control" name="password" placeholder="Password" style="border: black 2px solid;" required/>
						    </div>
						    
						    <div class="input-group" style="width:40%;">
						    	<label for="respuestaSecreta">Cuál es tu película o serie preferida?</label>
						    	<input id="respuestaSecreta" type="text" class="form-control" name="respuestaSecreta" placeholder="Respuesta secreta" style="border: black 2px solid;" required/>
						    </div>
							<br>
							
						    <button type="submit">Registrarse</button>
							
						</div>
						
			      	</form> 
			      	
				</div>
				
			</div>	
			      	
		</div>
		
		<div id="footer"></div>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
	    <script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script> 
	
	</body>
</html>
