<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Fotobook</title>
<link rel="stylesheet" type="text/css" href="/FotoBook/static/css/bgStyle.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/FotoBook/static/css/mycss.css">
<link rel="icon" href="static/img/music.png">
</head>
<body>
	<div class="w3-bar w3-black w3-padding-large">
	  	<a class="w3-bar-item" style="text-decoration: none"><img src="static/img/music1.png" width="20">otoBook</a>	  	
	  	<a href="/FotoBook/home1" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Albums</a>	
	  	<a href="#" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Photos</a>		  		 	  		  	   
	</div>
	
	<div class="w3-container">
		<div class="w3-container w3-border w3-round w3-margin w3-padding-large mesAlbums">
				<div class="w3-row">
					<div class="w3-col l6">
						<h4>Liste des albums</h4>
					</div>					
				</div>

				<div class="w3-row-padding">
					<c:choose>
						<c:when test="${ !empty albumsPublics }">
							<c:forEach items="${ albumsPublics }" var="album">
								<div class="w3-col s2 w3-card w3-round-large w3-margin w3-padding-large w3-border">
									<div class="w3-row-padding">
										<div class="w3-col s4">
											<i class="fa fa-folder w3-jumbo w3-text-orange"></i>
										</div>
										<div class="w3-col s8" style="margin-top: 5%">
											<a href="#" v-on:click="getSelectedAlbumName(`${ album.getName() }`)" class="w3-text-black albumName" data-id="${ album.getId() }">
												<c:out value="${ album.getName() }" />
											</a>
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
		
	</div>
</body>
</html>