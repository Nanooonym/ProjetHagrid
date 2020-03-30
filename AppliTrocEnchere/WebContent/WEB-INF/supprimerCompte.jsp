<title>Suppression Compte</title>
<link href="css/page.css" rel="stylesheet">
</head>
<body>

	<header style="">
		<div class="eni-encheres">
				<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
			</div>
	</header>


	<h1 class="title">Etes-vous sûr de supprimer votre compte ?</h1>

	<form action="./SupprimerCompte" method="post">
		<button type="submit" value="suppression">
			Supprimer
		</button>
	</form>
	<form action="<%=request.getContextPath()%>/Accueil" method="get">
		<button type="submit" value="accueil">
			Accueil
		</button>
	</form>

</body>
</html>