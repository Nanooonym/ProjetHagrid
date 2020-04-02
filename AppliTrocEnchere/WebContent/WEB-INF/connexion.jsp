<title>Connexion</title>
<link href="css/connexion.css" rel="stylesheet">
</head>

<body>

	<div class="grid">
	<header >
		<div class="eni-encheres">
			<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
		</div>

	</header>

	
		<div class="connexion">

			<form action="./Connexion" method="post">

				<div class="pseudo">
					<label>Identifiant :</label> <input type="text" id="textfield"
						name="pseudo">
				</div>

				<div class="password">
					<label>Mot de passe :</label> <input type="password" id="textfield"
						name="motDePasse">
				</div>




				<div class="con">
					<button class="boutonCo" type="submit">Connexion</button>

					<div class="blocText">
						<div class="remember">
							<input type="checkbox" value="remember">

							<p>Se souvenir de moi</p>
						</div>
						<p>Mot de Passe oublié</p>
					</div>
				</div>
		
		</form>
		
			<form action="./Inscription" method="get">
				<button class="creerCompte" type="submit">Créer un Compte</button>
			</form>
		


		<c:forEach items="${errorMessages}" var="error">
			<c:out value="${error}" />
			<br>
		</c:forEach>

	

	
		<form  action="./Accueil" method="get">
			<input class="annuler" type="submit" value="Annuler">
		</form>
		</div>
	</div>
	
</body>
</html>