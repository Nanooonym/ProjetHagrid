<link href="css/detailVente.css" rel="stylesheet">
<title>D�tails de la vente</title>
</head>
<body>
	<div class="grid">

		<div class="leTitre">
			<h2>D�tails vente</h2>
		</div>

		<div class="laDescription">

			<form id="formDetailVente" action="DetailVente" method="post">

				<!-- Nom de l'article s�lectionn� -->
				<div id="nomArticle">
					<label id=labelArticle for="article">${retrait.article.nomArticle}</label>
					<input type="hidden" name="noArticle"
						value="${retrait.article.noArticle}" readonly>
				</div>
				<!-- Description de l'article -->
				<div id="descriptionArticle">
					<p>Description : ${retrait.article.description}</p>
				</div>



				<!-- Cat�gorie de l'article -->
				<div id="categorieArticle">

					<p>Cat�gorie : ${retrait.article.categorie.libelle}</p>

				</div>


				<div>
					<p>Meilleure offre :</p>
					<label id="meilleureOffre">${retrait.article.prixVente} </label> <input
						type="hidden" name="montantEnchere"
						value="${retrait.article.prixVente}" readonly>
					<p>points par ${utilisateurMax.pseudo}</p>
				</div>


				<!-- Mise � prix de l'article -->
				<div id="miseAPrix">

					<p>Mise a prix : ${retrait.article.miseAPrix} points</p>
				</div>


				<!-- Date de fin d'ench�re -->
				<div id="finEnchereArticle">
					<p>Fin de l'enchere : ${retrait.article.dateFinEncheres}</p>
				</div>

				<!-- Adresse de retrait -->
				<div id="retrait">
					<p>Retrait: ${retrait.rue} ${retrait.codePostal}
						${retrait.ville}</p>
				</div>


				<!-- Nom du vendeur -->
				<div id="vendeurArticle">
					<p>Vendeur : ${retrait.article.utilisateur.pseudo}</p>
				</div>


				<!-- Proposition -->
				<div id="proposition">
					<label for="proposition">Ma proposition : </label>
					<c:if
						test="${sessionScope.utilisateur.pseudo != utilisateurMax.pseudo}">
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
					<p>Telephone : ${retrait.article.utilisateur.telephone}</p>
				</c:if>


		<div class="lesBoutons">
			<div id="boutonEncherir">
				<button type="submit" id="encherir">Ench�rir</button>
			</div>
		</div>

		</form>
		</div>
		<form action="./Accueil" method="get">
			<input type="submit" value="Annuler">
		</form>
			<c:forEach items="${errorMessages}" var="error">
		<c:out value="${error}" />
		<br>
	</c:forEach>
	</div>


</body>
</html>