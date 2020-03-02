<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="static/css/mycss.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="static/css/home.css">
<link rel="icon" href="static/img/music.png">
<title>Fotobook | Accueil</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	
		<c:if test="${!empty sessionScope.user}">
			<div class="w3-container w3-border w3-round w3-margin w3-padding-large mesAlbums">
				<div class="w3-row">
					<div class="w3-col l6">
						<h4>Mes albums</h4>
					</div>
					<div class="w3-col l6">
						<h4><a href="#" onclick="document.getElementById('id01').style.display='block'" class="w3-btn w3-orange w3-small w3-round w3-right">Ajouter un album</a></h4>
					</div>
				</div>
				
				<div class="w3-row-padding">
					<c:choose>
						<c:when test="${ !empty albums }">
							<c:forEach items="${ albums }" var="album">
							    <div class="w3-col s2 w3-card w3-round-large w3-margin w3-padding-large w3-border">						
									<div class="w3-row-padding">
										<div class="w3-col s4">
											<i class="fa fa-folder w3-jumbo w3-text-orange"></i>
										</div>
										<div class="w3-col s8" style="margin-top: 5%">
											<a href="#"><c:out value="${ album.getName() }"/></a>
										</div>
									</div>											
								</div>
							</c:forEach>													
						</c:when>
						
						<c:otherwise>
							<p>Votre liste d'album est vide.</p>
						</c:otherwise>
					</c:choose>					
				</div>
			</div>
		</c:if>

	<div id="id01" class="w3-modal">
		<div class="w3-modal-content w3-animate-top w3-card-4 w3-round w3-padding-large" style="width: 25%">
			<header class="w3-container">
				<span onclick="document.getElementById('id01').style.display='none'"
					class="w3-button w3-display-topright w3-hover-red">&times;</span>
				<div class="w3-row">
					<div class="w3-col l2" style="margin-top: 10px"><i class="fa fa-folder w3-xxlarge"></i></div>
					<div class="w3-col l10">
						<h3>Ajouter un album</h3>
						<p style="font-size: 12px; color: orange;">${ errors["emptyFieldError"] }</p>
					</div>
				</div>
			</header>
			<hr>
			<div class="w3-container">
				<form action="/FotoBook/add_album" method="post">
					<div class="w3-row-padding">
						<input type="hidden" name="idUser" value="${ sessionScope.user.id }">
						<input type="text" name="albumName" required
							class="w3-input w3-border" placeholder="Nom de l'album..."> 
						<br><br>
						
						<select class="w3-select w3-border" name="theme" required>
							<option value="" disabled selected>Choisir le thème</option>
							<option value="Vacances">Vacances</option>
							<option value="Famille">Famille</option>
							<option value="Ami">Ami</option>
							<option value="travail">Travail</option>
						</select> 
						
						<br></br>
						
						<label><b>Accès : </b></label> <br>
						<input class="w3-radio" type="radio" name="access" value="public" required> <label>publique</label> 
						<input class="w3-radio" type="radio" name="access" value="prive" required> <label>privé</label>

					</div>
					<div class="w3-right w3-padding">
						<input type="submit" value="Valider" class="w3-btn w3-round w3-orange">
					</div>
				</form>
			</div>			
		</div>
	</div>
	
</body>
</html>