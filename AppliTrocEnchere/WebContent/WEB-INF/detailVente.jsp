<title>DetailVente</title>
</head>
<body>

<header style=flex>
<p>ENI-Ench�res</p>
</header>


	<h1 class="title">D�tail Vente</h1>


		<div>
		<c:forEach items="${encheres}" var="enchere">
			<br>
			<div><c:out value="${enchere.articleVendu.nomArticle}" /></div><br>
			<div>Description :  <c:out value="${enchere.articleVendu.description}" /></div><br>
			<div>Categorie :  <c:out value="${enchere.categorie.libelle}" /> points</div>
			<div>Meilleure offre : <c:out value="${enchere.articleVendu.montant_enchere}" /></div>
			<div>Mise � prix : <c:out value="${enchere.articleVendu.prix_vente}" /></div>
			<div>Fin de l'ench�re : <c:out value="${enchere.articleVendu.dateFinEncheres}" /></div>
			<div>Retrait : <c:out value="${enchere.retrait.rue}" /></div>
							<c:out value="${enchere.retrait.code_postal}" />
							<c:out value="${enchere.retrait.ville}" />
			<div>Vendeur : <c:out value="${enchere.utilisateur.pseudo}" /></div>
			<div>Ma proposition : </div>
			<br>
		</c:forEach>
			<button type="submit">Ench�rir</button>
		</div>

</body>
</html>