
		<link href="css/remporteVente.css" rel="stylesheet">
		<title>Résultat de la vente</title>
	</head>
<body>
	<div class="grid">
		<header>
			<div class="eni-encheres">
				<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
			</div>

		</header>

	

<div class="leTitre">
		<c:if
	            test="${sessionScope.utilisateur.pseudo eq utilisateurMax.pseudo and retrait.article.dateFinEncheres > dateDuJour}">
	            <h2>Vous a remporté la vente</h2>
		</c:if>
		<c:if
	            test="${sessionScope.utilisateur.pseudo ne utilisateurMax.pseudo and retrait.article.dateFinEncheres > dateDuJour}">
	            <h2>${utilisateurMax.pseudo} a remporté la vente</h2>
		</c:if>
</div>

<div class="laDescription">
	<p>${ retrait.article.nomArticle }</p>
	<p>Description : ${ retrait.article.description }</p>
	<p>Meilleure offre : ${ retrait.article.prixVente }  </p>
	<p>Mise à prix : ${ retrait.article.miseAPrix } </p>
	<br/>
	<p>Retrait : ${ retrait.rue }<p> 
	<p> ${ retrait.codePostal } ${ retrait.ville } </p>
	<br/>
	<p>Vendeur : ${ retrait.article.utilisateur.pseudo }</p>
	<p>Tel : ${ retrait.article.utilisateur.telephone }</p>
</div>

<div class="leBouton">
	<form action="<%=request.getContextPath()%>/Accueil" method="get">
				<button type="submit" value="accueil">Retour</button>
	</form>
</div>
		</div>
	
</body>
</html>