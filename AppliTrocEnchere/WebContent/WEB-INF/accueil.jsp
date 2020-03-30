<title>Accueil</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<div class="grid-accueil">

		<header>

			<div class="eni-encheres">
				<a href="<%=request.getContextPath()%>/Accueil">ENI-Ench�res</a>
			</div>

			<div class="options">
				<c:if test="${empty sessionScope.utilisateur}">
					<a href="<%=request.getContextPath()%>/Connexion">S'inscrire -
						Se connecter</a>
				</c:if>

				<c:if test="${!empty sessionScope['utilisateur'] }">
					<c:out value="Utilisateur : ${sessionScope.utilisateur.pseudo}" />
					<c:out value="Encheres" />
					<a href="<%=request.getContextPath()%>/AjoutArticle">Vendre un
						article</a>
					<a href="<%=request.getContextPath()%>/MonProfil">Mon Profil</a>
					<a href="<%=request.getContextPath()%>/Deconnexion">D�connexion</a>
				</c:if>
			</div>

			<div class="title">
				<p>Liste des Ench�res</p>
			</div>

		</header>
		<div class="filtres-container">
			<form action="<%=request.getContextPath()%>/Accueil" method="post">
		<div class="filtres">

			<p>Filtres :</p>


				<input class="formulaire-items" type="text" id="textfield"
					name="filtre_nom_article" placeholder="Le nom de l'article contient"
					size="60" style="height: 30px">
				<div class="formulaire">
					<label class="formulaire-items">Cat�gorie : </label> <select
						class="formulaire-items" id="categorie" name="categorie">
						<option value="toutes">Toutes</option>
						<option value="cat1">Informatique</option>
						<option value="cat2">Ameublement</option>
						<option value="cat3">V�tement</option>
						<option value="cat4">Sport et Loisirs</option>
					</select>
				</div>


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
							<label class="formulaire-items" for="encheresOuvertes">ench�res
								ouvertes</label>
						</div>

						<div class="formulaire-checkbox">
							<input class="formulaire-items" type="checkbox"
								id="encheresEnCours" name="Achat" value="encheresEnCours">
							<label class="formulaire-items" for="enchereOuverte">mes
								ench�res en cours</label>
						</div>

						<div class="formulaire-checkbox">
							<input type="checkbox" id="encheresRemportees" name="Achat"
								value="encheresRemportees">
							<label class="formulaire-items" for="encheresRemportees">mes
								ench�res remport�es</label>
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
								id="ventesEnCours" name="Ventes" value="ventesEnCours" disabled>
							<label class="formulaire-items" for="ventesEnCours">mes
								ventes en cours</label>
						</div>

						<div class="formulaire-checkbox">
							<input class="formulaire-items" type="checkbox"
								id="ventesNonDebutees" name="Ventes" value="ventesNonDebutees"
								disabled> <label class="formulaire-items"
								for="ventesNonDebutees">ventes non d�but�es</label>
						</div>

						<div class="formulaire-checkbox">
							<input class="formulaire-items" type="checkbox"
								id="ventesTerminees" name="Ventes" value="ventesTerminees"
								disabled> <label class="formulaire-items"
								for="ventesTerminees">ventes termin�es</label>
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


		</div>
		
		<div class="rechercher">
			<button type="submit" class="rechercher">Rechercher</button>
		</div>
		</form>
		</div>
		
		<div class="encheres">

			<c:forEach items="${encheres}" var="enchere">
				<div class="enchere">
					<div>
						<c:out value="${enchere.articleVendu.nomArticle}" />
					</div>
					
					<div>
						<a href="<%=request.getContextPath()%>/EnchereRemportee">${enchere.articleVendu.nomArticle} :</a>
					</div>
					
					<div>
						<c:out value="Prix : ${enchere.articleVendu.prixVente}" />
					</div>

					<fmt:parseDate value="${enchere.articleVendu.dateFinEncheres}"
						pattern="yyyy-MM-dd" var="parsedDate" type="date" />
					<fmt:formatDate value="${parsedDate}" var="newParsedDate"
						type="date" pattern="dd/MM/yyyy" />
					<div>
						<c:out value="Fin de l'ench�re : ${newParsedDate}" />
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/MonProfil">Vendeur :
							${enchere.utilisateur.pseudo}</a>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>

	<footer>
	<p>�ProjetHagrid - Team Moldus</p>
	</footer>
</body>
</html>