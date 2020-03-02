<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/FotoBook/static/css/mycss.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/FotoBook/static/css/sign_up.css">
<link rel="icon" href="static/img/music.png">
<title>Fotobook | Inscription</title>
</head>
<body>
	
	<div class="w3-row-padding">
		<div class="w3-col l5 register-left">
			<img src="static/img/down-arrow.png">
			<h1>Rejoignez nous !</h1>	
			<p>Enregistrez et partagez vos albums photo avec le monde.</p>
			<a href="/FotoBook/sign_in" class="w3-btn w3-round-xxlarge w3-white w3-hover-black">Connectez-vous</a>
		</div>
		
		<div class="w3-col l7 register-right">
			<h2>Inscription</h2>
			<p class="w3-center" style="color: #555;">C'est simple et rapide.</p>			
			<div class="register-form">
				<form action="/FotoBook/sign_up" method="post">
					<div class="w3-row-padding w3-margin">
						<div class="w3-half w3-row">
							<div class="w3-col l1">
								<i class="fa fa-user w3-large"></i>
							</div>
							<div class="w3-col l11">						
								<input class="w3-input w3-border w3-round" type="text"
									placeholder="Nom..." name="lastName" value="${ lastName }"
									required>
							</div>
						</div>
						<div class="w3-half w3-row">
							<div class="w3-col l1">
								<i class="fa fa-user w3-large"></i>
							</div>
							<div class="w3-col l11">
								<input class="w3-input w3-border w3-round" type="text"
									placeholder="Prénom..." name="firstName" value="${ firstName }"
									required>
							</div>
						</div>
					</div>

					<div class="w3-row-padding w3-margin">
						<div class="w3-half w3-row">
							<div class="w3-col l1">
								<i class="fa fa-user w3-large"></i>
							</div>
							<div class="w3-col l11">
								<input class="w3-input w3-border w3-round" type="number"
									placeholder="Age..." name="age" min="10" max="75"
									value="${ age }" required>
								<p class="errorMsg">${ errors["ageError"] }</p>
							</div>
						</div>
						<div class="w3-half w3-row">
							<div class="w3-col l1">
								<i class="fa fa-map-marker w3-large"></i>
							</div>
							<div class="w3-col l11">
								<textarea class="w3-input w3-border w3-round"
									placeholder="Adresse..." name="address" required>${ address }</textarea>
							</div>
						</div>
					</div>

					<div class="w3-row-padding w3-margin">
						<div class="w3-half w3-row">
							<div class="w3-col l1">
								<i class="fa fa-envelope"></i>
							</div>
							<div class="w3-col l11">
								<input class="w3-input w3-border w3-round" type="email"
									placeholder="Email..." name="email" value="${ email }" required>
								<p class="errorMsg">${ errors["emailFormatError"] }</p>
								<p class="errorMsg">${ errors["emailExistError"] }</p>
							</div>
							

						</div>

						<div class="w3-half w3-row">
							<div class="w3-col l1">
								<i class="fa fa-phone w3-large"></i>
							</div>
							<div class="w3-col l11">
								<input class="w3-input w3-border w3-round" type="text"
									placeholder="Téléphone..." name="phoneNumber"
									value="${ phoneNumber }" required>
								<p class="errorMsg">${ errors["phoneNumberFormatError"] }</p>
								<p class="errorMsg">${ errors["phoneNumberExistError"] }</p>
							</div>
							
						</div>
					</div>

					<div class="w3-row-padding w3-margin">
						<div class="w3-half w3-row">
							<div class="w3-col l1">
								<i class="fa fa-lock w3-large"></i>
							</div>
							<div class="w3-col l11">
								<input class="w3-input w3-border w3-round" type="password"
									placeholder="Mot de passe..." name="password" required>
								<p class="errorMsg">${ errors["passwordFormatError"] }</p>
								<p class="errorMsg">${ errors["passwordError"] }</p>
							</div>
						</div>
						<div class="w3-half w3-row">
							<div class="w3-col l1">
								<i class="fa fa-lock w3-large"></i>
							</div>
							<div class="w3-col l11">
								<input class="w3-input w3-border w3-round" type="password"
									placeholder="Confirmer mot de passe..." name="passwordConfirm"
									required>
							</div>
						</div>
					</div>

					<div class="w3-container w3-margin">
						<div class="w3-row">
							<div class="w3-col l6">
								<p class="errorMsg">${ errors["emptyFieldError"] }</p>
							</div>
							
							<div class="w3-col l6">
								<input type="submit" value="Valider"
									   class="w3-btn w3-round-xxlarge w3-right registerBtn">
							</div>
						</div>					
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>