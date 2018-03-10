<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page import="java.util.List"%>
<%@page import="static java.lang.System.out"%>
<%@page import="org.erratica.app.model.Account"%>
<%@page import="org.erratica.app.model.Progress"%>
<%@page import="org.erratica.app.service.IProgress"%>
<%@page import="org.erratica.app.service.IChampion"%>
<%@page import="org.erratica.app.service.ProgressImpl"%>
<%@page import="org.erratica.app.service.ChampionImpl"%>

<!DOCTYPE html>
<html lang="en">

	<head>
	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">   
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <title>ERRATICA | Profile</title>
		<spring:url value="/resources" var="urlPublic"/>
		<spring:url value="/" var="urlRoot"/>
		<spring:url value="/home" var="urlHome"/>
		<spring:url value="/account/profile" var="urlProfile"/>
		<spring:url value="/account/deleteAcc" var="urlDeleteAcc"/>
		<spring:url value="/account/newGame" var="urlNewGame"/>
		<spring:url value="/account/deleteGame" var="urlDeleteGame"/>
		<spring:url value="/account/championProgress" var ="urlProgressDetal"/>
		<spring:url value="/account/newChamp" var="urlNewChamp"/>
		<spring:url value="/account/deleteChamp" var="urlDeleteChamp"/>
		<spring:url value="/account/play" var="urlGame"/>
		<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
		<link href="${urlPublic}/css/myStyle.css" rel="stylesheet"> 
		
    </head>
    
	<body onload="init()">
	
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
		
			<div class="row" id="profileContainer">
				
				<div class="col-md-6" style="height:100%; width:69%; background-color:black; margin-right:2%;">
					<table border="1">
						<tr>
							<th>Nombre del mapa</th>
							<th>Nivel del mapa</th>
							<th>Tiempo en terminar el mapa</th>
							<th><a href="${urlNewGame}?idChamp=${champion.id}" class="btn btn-success btn-sm" role="button" id="newGame" title="Partida Nueva"><span>Nueva partida</span></a></th>
						</tr>
						<c:forEach var="progressList" items="${progressList}">
							<tr>
								<td>${progressList.map}</td>
								<td>${progressList.levelMap}</td>
								<td>${progressList.time}</td>
								<td><a href="${urlGame}?idProg=${progressList.id}" class="btn btn-success btn-sm" role="button" title="Play"><span>jugar</span></a>
								<a href="${urlDeleteGame}?idProg=${progressList.id}" class="btn btn-success btn-sm" role="button" title="Delete"><span>borrar</span></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				
				<div class="col-md-6" style="height:100%; width:29%; background-color:black;">
				
					<div id="contenedorEliminarCuenta" style="text-align:right;margin-top:3%;">
						<a href="${urlDeleteAcc}/${user.idAccount}" class="btn btn-danger btn-sm" role="button" title="Eliminar Cuenta"><span>Eliminar Cuenta</span></a>
					</div>
					
					<div id="contenedorPerfil" style="margin:10% 5% 10% 5%; text-align:center;">
						<%
							Account acc = (Account)request.getAttribute("user");
							String dateAcc = acc.getFechaCreacion().toString().substring(0, 10);
						%>
						<p style="font-size: 1.5vw;">${usuario.nameAccount}</p>  
						<p style="font-size: 1.2vw;">Fecha de creación: <%=dateAcc%>.</p>
						<p style="font-size: 1.2vw;">Número de personajes: ${nChampions} activos.</p>
					</div>
					
					<div id="contenedorPersonajes" style="margin:10% 5% 10% 5%; text-align:center;">
						<p style="font-size: 2vw;">Personajes</p>
						<a class="btn btn-success btn-sm" role="button" title="Personaje nuevo" data-toggle="modal" data-target="#myModal" id="newChamp"><span>Personaje nuevo</span></a>
						<div class="tab-content">
							<table>
								<c:forEach var="champions" items="${champions}">
									<tr>
										<td>${champions.championName}</td>
										<td><a href="${urlProgressDetal}?idChamp=${champions.id}" class="btn btn-success btn-sm" role="button" id="progressDetail" title="Partida Nueva"><span>Progreso</span></a></td>
										<td><a href="${urlDeleteChamp}?idChamp=${champions.id}" class="btn btn-success btn-sm" role="button" id="deleteChamp" title="Partida Nueva"><span>Eliminar</span></a></td>
									</tr>	
								</c:forEach>
							</table>
						</div>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
					    <div class="modal-dialog">
						    <div class="modal-content" style="width:68%;">
							    <form action="${urlNewChamp}" method="post">
							    	<input id="championName" type="text" name="championName" placeholder=" Introduzca un nombre para el personaje" style="border: black 2px solid;width:70%;color:black;" 
							   		pattern="[A-Za-z]{5,15}" title="El nombre ha de tener entre 5 y 15 letras" required/>
							   		<input type="submit" value="Crear personaje" style="color:black;border:0;"/>
							    </form>
						    </div>
						</div>
					</div>
				</div>
				
			</div>
						      	
		</div>
		
		<div id="footer"></div>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
	    <script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script> 
	    
	    <script type="text/javascript">
	    	function init(){
	    		var count = ${nChampions};
	    		if(count==5){
	    			$("#newChamp").css("pointer-events","none");
	    			$("#newChamp").css("display","inline-block");
	    		}
	    		if(count==0){
	    			$("#newGame").css("pointer-events","none");
	    			$("#newGame").css("display","inline-block");
	    		}
	    	}
	    </script>
	
	</body>
	
</html>