<title>Connexion</title>
<link href="css/connexion.css" rel="stylesheet">
</head>
<body>

<div class="grid">
	<header>
		<div class="eni-encheres">
			<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
		</div>

	</header>

		<div class="connexion">

				<form action="./Connexion" method="post">
				
				<div class="pseudo">
					
						<label>Identifiant :</label>
						<c:if test="${cookie.remember.value == 'rmb'}">
							<input type="text"  name="pseudo" value="${cookie.login.value}">
						</c:if>
						<c:if test="${cookie.remember.value == null}">
							<input type="text"  name="pseudo">
						</c:if>

					</div>

			
					<div class="password">
						
						<label>Mot de passe :</label>
						<c:if test="${cookie.remember.value == 'rmb'}">
							<input type="password" name="motDePasse" value="${cookie.password.value}">
						</c:if>
						<c:if test="${cookie.remember.value == null}">
							<input type="password" name="motDePasse">
						</c:if>
					</div>
					
					<div class="con">
						<button class="boutonCo" type="submit">Connexion</button>


						<div class="blocText">
							<div class="remember">
								<c:if test="${cookie.remember.value == 'rmb'}">
									<input type="checkbox" name="checkRemember" value="rmb" checked>
								</c:if>
								<c:if test="${cookie.remember.value == null}">
									<input type="checkbox" name="checkRemember" value="rmb">
								</c:if>	
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

	<form action="./Accueil" method="get">
		<input class="annuler" type="submit" value="Annuler">
	</form>
	</div>
</div>
</body>
</html>