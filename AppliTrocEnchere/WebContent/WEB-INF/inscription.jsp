<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription</title>
<link href="css/creationProfil.css" rel="stylesheet">
</head>
<body>


	<header style="">
	<div class="eni-encheres">
				<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
			</div>

	</header>
<div class="contenu">

	<h1 class="title">Mon profil</h1>
	<div class="container-column">

		<form action="./Inscription" method="post">
			<div class="container-row">

				<div class="container-column">
					<div class="container-formulaire">
						<label>Pseudo :</label> <input type="text" id="textfield"
							name="pseudo">
					</div>

					<div class="container-formulaire">
						<label>Prénom :</label> <input type="text" id="textfield"
							name="prenom">
					</div>

					<div class="container-formulaire">
						<label>Téléphone :</label> <input type="text" id="textfield"
							name="telephone">
					</div>

					<div class="container-formulaire">
						<label>Code postal :</label> <input type="text" id="textfield"
							name="codePostal">
					</div>

					<div class="container-formulaire">
						<label>Mot de passe :</label> <input type="password" id="textfield"
							name="motDePasse">
					</div>
				</div>

				<div class="container-column">
					<div class="container-formulaire">
						<label>Nom :</label> <input type="text" id="textfield" name="nom">
					</div>

					<div class="container-formulaire">
						<label>Email :</label> <input type="text" id="textfield"
							name="email">
					</div>

					<div class="container-formulaire">
						<label>Rue :</label> <input type="text" id="textfield" name="rue">
					</div>

					<div class="container-formulaire">
						<label>Ville :</label> <input type="text" id="textfield"
							name="ville">
					</div>

					<div class="container-formulaire">
						<label>Confirmation :</label> <input type="password" id="textfield"
							name="confirmation">
					</div>

				</div>

			</div>
			
			<c:forEach items="${errorMessages}" var="error">
				<c:out value="${error}" />
				<br>
			</c:forEach>
		</form>
		<div class="boutons">
			
				<button class="creation" type="submit">Créer</button>
			
	
	<form action="./Connexion" method="get">
		<input class="retour" type="submit" value="Annuler">
	</form>
	
	</div>
</div>
</div>	
</body>
</html>