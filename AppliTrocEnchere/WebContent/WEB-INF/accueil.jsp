<title>Accueil</title>
</head>
<body>

<header>
<p>ENI-Enchères</p>


	<c:if test="${empty sessionScope.utilisateur}">
		<a href="<%=request.getContextPath()%>/Connexion">S'inscrire - Se connecter</a>
	</c:if>
	
	<c:if test="${!empty sessionScope['utilisateur'] }">			
		<c:out value="Utilisateur : ${sessionScope.utilisateur.pseudo}"/>
		<c:out value="Encheres"/>
		<a href="<%=request.getContextPath()%>/SupprimerCompte">Supprimer Compte</a>
		<a href="<%=request.getContextPath()%>/Deconnexion">Déconnexion</a>
		
	</c:if>


</header>

<h1>Liste des Enchères</h1>

<div>
<p>Filtres :</p>

<form>
<input type="text" id="textfield" name="filtre_nom_article">
<label>Catégories : </label>
		 <select id="categorie" name="categorie">
		    <option value="toutes">Toutes</option>
            <option value="cat1">Informatique</option>
            <option value="cat2">Ameublement</option>
            <option value="cat3">Vêtement</option>
            <option value="cat4">Sport et Loisirs</option>
        </select>
        <button type="submit">Rechercher</button>
        </form>
</div>
<div>

		<c:forEach items="${encheres}" var="enchere"><br>
			<c:out value="${enchere.articleVendu.nomArticle}" /><br>
			<c:out value="${enchere.articleVendu.dateFinEncheres}" /><br>
			<c:out value="${enchere.montantEnchere}" /><br>
			<c:out value="${enchere.utilisateur.pseudo}" /><br>
			<br>
		</c:forEach>


</div>
</body>
</html>