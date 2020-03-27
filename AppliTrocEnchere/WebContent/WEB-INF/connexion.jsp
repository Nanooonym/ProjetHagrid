<title>Connexion</title>
<link href="css/page.css" rel="stylesheet">
</head>
<body>

<header class="container">
<h2>ENI-Enchères</h2>
</header>


		<h1 class="title">Mon profil</h1>
<div class="container-column">


		<div class="container-row">

			<div class="container-column">
				<form action="./Connexion" method="post">
				<div class="container-formulaire">
					<label>Identifiant :</label>
					<input type="text" id="textfield" name="pseudo">
				</div>

				<div class="container-formulaire">
					<label>Mot de passe :</label>
					<input type="text" id="textfield" name="motDePasse">
				</div>
				<div class="container-formulaire">

						<button type="submit">Connexion</button>
						<div class="container-column">
							<div class="container-formulaire">
							<input type="checkbox" value="remember">
							<p>Se souvenir de moi</p>
							</div>
							<p>Mot de Passe oublié</p>
						</div>

				</div>
				</form>

					<form action="./Inscription" method="get">
						<button type="submit">Créer un Compte</button>
					</form>

		</div>
		</div>
	


</div>

			<c:forEach items="${errorMessages}" var="error">
				<c:out value="${error}" />
				<br>
			</c:forEach>

		<form action="./Accueil" method="get">
			<input type="submit" value="Annuler">
		</form>

</body>
</html>