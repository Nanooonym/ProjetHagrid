<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Détails de la vente</title>
</head>
<body>
	<h1>Détails vente</h1>
	<form id="formDetailVente" action="DetailVente" method="post">

		<!-- Nom de l'article sélectionné -->
		<div id="nomArticle">
			<label id=labelArticle for="article">${retrait.article.nomArticle}</label>
			<input type="hidden" name="noArticle"
				value="${retrait.article.noArticle}" readonly>
		</div>
		<!-- Description de l'article -->
		<div id="descriptionArticle">
			<p>Description : ${retrait.article.description}</p>
		</div>



		<!-- Catégorie de l'article -->
		<div id="categorieArticle">

			<p>Catégorie : ${retrait.article.categorie.libelle}</p>

		</div>


		<div>
			<p>Meilleure offre :</p>
			<label id="meilleureOffre">${retrait.article.prixVente} </label> <input
				type="hidden" name="montantEnchere"
				value="${retrait.article.prixVente}" readonly>
			<p>points par ${utilisateurMax.pseudo}</p>
		</div>


		<!-- Mise à prix de l'article -->
		<div id="miseAPrix">

			<p>Mise à prix : ${retrait.article.miseAPrix} points</p>
		</div>


		<!-- Date de fin d'enchère -->
		<div id="finEnchereArticle">
			<p>Fin de l'enchère : ${retrait.article.dateFinEncheres}</p>
		</div>

		<!-- Adresse de retrait -->
		<div id="retrait">
			<p>Retrait: ${retrait.rue} ${retrait.codePostal} ${retrait.ville}</p>
		</div>


		<!-- Nom du vendeur -->
		<div id="vendeurArticle">
			<p>Vendeur : ${retrait.article.utilisateur.pseudo}</p>
		</div>


		<!-- Proposition -->
		<div id="proposition">
			<label>Ma proposition : </label>
			<c:if
				test="${sessionScope.utilisateur.pseudo ne utilisateurMax.pseudo}">
				<input type="number" id="proposition" name="proposition"
					min="${retrait.article.prixVente+1}" max="100000000"
					value="${retrait.article.prixVente+1}">
			</c:if>
			<c:if
				test="${sessionScope.utilisateur.pseudo eq utilisateurMax.pseudo}">
				<c:out value="${retrait.article.prixVente}" />
			</c:if>
		</div>

		<c:if test="${retrait.article.dateFinEncheres < now}">
			<p>Téléphone : ${retrait.article.utilisateur.telephone}</p>
		</c:if>

		<div id="boutonEncherir">
			<button type="submit" id="encherir">Enchérir</button>

		</div>
	</form>
	<form action="./Accueil" method="get">
		<input type="submit" value="Annuler">
	</form>

	<c:forEach items="${errorMessages}" var="error">
		<c:out value="${error}" />
		<br>
	</c:forEach>





</body>
</html>