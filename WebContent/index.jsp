<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FotoBook</title>
<link rel="stylesheet" type="text/css" href="static/css/bgStyle.css">
<link rel="stylesheet" type="text/css" href="static/css/mycss.css">
</head>
<body>
	<video id="videoBG" src="static/img/video.mp4" autoplay muted loop></video>
	<div class="w3-row w3-top w3-container w3-margin w3-padding">
		<div class="w3-col l6 head">
			<h3>
				<img src="static/img/music1.png" width="40">OTOBOOK
			</h3>
		</div>
	</div>
	<div class="banner-text">
		<h1>Bienvenue sur FotoBook</h1>
		<div class="w3-card w3-white w3-round-large w3-opacity w3-hover-opacity-off w3-center">
			<div class="w3-padding">
				<h3>Connectez-vous</h3>
				<span class="erreur"><i>${errors['signInError']}</i></span>
				<form action="/FotoBook/sign_in" method="post" class="w3-form">
					<div class="w3-margin w3-padding">
						<input type="email" 
							   name="email" 
							   value="" 
							   placeholder="Adresse email..."
							   class="w3-input w3-round-large w3-border w3-padding" 
							   value= "${email}" required>
					</div>
					
					<div class="w3-margin w3-padding">
						<input type="password" 
							   name="password" 
							   value="" 
							   placeholder="Mot de passe..."
							   class="w3-input w3-round-large w3-border w3-padding" required>
					</div>
					
					<div class="w3-margin w3-padding">
						<input type="submit" 							 
							   value="Connexion" 							   
							   class="w3-btn w3-round-large w3-green">
					</div>
				</form>
			</div>
		</div>
		<div class="w3-margin w3-text-white">
			<a href="#" class="">Inscription</a> | <a href="#" class="">Visiter le site</a>
		</div>
	</div>
	<div class="w3-footer">
		
	</div>
</body>
</html>