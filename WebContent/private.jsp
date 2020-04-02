<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Fotobook</title> 
<link rel="stylesheet" type="text/css" href="static/css/home.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/FotoBook/static/css/mycss.css">
<link rel="icon" href="static/img/music.png">
<link href="static/css/lightbox.css" rel="stylesheet"/>
<script src="static/js/jquery.js"></script>
<script src="static/js/lightbox-plus-jquery.js"></script>
</head>
<body>
	<div class="w3-top">
		<div class="w3-bar w3-black w3-padding-large">
		  	<a class="w3-bar-item" style="text-decoration: none"><img src="static/img/music1.png" width="20">otoBook</a>
		  	<c:if test="${!empty sessionScope.user}">	  	
		  		<a href="/FotoBook/home" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Mes albums</a>
		  		<a href="/FotoBook/public" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Albums publiques</a>
		  		<a href="/FotoBook/private" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Albums autorisés</a>
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
						<p>La liste des albums privés est vide.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>				
	</div>
	
	<div class="w3-container w3-margin w3-padding-large" id="albumBloc" style="margin-top: 300px !important"></div>
	
	<div id="id02" class="w3-modal">
		<div
			class="w3-modal-content w3-animate-top w3-card-4 w3-round w3-padding-large"
			style="width: 25%">
			<header class="w3-container">
				<span onclick="document.getElementById('id02').style.display='none'"
					class="w3-button w3-display-topright w3-hover-red">&times;</span>
				<div class="w3-row">
					<div class="w3-col l2" style="margin-top: 10px">
						<i class="fa fa-folder w3-xxlarge"></i>
					</div>
					<div class="w3-col l10">
						<h3>Modifier l'album</h3>
						<p style="font-size: 12px; color: orange;">${ errors["emptyFieldError"] }</p>
					</div>
				</div>
			</header>
			<hr>
			<div class="w3-container">
				<form action="/FotoBook/update_album" method="post">
					<div class="w3-row-padding">
						<input type="hidden" name="idUser"
							value="${ sessionScope.user.id }"> 
						<input type="hidden" name="idAlbum"
							id="idAlbum"> 
						<input type="text"
							   id="aName" 
							   name="albumName"
							   required class="w3-input w3-border"
							   > <br><br> 
						<select class="w3-select w3-border" name="theme" required id="aTheme">							
							<option value="Vacances">Vacances</option>
							<option value="Famille">Famille</option>
							<option value="Ami">Ami</option>
							<option value="travail">Travail</option>
						</select> <br></br> 
						<label><b>Accès : </b></label> <br> 
						<input class="w3-radio" type="radio" name="access" value="public" required  id="apublic"> 
						<label>publique</label> 
						<input class="w3-radio"
							type="radio" name="access" value="prive" required id="aprive"> 
						<label>privé</label><br><br>
						
						<select class="w3-select w3-border" id="id02users" name="users" multiple="multiple">
											
						</select> <br></br>												
						
					</div>
					<div class="w3-right w3-padding">
						<input type="submit" value="Valider"
							class="w3-btn w3-round w3-orange">
					</div>					
				</form>
			</div>
		</div>
	</div>
	
	<div id="detailImage" class="w3-modal">
		<div
			class="w3-modal-content w3-animate-top w3-card-4 w3-round w3-padding-large"
			style="width: 25%">
			<header class="w3-container">
				<span onclick="document.getElementById('detailImage').style.display='none'"
					class="w3-button w3-display-topright w3-hover-red">&times;</span>
				<div class="w3-row">
					<div class="w3-col l2" style="margin-top: 10px">
						<i class="fa fa-image w3-xxlarge"></i>
					</div>
					<div class="w3-col l10">
						<h3>Détail de l'image</h3>						
					</div>
				</div>
			</header>
			<hr>
			<div class="w3-container">
				<div class="w3-row-padding">
					<div class="w3-col l6">Titre: </div>
					<div class="w3-col l6" id="imgTitle"></div>
				</div>
				<div class="w3-row-padding albuminfos">
					<div class="w3-col l6">Description: </div>
					<div class="w3-col l6" id="imgDescription"></div>
				</div>
				<div class="w3-row-padding albuminfos">
					<div class="w3-col l6">Mots clés: </div>
					<div class="w3-col l6" id="imgKeyWords"></div>
				</div>
				<div class="w3-row-padding albuminfos">
					<div class="w3-col l6">Hauteur: </div>
					<div class="w3-col l6" id="imgHeight"></div>
				</div>
				<div class="w3-row-padding albuminfos">
					<div class="w3-col l6">Largeur: </div>
					<div class="w3-col l6" id="imgWidth"></div>
				</div>
				<div class="w3-row-padding albuminfos">
					<div class="w3-col l6">Date de création: </div>
					<div class="w3-col l6" id="imgCreationDate"></div>
				</div>
				<div class="w3-row-padding albuminfos">
					<div class="w3-col l6">Date de mise à jour: </div>
					<div class="w3-col l6" id="imgUpdatedDate"></div>
				</div>
				<hr>
				<div class="w3-row-padding albuminfos">
					<div class="w3-col l6"><b>Album: </b></div>
					<div class="w3-col l6">
						<span id="openAlbumInfos"></span>						
					</div>
				</div>
				<div class="w3-hide" id="albumInfos">
					<div class="w3-row-padding albuminfos">
						<div class="w3-col l6">Date de création: </div>
						<div class="w3-col l6" id="albumcreationDate"></div>
					</div>
					<div class="w3-row-padding albuminfos">
						<div class="w3-col l6">Accès: </div>
						<div class="w3-col l6" id="albumacces"></div>
					</div>
					<div class="w3-row-padding albuminfos">
						<div class="w3-col l6">Thème: </div>
						<div class="w3-col l6" id="albumTheme"></div>
					</div>
				</div>								
			</div>
		</div>
	</div>
	
	<div id="editImage" class="w3-modal">
		<div
			class="w3-modal-content w3-animate-top w3-card-4 w3-round w3-padding-large"
			style="width: 25%">
			<header class="w3-container">
				<span onclick="document.getElementById('editImage').style.display='none'"
					class="w3-button w3-display-topright w3-hover-red">&times;</span>
				<div class="w3-row">
					<div class="w3-col l2" style="margin-top: 10px">
						<i class="fa fa-photo w3-xxlarge"></i>
					</div>
					<div class="w3-col l10">
						<h3>Modifier l'image</h3>						
					</div>
				</div>
			</header>
			<hr>
			<div class="w3-container">
				<form action="/FotoBook/edit_image" method="post" enctype="multipart/form-data" class="editForm">
					<div class="w3-row-padding">
						<input type="hidden" name="imgId" id="editImgId">
						<input type="text" name="imgTitle" id="editImgTitle" style="width:100% !important; border-radius:5px 5px 5px 5px" placeholder="Tapez le titre...">
						<br><br>
						<textarea class="w3-input w3-round w3-border" name="imgDescription" id="editImgDescription" placeholder="Tapez la description..."></textarea>
						<br>
						<textarea class="w3-input w3-round w3-border" name="imgKeyWords" id="editImgKeyWords" placeholder="Tapez les mots clés..."></textarea>
						<br><br>
						
					</div>
					<div class="w3-right w3-padding">
						<input type="submit" value="Valider"
							class="w3-btn w3-round w3-orange">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
