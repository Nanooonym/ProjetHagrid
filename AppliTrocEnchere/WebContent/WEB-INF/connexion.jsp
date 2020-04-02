<title>Connexion</title>
<link href="css/page.css" rel="stylesheet">
</head>
<body>

	<header style="">
		<div class="eni-encheres">
			<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
		</div>

	</header>

	<div class="container-column">

		<div class="container-row">

			<div class="container-column">
				<form action="./Connexion" method="post">
					<div class="container-formulaire">
						<label>Identifiant :</label>
						<c:if test="${cookie.remember.value == 'rmb'}">
							<input type="text"  name="pseudo" value="${cookie.login.value}">
						</c:if>
						<c:if test="${cookie.remember.value == null}">
							<input type="text"  name="pseudo">
						</c:if>

					</div>

					<div class="container-formulaire">
						<label>Mot de passe :</label>
						<c:if test="${cookie.remember.value == 'rmb'}">
							<input type="password" name="motDePasse" value="${cookie.password.value}">
						</c:if>
						<c:if test="${cookie.remember.value == null}">
							<input type="password" name="motDePasse">
						</c:if>
					</div>
					<div class="container-formulaire">

						<button type="submit">Connexion</button>
						<div class="container-column">
							<div class="container-formulaire">
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