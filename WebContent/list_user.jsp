<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FotoBook | utilisateurs</title>
<link rel="stylesheet" type="text/css" href="static/css/mycss.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="static/img/music.png">
<script src="static/js/jquery.js"></script>
</head>
<body>
	<div class="w3-bar w3-black w3-padding-large">
	  	<a class="w3-bar-item" style="text-decoration: none"><img src="static/img/music1.png" width="20">otoBook</a>
		<!-- <a href="#" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white w3-hide-small">Albums publiques</a> -->  		  
	  	<c:choose>  		
	  		<c:when test="${!empty sessionScope.user}">
	  			<a href="/FotoBook/home" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white w3-hide-small">Mes albums</a>
	  			<a href="/FotoBook/public" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white w3-hide-small">Albums publics</a>
	  			<a href="/FotoBook/private" class="w3-bar-item w3-hover-none w3-text-grey w3-hover-text-white" style="text-decoration: none">Albums autorisés</a>
	  			<c:if test="${sessionScope.user.userType == 'ADMIN'}">
	  				<a href="/FotoBook/users" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white w3-hide-small">Gérer les utilisateurs</a>
	  			</c:if>
	  			<div class="w3-dropdown-hover w3-mobile w3-hide-small w3-right">
		  			<button class="w3-btn w3-hover-black w3-text-grey w3-hover-text-white">			    	
						<i class="fa fa-user-circle w3-large"></i> ${sessionScope.user.firstName} ${sessionScope.user.lastName}					
				    </button>
				    <div class="w3-dropdown-content w3-bar-block w3-dark-grey">
				      <a href="/FotoBook/sign_out" class="w3-bar-item w3-btn w3-black">Déconnexion</a>
				    </div>
			   </div>
	  		</c:when>
	  	</c:choose>		    
	</div>
	
	<div class="w3-container w3-center">
		<h3 class="w3-margin">Gestion des utilisateurs</h3>
		
		<c:choose>
			<c:when test="${ !empty listUtilisateur }">								
				<table class="w3-table w3-centered w3-hoverable w3-border w3-round-large">
					<thead>
						<tr>
							<th> ID </th>
							<th> Nom </th>
							<th> Prenom </th>
							<th> Age </th>
							<th> Adresse </th>
							<th> Email </th>				
							<th> Téléphone </th>
							<th> Type </th>
							<th> Date d'inscription </th>
							<th colspan="2" class="action"> Actions </th>
						</tr>
					</thead>
					<tbody>		
						<c:forEach var="u" items="${ listUtilisateur }">
							<tr>
								<td> ${ u.getId() } </td>
								<td> ${ u.getLastName() }</td>
								<td> ${ u.getFirstName() }</td>
								<td> ${ u.getAge() }  </td>
								<td> ${ u.getAddress() }  </td>
								<td> ${ u.getEmail() }</td>
								<td> ${ u.getPhoneNumber() }</td>
								<td> ${ u.getUserType() } </td>
								<td> ${ u.getRegisterDate() }  </td>
								<td class="w3-center" data-id="${ u.getId() }"><a href="#" class="edit w3-btn w3-orange w3-round-large w3-large"><i class="fa fa-edit"></i></a></td>
								<td class="w3-center"><a href="/FotoBook/delete_user?id=${ u.getId() }" onclick="return confirm('Êtes vous sûr de vouloir supprimer ?')" class="w3-btn w3-red w3-round-large w3-large"><i class="fa fa-trash"></i></a></td>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p class="emptyMsg"> La liste des utilisateurs est vide ! </p>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="w3-container w3-margin">
		<button class="w3-btn w3-orange w3-round" onclick="document.getElementById('id03').style.display='block'">Ajouter un utilisateur</button>
	</div>
	
	<div id="id03" class="w3-modal">
		<div
			class="w3-modal-content w3-animate-top w3-card-4 w3-round w3-padding-large"
			style="width: 35%">
			<header class="w3-container">
				<span onclick="document.getElementById('id03').style.display='none'"
					class="w3-button w3-display-topright w3-hover-red">&times;</span>
				<div class="w3-row">
					<div class="w3-col l1" style="margin-top: 10px">
						<i class="fa fa-user w3-xxlarge"></i>
					</div>
					<div class="w3-col l10">
						<h3>Ajouter utilisateur</h3>
						<p style="font-size: 12px; color: orange;">${ errors["emptyFieldError"] }</p>
					</div>
				</div>
			</header>
			<hr>
			<div class="w3-container">
				<form action="/FotoBook/add_user" method="post">
					<div class="w3-row-padding">
						<div class="w3-col l6">
							<input type="text" name="lastName" required class="w3-input w3-border w3-round" placeholder="Tapez le nom" value="${ lastName }"> <br><br> 
						</div>
						<div class="w3-col l6">
							<input type="text" name="firstName" required class="w3-input w3-border w3-round" placeholder="Tapez le prénom" value="${ firstName }"> <br><br> 
						</div>
						
						<div class="w3-col l6">
							<input type="number" name="age" required class="w3-input w3-border w3-round" placeholder="Tapez l'age" value="${ age }"> <br><br>
							<p style="font-size: 12px; color: orange;">${ errors["ageError"] }</p> 
						</div>
						<div class="w3-col l6">
							<input type="email"	name="email" required class="w3-input w3-border w3-round" placeholder="Tapez l'email" value="${ email }"> <br><br> 
							<p style="font-size: 12px; color: orange;">${ errors["emailFormatError"] }</p>
							<p style="font-size: 12px; color: orange;">${ errors["emailExistError"] }</p>
						</div>
						
						<div class="w3-col l6">
							<input type="text" name="phoneNumber" required class="w3-input w3-border w3-round" placeholder="Tapez le téléphone" value="${ phoneNumber }"> <br><br> 
							<p style="font-size: 12px; color: orange;">${ errors["phoneNumberFormatError"] }</p>
							<p style="font-size: 12px; color: orange;">${ errors["phoneNumberExistError"] }</p>
						</div>
						<div class="w3-col l6">
							<input type="text" name="address" required class="w3-input w3-border w3-round" placeholder="Tapez l'adresse" value="${ address }"> <br><br> 
						</div>
						
						<div class="w3-col l6">
							<input type="password" name="password" required class="w3-input w3-border w3-round" placeholder="Tapez le mot de passe"> <br><br> 
							<p style="font-size: 12px; color: orange;">${ errors["passwordFormatError"] }</p>
						</div>
						
						<select class="w3-select w3-border" name="userType" required id="uUserType">							
							<option disabled selected>Sélectionnez le type</option>
							<option value="ADMIN">Admin</option>
							<option value="SAMPLE">Simple utilisateur</option>
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
	
	<div id="id04" class="w3-modal">
		<div
			class="w3-modal-content w3-animate-top w3-card-4 w3-round w3-padding-large"
			style="width: 35%">
			<header class="w3-container">
				<span onclick="document.getElementById('id04').style.display='none'"
					class="w3-button w3-display-topright w3-hover-red">&times;</span>
				<div class="w3-row">
					<div class="w3-col l1" style="margin-top: 10px">
						<i class="fa fa-user w3-xxlarge"></i>
					</div>
					<div class="w3-col l10">
						<h3>Modifier l'utilisateur</h3>
						<p style="font-size: 12px; color: orange;">${ errors["emptyFieldError"] }</p>
					</div>
				</div>
			</header>
			<hr>
			<div class="w3-container">
				<form action="/FotoBook/update_user" method="post">
					<div class="w3-row-padding">
						<input type="hidden" name="uIdUser" id="uIdUser"> 
						<div class="w3-col l6">
							<input type="text" id="uLastName" name="lastName" required class="w3-input w3-border w3-round"> <br><br> 
						</div>
						<div class="w3-col l6">
							<input type="text" id="uFirstName" name="firstName" required class="w3-input w3-border w3-round"> <br><br> 
						</div>
						
						<div class="w3-col l6">
							<input type="number" id="uAge" name="age" required class="w3-input w3-border w3-round"> <br><br> 
						</div>
						<div class="w3-col l6">
							<input type="email" id="uEmail"	name="email" required class="w3-input w3-border w3-round"> <br><br> 
						</div>
						
						<div class="w3-col l6">
							<input type="text" id="uPhone" name="phoneNumber" required class="w3-input w3-border w3-round"> <br><br> 
						</div>
						<div class="w3-col l6">
							<input type="text" id="uAddress" name="address" required class="w3-input w3-border w3-round"> <br><br> 
						</div>
						
						
						<select class="w3-select w3-border" name="userType" required id="uUserType">							
							<option value="ADMIN">Admin</option>
							<option value="SAMPLE">Simple utilisateur</option>
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
</body>
<script>
	$(document).on("click", ".edit", function (e){
		let id = $(e.target).closest("td").data("id");
		
		$.ajax({
            url: "/FotoBook/get_user?id="+id,
            method: "GET",
            dataType: "json",
            success: function (data) {
                $('#uIdUser').val(data.id);
                $('#uLastName').val(data.lastName);
                $('#uFirstName').val(data.firstName);
                $('#uAge').val(data.age);
                $('#uAddress').val(data.address);
                $('#uEmail').val(data.email);
                $('#uPhone').val(data.phoneNumber);
                $('#uUserType').val(data.userType);
                
                $('#id04').show();              
            }
        });
	});
</script>
</html>