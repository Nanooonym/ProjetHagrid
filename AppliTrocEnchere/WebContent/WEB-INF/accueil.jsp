<title>Accueil</title>
</head>
<body>

<header style=flex>
<p>ENI-Ench�res</p>


	<c:if test="${empty sessionScope.utilisateur}">
		<a href="<%=request.getContextPath()%>/Connexion">S'inscrire - Se connecter</a>
	</c:if>
	
	<c:if test="${!empty sessionScope['utilisateur'] }">			
		<c:out value="Utilisateur : ${sessionScope.utilisateur.pseudo}"/>
		<c:out value="Encheres"/>
		<a href="<%=request.getContextPath()%>/SupprimerCompte">Supprimer Compte</a>
		<a href="<%=request.getContextPath()%>/Deconnexion">D�connexion</a>
		
	</c:if>


</header>

<h1>Liste des Ench�res</h1>

<div>
<p>Filtres :</p>

<form>
<input type="text" id="textfield" name="filtre_nom_article">
<label>Cat�gories : </label>
		 <select id="categorie" name="categorie">

		    <option value="toutes">Toutes</option>

            <option value="cat1">Informatique</option>
            <option value="cat2">Ameublement</option>
            <option value="cat3">V�tement</option>
            <option value="cat4">Sport et Loisirs</option>
        </select>
        <button type="submit">Rechercher</button>
        </form>
</div>
<div style=flex>

		
		<div>
		<c:forEach items="${encheres}" var="enchere">
			<br>
			<div> <a href="<%=request.getContextPath()%>/DetailVente"><c:out value="${enchere.articleVendu.nomArticle}" /></a></div><br>
			<div>Prix : <c:out value="${enchere.montantEnchere}" /> points</div>
			<div>Fin de l'ench�re : <c:out value="${enchere.articleVendu.dateFinEncheres}" /></div>
			<div>Vendeur : <c:out value="${enchere.utilisateur.pseudo}" /></div>
			<br>
		</c:forEach>
		</div>

</div>
</body>
</html>