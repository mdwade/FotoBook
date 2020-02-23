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
<style type="text/css">
	
</style>
<title>FotoBook | Accueil</title>
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
						<h4><a href="#" class="w3-btn w3-green w3-small w3-round w3-right">Ajouter un album</a></h4>
					</div>
				</div>
				
				<div class="w3-row-padding">
					<div class="w3-col s2 w3-card w3-round-large w3-margin w3-padding-large">						
						<div class="w3-row-padding">
							<div class="w3-col s4">
								<i class="fa fa-folder w3-jumbo w3-text-yellow"></i>
							</div>
							<div class="w3-col s8" style="margin-top: 5%">
								<a href="#">Mes vacances 2017</a>
							</div>
						</div>					
					</div>
				</div>
			</div>
		</c:if>
	
	<%@ include file="footer.jsp" %>
</body>
</html>