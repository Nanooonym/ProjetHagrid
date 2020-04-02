<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<h2>Vous avez remporté la vente</h2>
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