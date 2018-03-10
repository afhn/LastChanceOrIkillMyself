<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page import="org.erratica.app.model.Account"%>
<%@page import="org.erratica.app.service.IChampion"%>
<%@page import="org.erratica.app.service.ChampionImplementation"%>

<!DOCTYPE html>
<html lang="en">

	<head>
	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">   
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <title>ERRATICA | Game</title>
		<spring:url value="/resources" var="urlPublic"/>
		<spring:url value="/" var="urlRoot"/>
		<spring:url value="/home" var="urlHome"/>
		<spring:url value="/account/profile" var="urlProfile"/>
		<spring:url value="/account/play" var="urlGame"/>
		<spring:url value="/account" var="urlGameStart"/>
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
			<pre style="word-wrap: break-word; white-space: pre-wrap; display: none;">{"idChampion":"${idChampion}","idProgreso":"${idProgress}"}</pre>
			<div class="row" id="profileContainer">	 
			<div id="gameContainer" style="width: 100%; height: 100%; margin: auto"></div>
			</div>
			     	
		</div>
		
		<div id="footer"></div>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
	    <script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script> 
	    
    	<script src="${urlPublic}/Build/UnityLoader.js"></script> 
	    <script>
	      var gameInstance = UnityLoader.instantiate("gameContainer", "/app/resources/Build/Juego.json");
	    </script>
	
	</body>
	
</html>