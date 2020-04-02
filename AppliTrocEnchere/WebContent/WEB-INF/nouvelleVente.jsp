
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/nouvelleVente.css" rel="stylesheet">
<title>Nouvelle Vente</title>

</head>
<body>
<header >
		<div class="eni-encheres">
			<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
		</div>

	</header>
	
<div class="title">
	<h1>Nouvelle Vente</h1>
</div>

<div class="contenu">
	<form action="<%=request.getContextPath()%>/AjoutArticle" method="post">
		

			<div class="article">
				<label id="labelArticle" for="article">Article : </label>
				<input type="text" name="nomArticle" id="nomArticle"><br>
			</div>
			<br>
		<div class="desc">
			<label id="labelDescription" for="description">Description : </label>
			<textarea name="description" rows="3" cols="25" required> </textarea>
			<br>
		</div>
			<div class="categories">
				<label id="categorie" for="categorie">Catégories : </label>
				<select id="categorie" name="categorie">
					<option value="0">Toutes</option>
					<option value="1">Informatique</option>
					<option value="2">Ameublement</option>
					<option value="3">Vêtement</option>
					<option value="4">Sport et Loisirs</option>
				</select>
				<br>
				<br>
			</div>
			
			<div class="miseAPrix">
				<label id="labelMiseAPrix" for="miseAPrix">Mise à prix : </label>
				<input type="text" name="miseAPrix" id="miseAPrix"><br>
			</div>
				<br>
			<div class="debutEnchere">
				<label for="dateDebut">Début de l'enchère : </label> 
				<input type="date" id="dateDebut"name="dateDebut"> 
			</div>
				
			<div class="finEnchere">
				<label for="dateFin">Fin de l'enchère : </label> 
				<input type="date" id="dateFin"name="dateFin"> 
			</div>
				<br>			
			<div class="retrait">
				<fieldset>
					<legend>
						<label for="lieuDeRetrait">Retrait</label>
					</legend>
					<div>
					<label for="rue">Rue :</label>
						<input type="text" name="rue" value="${utilisateur.rue}" required><br>
					</div>
					
					<div>
						<label for="codePostal"> Code Postal :</label>
						<input type="text" name="codePostal" value="${utilisateur.codePostal}" required><br>
					</div>
					
					<div>
						<label for="ville">Ville :</label>
						<input type="text" name="ville" value="${utilisateur.ville}" required>
					</div>
				</fieldset>
			</div>
				<br>
			<button class="save" type="submit">Enregistrer</button>
			
	

	</form>
			
		<form action="<%=request.getContextPath()%>/Accueil" method="get">
			<input type="submit" value="Annuler">
		</form>
		
		<c:forEach items="${errorMessages}" var="error">
				<c:out value="${error}" />
				<br>
		</c:forEach>

</div>
</body>
</html>