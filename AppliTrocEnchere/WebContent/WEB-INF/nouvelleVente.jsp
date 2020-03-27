
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle Vente</title>

</head>
<body>

	<h1>Nouvelle Vente</h1>

	<form action="./AjoutArticle" method="post">

		<div>

			<div>

				<label id="labelArticle" for="article">Article : </label> <input
					type="text" name="nom_article" id="nom_article"><br>

			</div>
			<label id="labelDescription" for="description">Description :
			</label>
			<textarea name="description" rows="3" cols="40" required> </textarea>
			<br>
			<div>
				<label id="categorie" for="categorie">Catégories</label> <select
					id="categorie" name="categorie">
					<option value="Toutes">Toutes</option>
					<option value="1">Informatique</option>
					<option value="2">Ameublement</option>
					<option value="3">Vêtement</option>
					<option value="4">Sport et Loisirs</option>
				</select> <br>
			</div>




			<div>
				<fieldset>
					<legend>
						<label for="lieuDeRetrait">Retrait</label>
					</legend>
					<label for="rue">Rue :</label> <input type="text" name="rue"
						value="${utilisateur.getRue()}" required><br> <label
						for="codePostal"> Code Postal :</label> <input type="text"
						name="codePostal" value="${utilisateur.getCodePostal()}" required><br>
					<label for="ville">Ville :</label> <input type="text" name="ville"
						value="${utilisateur.getVille()}" required>
				</fieldset>
			</div>

			<div>
				<input value="submit" type="submit"> <a
					href="<%=request.getContextPath()%>/Accueil"><input
					type="button" value="Annuler"></a>
			</div>
		</div>
	</form>


</body>
</html>