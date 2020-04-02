
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle Vente</title>

</head>
<body>

	<h1>Nouvelle Vente</h1>

	<form action="<%=request.getContextPath()%>/UpdateArticle" method="post">
		<div>

			<div>
				<label id="labelArticle" for="article">Article : </label>
				<input type="text" name="nomArticle" id="nomArticle" value="${retrait.article.nomArticle}"><br>
			</div>
			
			<label id="labelDescription" for="description">Description :</label>
			<textarea name="description" rows="3" cols="40"> ${retrait.article.description}</textarea>
			<br>
			
			<div>
				<label id="categorie" for="categorie">Catégories</label>
				<select id="categorie" name="categorie">
					<option value="0">Toutes</option>
					<option value="1">Informatique</option>
					<option value="2">Ameublement</option>
					<option value="3">Vêtement</option>
					<option value="4">Sport et Loisirs</option>
				</select>
				<br>
			</div>
			
			<div>
				<label id="labelMiseAPrix" for="miseAPrix">Mise à prix : </label>
				<input type="text" name="miseAPrix" id="miseAPrix" value="${retrait.article.miseAPrix}"><br>
			</div>

			<div>
				<label for="dateDebut">Début de l'enchère : </label> 
				<input type="date" id="dateDebut"name="dateDebut" value="${retrait.article.dateDebutEncheres}"> 
			</div>
			
			<div>
				<label for="dateFin">Fin de l'enchère : </label> 
				<input type="date" id="dateFin"name="dateFin" value="${retrait.article.dateFinEncheres}"> 
			</div>
							
			<div>
				<fieldset>
					<legend>
						<label for="lieuDeRetrait">Retrait</label>
					</legend>
					<div>
					<label for="rue">Rue :</label>
						<input type="text" name="rue" value="${retrait.rue}" required><br>
					</div>
					
					<div>
						<label for="codePostal"> Code Postal :</label>
						<input type="text" name="codePostal" value="${retrait.codePostal}" required><br>
					</div>
					
					<div>
						<label for="ville">Ville :</label>
						<input type="text" name="ville" value="${retrait.ville}" required>
					</div>
				</fieldset>
			</div>

			<button type="submit">Enregistrer</button>
			
		</div>

	</form>
			
		<form action="<%=request.getContextPath()%>/Accueil" method="get">
			<input type="submit" value="Annuler">
		</form>
		
		<form action="<%=request.getContextPath()%>/SupprimerVente?" method="post">
			<input type="submit" value="supprimerVente">
			<input type="hidden" name="${retrait.article.noArticle}" value="${retrait.article.noArticle}">
		</form>
		
		<c:forEach items="${errorMessages}" var="error">
				<c:out value="${error}" />
				<br>
		</c:forEach>


</body>
</html>