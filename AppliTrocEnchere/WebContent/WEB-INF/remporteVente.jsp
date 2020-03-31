<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Résultat de la vente</title>
</head>
<body>

<header style=flex>
<div class="eni-encheres">
	<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
</div>

</header>

<div style="text-align:center">

<h3>Vous avez remporté la vente</h3>
<p>${ retrait.article.nomArticle }</p>
<p>Description : ${ retrait.article.description }</p>
<p>Meilleure offre : ${ retrait.article.prixVente }  </p>
<p>Mise à prix : ${ retrait.article.miseAPrix } </p>
<br/>
<p>Retrait : ${ retrait.rue } ${ retrait.codePostal } ${ retrait.ville }</p>
<br/>
<p>Vendeur : ${ retrait.article.utilisateur.pseudo }</p>
<p>Tel : ${ retrait.article.utilisateur.telephone }</p>
<br>
<form action="<%=request.getContextPath()%>/Accueil" method="get">
			<button type="submit" value="accueil">Retour</button>
		</form>

</div>

</body>
</html>