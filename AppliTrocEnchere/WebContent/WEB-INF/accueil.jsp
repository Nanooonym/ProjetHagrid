<link href="css/page.css" rel="stylesheet">
<title>Accueil</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link href="css/page.css" rel="stylesheet">
</head>
<body>
	<div class="grid-accueil">

		<header>

			<div class="eni-encheres">
				<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
			</div>

			<div class="options">
				<div><c:if test="${empty sessionScope.utilisateur}">
					<a href="<%=request.getContextPath()%>/Connexion">S'inscrire -
						Se connecter</a>
				</c:if></div>

				<c:if test="${!empty sessionScope['utilisateur'] }">
					<div class="option"><c:out value="Utilisateur : ${sessionScope.utilisateur.pseudo}" /></div>
					<div class="option"><c:out value="Encheres" /></div>
					<div class="option"><a href="<%=request.getContextPath()%>/AjoutArticle">Vendre un
						article</a></div>
					<div class="option"><a href="<%=request.getContextPath()%>/MonProfil">Mon Profil</a></div>
					<div class="option"><a href="<%=request.getContextPath()%>/Deconnexion">Déconnexion</a></div>
				</c:if>
			</div>

			<div class="title">
				<p>Liste des Enchères</p>
			</div>

		</header>
		<div class="filtres-container">
			<form action="<%=request.getContextPath()%>/Accueil" method="post">
		<div class="filtres">

			<p>Filtres :</p>

<!-- 					Filtres par nom et par catégorie -->
				<input class="formulaire-items" type="text" id="article"
					name="article" placeholder="Le nom de l'article contient"
					size="60" style="height: 30px">
				<div class="formulaire">
					<label class="formulaire-items">Catégorie : </label> <select
						class="formulaire-items" id="categorie" name="categorie">
						<option value="0">Toutes</option>
						<option value="1">Informatique</option>
						<option value="2">Ameublement</option>
						<option value="3">Vêtement</option>
						<option value="4">Sport et Loisirs</option>
					</select>
				</div>

<!-- 					Checkbox et Radio Buttons -->
				<c:if test="${!empty sessionScope['utilisateur'] }">
				<div class=choix>

					<div class="achatVente">

						<div class="formulaire">
							<input class="formulaire-items" type="radio" id="achats"
								name="AchatVente" value="Achats" checked="checked"> <label
								class="formulaire-items" for="achats">Achats</label>
						</div>

						<div class="formulaire-checkbox">
							<input class="formulaire-items" type="checkbox"
								id="encheresOuvertes" name="Achat" value="encheresOuvertes">
							<label class="formulaire-items" for="encheresOuvertes">enchères
								ouvertes</label>
						</div>

						<div class="formulaire-checkbox">
							<input class="formulaire-items" type="checkbox"
								id="encheresEnCours" name="Achat" value="encheresEnCours">
							<label class="formulaire-items" for="enchereOuverte">mes
								enchères en cours</label>
						</div>

						<div class="formulaire-checkbox">
							<input type="checkbox" id="encheresRemportees" name="Achat"
								value="encheresRemportees">
							<label class="formulaire-items" for="encheresRemportees">mes
								enchères remportées</label>
						</div>
					</div>

					<div class="achatVente">
						<div class="formulaire">
							<input class="formulaire-items" type="radio" id="ventes"
								name="AchatVente" value="Mes Ventes"> <label
								class="formulaire-items" for="ventes">Mes ventes</label>
						</div>

						<div class="formulaire-checkbox">
							<input class="formulaire-items" type="checkbox"
								id="ventesEnCours" name="Ventes" value="mesVentesEnCours" disabled>
							<label class="formulaire-items" for="ventesEnCours">mes
								ventes en cours</label>
						</div>

						<div class="formulaire-checkbox">
							<input class="formulaire-items" type="checkbox"
								id="ventesNonDebutees" name="Ventes" value="mesVentesNonDebutees"
								disabled> <label class="formulaire-items"
								for="ventesNonDebutees">ventes non débutées</label>
						</div>

						<div class="formulaire-checkbox">
							<input class="formulaire-items" type="checkbox"
								id="ventesTerminees" name="Ventes" value="mesVentesTerminees"
								disabled> <label class="formulaire-items"
								for="ventesTerminees">ventes terminées</label>
						</div>

					</div>

					<script>
			        	$('input[name="AchatVente"]').on('change', function()
			        	{
			        		$('input[name="Achat"]').attr('disabled',this.value!="Achats")
			        		$('input[name="Achat"]').attr('enabled',this.value=="Achats")
			        		$('input[name="Ventes"]').attr('disabled',this.value=="Achats")
			        		$('input[name="Ventes"]').attr('enabled',this.value!="Achats")
			        		
			        	});
			        </script>
				</div>
					</c:if>


		</div>
		</div>
		<div>
			<button type="submit" class="rechercher">Rechercher</button>
		</div>
		</form>
		
		
		<div class="encheres">

			<c:forEach items="${encheres}" var="enchere">
				<div class="enchere">
					
					
					<div>
						<c:if test="${enchere.utilisateur.pseudo == sessionScope.utilisateur.pseudo}">
						<a href="<%=request.getContextPath()%>/ModifierVente?idArticle=${enchere.articleVendu.noArticle}">
								${ enchere.articleVendu.nomArticle }</a>
						</c:if>
						<c:if test="${enchere.utilisateur.pseudo != sessionScope.utilisateur.pseudo}">
						<a href="<%=request.getContextPath()%>/DetailVente?idArticle=${enchere.articleVendu.noArticle}">
								${ enchere.articleVendu.nomArticle }</a>
						</c:if>
						
					</div>
					
					<div>
						<c:out value="Prix : ${enchere.articleVendu.prixVente}" />
					</div>

					<fmt:parseDate value="${enchere.articleVendu.dateFinEncheres}"
						pattern="yyyy-MM-dd" var="parsedDate" type="date" />
					<fmt:formatDate value="${parsedDate}" var="newParsedDate"
						type="date" pattern="dd/MM/yyyy" />
					<div>
						<c:out value="Fin de l'enchère : ${newParsedDate}" />
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/MonProfil">Vendeur :
							${enchere.utilisateur.pseudo}</a>
					</div>
				</div>
			</c:forEach>
			
<!-- 			Messages d'erreur -->
			<c:forEach items="${errorMessages}" var="error">
				<c:out value="${error}" />
				<br>
			</c:forEach>

		</div>
	</div>

	<footer>
	<p>©ProjetHagrid - Team Moldus</p>
	</footer>
</body>
</html>