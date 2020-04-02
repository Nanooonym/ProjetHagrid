<title>Suppression Compte</title>
<link href="css/supprimerCompte.css" rel="stylesheet">
</head>
<body>

	<header style="">
		<div class="eni-encheres">
				<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
			</div>
	</header>


	<h1 class="title">Etes-vous sûr de supprimer votre compte ?</h1>
<div class="boutons">
	<form action="./SupprimerCompte" method="post">
		<button class="delete" type="submit" value="suppression">
			Supprimer
		</button>
	</form>
	<form action="<%=request.getContextPath()%>/Accueil" method="get">
		<button class="annuler" type="submit" value="accueil">
			Accueil
		</button>
	</form>
</div>
</body>
</html>