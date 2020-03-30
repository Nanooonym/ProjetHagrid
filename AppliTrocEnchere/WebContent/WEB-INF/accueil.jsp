<title>Accueil</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<div class="grid-accueil">
	
		<header>
		
			<div class="eni-encheres">
				<a href="<%=request.getContextPath()%>/Accueil">ENI-Ench�res</a>
			</div>
			
			<div class="options">
				<c:if test="${empty sessionScope.utilisateur}">
					<a href="<%=request.getContextPath()%>/Connexion">S'inscrire - Se connecter</a>
				</c:if>
				
				<c:if test="${!empty sessionScope['utilisateur'] }">			
					<c:out value="Utilisateur : ${sessionScope.utilisateur.pseudo}"/>
					<c:out value="Encheres"/>
					<a href="<%=request.getContextPath()%>/AjoutArticle">Vendre un article</a>
					<a href="<%=request.getContextPath()%>/MonProfil">Mon Profil</a>
					<a href="<%=request.getContextPath()%>/Deconnexion">D�connexion</a>	
				</c:if>
			</div>
			
			<div class="title">
				<p>Liste des Ench�res</p>
			</div>
			
		</header>

		<div class="filtres">
		
			<p>Filtres :</p>
			
			<form>
				<input class="formulaire-items" type="text" id="textfield" name="filtre_nom_article" placeholder="Le nom de l'article contient" size="60" style="height:30px">
					<div class="formulaire">
						<label class="formulaire-items">Cat�gorie : </label>
						<select class="formulaire-items" id="categorie" name="categorie">
						    <option value="0">Toutes</option>
				            <option value="1">Informatique</option>
				            <option value="2">Ameublement</option>
				            <option value="3">V�tement</option>
				            <option value="4">Sport et Loisirs</option>
				        </select>
			        </div>
			        
			        
			        <div class=choix>	
			        	<div class="achatVente">	
				        	<div class="formulaire">
					        	<input class="formulaire-items" type="radio" id="achats" name="AchatVente" value="Achats" checked="checked">
					        	<label class="formulaire-items" for="achats">Achats</label>
					        </div>
			
				        	<div class="formulaire-checkbox">
					        	<input class="formulaire-items" type="checkbox" id="enchereOuverte" name="Achat" value="enchereOuverte">
					        	<label class="formulaire-items" for="enchereOuverte">ench�res ouvertes</label>
				        	</div>
				        	
				        	<div class="formulaire-checkbox">
					        	<input class="formulaire-items" type="checkbox" id="enchereEnCours" name="Achat" value="enchereEnCours">
					        	<label class="formulaire-items" for="enchereOuverte">mes ench�res en cours</label>
				        	</div>
				        		
				        	<div class="formulaire-checkbox">
					        	<input type="checkbox" id="enchereRemportee" name="Achat" value="enchereRemportee">
					        	<label class="formulaire-items" for="enchereRemportee">mes ench�res remport�es</label>
							</div>			
						</div>
			        		
			        	<div class="achatVente">	
				        	<div class="formulaire">
					        	<input class="formulaire-items" type="radio" id="ventes" name="AchatVente" value="Mes Ventes">
					        	<label class="formulaire-items" for="ventes">Mes ventes</label>
				        	</div>
				        	
							<div class="formulaire-checkbox">
				        		<input class="formulaire-items" type="checkbox" id="ventesEnCours" name="Ventes" value="ventesEnCours" disabled>
				        		<label class="formulaire-items" for="ventesEnCours">mes ventes en cours</label>
				        	</div>	
				        	
				        	<div class="formulaire-checkbox">
				        		<input class="formulaire-items" type="checkbox" id="ventesNonDebutees" name="Ventes" value="ventesNonDebutees" disabled>
				        		<label class="formulaire-items" for="ventesNonDebutees">ventes non d�but�es</label>
				        	</div>
				        		
				        	<div class="formulaire-checkbox">
				        		<input class="formulaire-items" type="checkbox" id="ventesTerminees" name="Ventes" value="ventesTerminees" disabled>
				        		<label class="formulaire-items" for="ventesTerminees">ventes termin�es</label>
				        	</div>
			        	</div>
			        <script>
			        	$('input[name="AchatVente"]').on('change', function()
			        	{
			        		$('input[name="Achat"]').attr('disabled',this.value!="Achats")
			        		$('input[name="Ventes"]').attr('disabled',this.value=="Achats")
			        	});
			        </script>
			        </div>       
				</form>
			</div>
	
		<div class="rechercher">
			<button type="submit" class="rechercher">Rechercher</button>
		</div>

		<div class="encheres">
	
			<c:forEach items="${encheres}" var="enchere">
				<div class="enchere">
					<div><c:out value="${enchere.articleVendu.nomArticle}" /></div>
					<div><c:out value="Prix : ${enchere.montantEnchere}" /></div>
					
					<fmt:parseDate value="${enchere.articleVendu.dateFinEncheres}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
					<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd/MM/yyyy" />
					<div><c:out value="Fin de l'ench�re : ${newParsedDate}"/></div>
					<div><c:out value="Vendeur : ${enchere.utilisateur.pseudo}" /></div>
				</div>
			</c:forEach>
	
		</div>
	</div>

<!-- <footer> -->
<!-- <p>�ProjetHagrid - Team Moldus</p> -->
<!-- </footer> -->
</body>
</html>