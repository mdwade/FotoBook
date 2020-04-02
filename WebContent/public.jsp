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
	  	<c:if test="${!empty sessionScope.user}">	  	
	  		<a href="/FotoBook/home" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Mes albums</a>
	  		<a href="/FotoBook/public" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Albums publiques</a>
	  		<a href="#" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Albums autorisés</a>
	  	</c:if>
	  	
	  	<c:if test="${sessionScope.user.userType == 'ADMIN'}">
			<a href="/FotoBook/users" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white w3-hide-small">Gérer les utilisateurs</a>
		</c:if>		
		
		<c:if test="${!empty sessionScope.user}"> 
			<div class="w3-dropdown-hover w3-mobile w3-hide-small w3-right">
	  			<button class="w3-btn w3-hover-black w3-text-grey w3-hover-text-white">			    	
					<i class="fa fa-user-circle w3-large"></i> ${sessionScope.user.firstName} ${sessionScope.user.lastName}					
			    </button>
			    <div class="w3-dropdown-content w3-bar-block w3-dark-grey">
			      <a href="/FotoBook/sign_out" class="w3-bar-item w3-btn w3-black">Déconnexion</a>
			    </div>
		   </div>
		</c:if>	
		<c:if test="${empty sessionScope.user}">	
			<div class="w3-right">
				<a href="/FotoBook/sign_in" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Se connecter</a>
				<a href="/FotoBook/sign_up" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">S'inscrire</a>
			</div>  	
	  	</c:if>	  		 	  		  	   
	</div>
	
	<div class="w3-container">
		<div class="w3-container w3-border w3-round w3-margin w3-padding-large mesAlbums">
				<div class="w3-row">
					<div class="w3-col l6">
						<h4>Albums publiques</h4>
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
										<c:if test="${!empty sessionScope.user && sessionScope.user.userType == 'ADMIN'}">
											<div class="w3-row w3-right">
												<div class="w3-col s3" style="padding-right: 15px">
													<c:choose>
														<c:when test="${ album.getAccess() == 'prive' }">
															<a href="#"><i class="fa fa-lock w3-text-grey"></i></a>
														</c:when>
														<c:otherwise>
															<a href="#"><i class="fa fa-unlock w3-text-grey"></i></a>
														</c:otherwise>
													</c:choose>
												</div>
												<div class="w3-col s3" style="padding-right: 20px" data-id="${ album.getId() }">
													<a href="#" class="edit"><i class="fa fa-edit w3-text-orange"></i></a>
												</div>
												<div class="w3-col s3">
													<a href="/FotoBook/delete_album?id=${ album.getId() }" onclick="return confirm('Voulez-vous vraiment supprimer l\'album ?')"><i class="fa fa-trash w3-text-red"></i></a>
												</div>
											</div>
										</c:if>
									</div>									
								</div>
							</c:forEach>
						</c:when>

						<c:otherwise>
							<p>La liste des albums publiques est vide.</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>			
	</div>
</body>
</html>