<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<video id="videoBG" src="static/img/video.mp4" autoplay muted loop></video>
	<div class="w3-row w3-top w3-container w3-margin w3-padding">
		<div class="w3-col l6 head">
			<h3>
				<img src="static/img/music1.png" width="30">otobook
			</h3>
		</div>
	</div>
	<div class="banner-text">
		<h1>Bienvenue sur Fotobook</h1>
		<div class="w3-card w3-round-large w3-center" style="background: #f8f8f8">
			<div class="w3-padding">
				<h3>Connectez-vous</h3>
				<span class="erreur"><i>${errors['signInError']}</i></span>
				<form action="/FotoBook/sign_in" method="post" class="w3-form">
					<div class="w3-margin w3-padding w3-row">
						<div class="w3-col l2" style="padding-top: 10px">
							<i class="fa fa-envelope w3-large"></i>
						</div>
						<div class="w3-col l10">
							<input type="email" 
							   name="email"  
							   placeholder="Adresse email..."
							   class="w3-input w3-round-large w3-border w3-padding" 
							   value= "${email}" required>
						</div>
					</div>
					
					<div class="w3-margin w3-padding w3-row">
						<div class="w3-col l2" style="padding-top: 10px">
							<i class="fa fa-lock w3-large"></i>
						</div>
						<div class="w3-col l10">
							<input type="password" 
							   name="password" 
							   value="" 
							   placeholder="Mot de passe..."
							   class="w3-input w3-round-large w3-border w3-padding" required>
						</div>						
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
			<a href="/FotoBook/sign_up" class="">Inscription</a> |  <a href="/FotoBook/public" class="">Visiter le site</a>
		</div>
	</div>
	
	<div class="w3-footer">
		
	</div>
</body>
</html>