</body>
<script>
	$(document).on("click", ".albumName", function (e){			
		let id = $(e.target).closest("a").data("id");
		$.get("/FotoBook/get_album1?id="+id).done(function(data) {		
			$('#albumBloc').html(data);			
		});
	});
	
//-----------------------------------------------------------------------------------------------------------------------------------
	
	$(document).on("click", ".edit", function (e){
		let id = $(e.target).closest("div").data("id");
		
		$.get("/FotoBook/get_album?id="+id).done(function(data) {
			$('#idAlbum').val(data.id);
	        $('#aName').val(data.name);
	        $('#aTheme').val(data.theme);
	        $('#a'+data.access).prop('checked', 'checked');  
	        $("#id02users").hide();
	        if(data.access == 'prive'){
	        	$("#id02users").show();
	        }	        
	        
		});
		
		$.get("/FotoBook/get_users").done(function(data) {
			$('#id02users').empty();
        	$('#id02users').append('<option disabled>Les utilisateurs à autoriser</option>'); 
        	
        	
        	$.get("/FotoBook/get_authorised_users?id="+id).done(function(data1) {								    			    			
    			for(let i in data){ 
    				$('#id02users').append('<option value='+data[i].id+'>'+data[i].firstName+' '+data[i].lastName+'</option>');
                	for(let j in data1){
                		if(data[i].id == data1[j][0])
                    	{                    		
                			$('#id02users').find('option[value='+data[i].id+']').prop('selected', 'selected');
                    	}                    	
                	}    				               	                              	                	         
                }
    			$('#id02users').find('option[value='+userId+']').remove();
    		});                    
	                        	        
		});				
		
		$('#id02').show();
		
	});
		
//-----------------------------------------------------------------------------------------------------------------------------------

	$(document).on("click", ".imgAlbum", function (e){			
		let id = $(e.target).closest("img").data("id");
		$.get("/FotoBook/get_image?id="+id).done(function(data) {		
			console.log(data.imageFile);	
		});		
	});
	
//-----------------------------------------------------------------------------------------------------------------------------------

	$(document).on("click", ".detailImage", function (e){			
		let id = $(e.target).closest("a").data("id").split("-");
				
		$.get("/FotoBook/get_image?id="+id[0]).done(function(data) {
			$.get("/FotoBook/get_album?id="+id[1]).done(function(data1){
				$('#imgTitle').text(data.title);
				$('#imgDescription').text(data.description);
				$('#imgKeyWords').text(data.keyWords);
				$('#imgHeight').text(data.height+" px");
				$('#imgWidth').text(data.width+" px");
				$('#imgCreationDate').text(data.creationDate);
				$('#imgUpdatedDate').text(data.updatedDate);
				$('#openAlbumInfos').text(data1.name);
				$('#albumcreationDate').text(data1.creationDate);
				$('#albumacces').text(data1.access);
				$('#albumTheme').text(data1.theme);
				$('#detailImage').show();				
			});		
		});		
	});

//-----------------------------------------------------------------------------------------------------------------------------------

	$(document).on("click", ".editImage", function (e){			
		let id = $(e.target).closest("a").data("id");
				
		$.get("/FotoBook/get_image?id="+id).done(function(data) {
				$('#editImgId').val(data.id);
				$('#editImgTitle').val(data.title);
				$('#editImgDescription').val(data.description);
				$('#editImgKeyWords').val(data.keyWords);								
				$('#editImage').show();
				console.log(data);
				
		});		
	});
	
//-----------------------------------------------------------------------------------------------------------------------------------

	$(document).on('click', '#openAlbumInfos', function(){		
		var x = document.getElementById("albumInfos");
		if (x.className.indexOf("w3-show") == -1) {
		   x.className += " w3-show";
		} else { 
		    x.className = x.className.replace(" w3-show", "");
		}
	});
	
//-----------------------------------------------------------------------------------------------------------------------------------



</script>
</html>