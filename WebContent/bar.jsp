<div class="w3-bar w3-black w3-padding-large">
  	<a href="#" class="w3-bar-item" style="text-decoration: none"><img src="static/img/music1.png" width="20">OTOBOOK</a>
  	<a href="#" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white w3-hide-small">Link 1</a>
  	<a href="#" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white w3-hide-small">Link 2</a>
		  
  	<c:choose>
  		<c:when test="${!empty sessionScope.user}">
	  		<div class="w3-dropdown-hover w3-mobile w3-hide-small w3-right">
	  			<button class="w3-btn w3-hover-black w3-text-grey w3-hover-text-white">			    	
					<i class="fa fa-user"></i> ${sessionScope.user.firstName} ${sessionScope.user.lastName}					
			    </button>
			    <div class="w3-dropdown-content w3-bar-block w3-dark-grey">
			      <a href="/FotoBook/sign_out" class="w3-bar-item w3-btn w3-black">Déconnexion</a>
			    </div>
		   </div>
  		</c:when>
  		<c:otherwise>
  			<div class="w3-right">
	  			<a href="/FotoBook/sign_in" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white w3-hide-small">Connexion</a>
	  			<a href="#" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white w3-hide-small">Inscription</a>
  			</div>
  		</c:otherwise>
  	</c:choose>
		      
	<a href="javascript:void(0)" class="w3-bar-item w3-button w3-hover-white w3-round-large w3-right w3-hide-large w3-hide-medium" onclick="myFunction()">&#9776;</a>
</div> 
	
<div id="smallBar" class="w3-bar-block w3-black w3-hide w3-hide-large w3-hide-medium w3-padding-large">
	<a href="#" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white">Link 1</a>
	<a href="#" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white">Link 2</a>
	<c:choose>
  		<c:when test="${!empty sessionScope.user}">
	  		<div class="w3-dropdown-hover w3-mobile w3-hide-small w3-right">
	  			<button class="w3-btn w3-hover-black w3-text-grey w3-hover-text-white">			    	
					<i class="fa fa-user"></i> ${sessionScope.user.firstName} ${sessionScope.user.lastName}					
			    </button>
			    <div class="w3-dropdown-content w3-bar-block w3-dark-grey">
			      <a href="/FotoBook/sign_out" class="w3-bar-item w3-btn w3-black">Déconnexion</a>
			    </div>
		   	</div>
  		</c:when>
  		<c:otherwise>
  			<div class="">
	  			<a href="/FotoBook/sign_in" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white">Connexion</a>
	  			<a href="#" class="w3-bar-item w3-button w3-hover-none w3-text-grey w3-hover-text-white">Inscription</a>
  			</div>
  		</c:otherwise>
  	</c:choose>
</div>
