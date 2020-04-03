
		<link href="css/remporteVente.css" rel="stylesheet">
		<title>R�sultat de la vente</title>
	</head>
<body>
	<div class="grid">
		<header>
			<div class="eni-encheres">
				<a href="<%=request.getContextPath()%>/Accueil">ENI-Ench�res</a>
			</div>

		</header>

	

<div class="leTitre">
	<h2>Vous avez remport� la vente</h2>
</div>

<div class="laDescription">
	<p>${ retrait.article.nomArticle }</p>
	<p>Description : ${ retrait.article.description }</p>
	<p>Meilleure offre : ${ retrait.article.prixVente }  </p>
	<p>Mise � prix : ${ retrait.article.miseAPrix } </p>
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