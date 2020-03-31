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
			<label id=labelArticle for="article">${articleVendu.nomArticle}</label>
			<input type="hidden" name="noArticle"
				value="${articleVendu.noArticle}" readonly>
		</div>
		<!-- Description de l'article -->
		<div id="descriptionArticle">
			<label>Description : </label>
			<textarea id="textarea" rows="3" cols="40" readonly>${articleVendu.description}</textarea>
		</div>

		<!-- Catégorie de l'article -->
		<div id="categorieArticle">
			<label>Catégorie : </label> <input type="text" name="categorie"
				value="${articleEnchere.categorie.libelle}" readonly>

		</div>


		<!-- Meilleure enchère + pseudo -->
		<div id="meilleurOffre">
			<c:if test="${articleVendu.prixVente > articleVendu.miseAPrix}">
				<!-- Montant de l'enchère : -->
				<p>Meilleure offre: ${articleVendu.prixVente} points par
					${utilisateur.pseudo}</p>
				<input type="hidden" name="meilleureOffre"
					value="${articleVendu.prixVente}">
				<input type="hidden" name="noUtilisateur"
					value="${utilisateur.noUtilisateur}">
			</c:if>

		</div>


		<!-- Mise à prix de l'article -->
		<div id="miseAPrix">
			<label>Mise à prix : </label>
			<p>${articleVendu.miseAPrix}points</p>
		</div>

		<!-- Date de fin d'enchère -->
		<div id="finEnchereArticle">
			<label>Fin de l'enchère :</label> <input name="finEnchere"
				id="finEnchere" value="${articleVendu.dateFinEncheres}" readonly>
		</div>

		<!-- Adresse de retrait -->
		<div id="retrait">
			<p>Retrait: ${articleEnchere.retrait.rue}
				${articleEnchere.retrait.codePostal} ${articleEnchere.retrait.ville}</p>
		</div>


		<!-- Nom du vendeur -->
		<div id="vendeurArticle">
			<label>Vendeur : </label> <input id="vendeur" name="vendeur"
				value="${articleVendu.utilisateur.pseudo}" readonly>
		</div>


		<!-- Proposition -->
		<div id="proposition">
			<label>Ma proposition : </label> <input type="number"
				id="proposition" name="proposition"
				min="${articleVendu.prixVente+1}" max="100000000">
		</div>

		<div id="boutonEncherir">
			<input type="submit" id="encherir" value="Enchérir"> <a
				href="<%=request.getContextPath()%>/Accueil"><input
				type="button" id="annuler" value="Annuler"></a>
		</div>



	</form>


</body>
</html>