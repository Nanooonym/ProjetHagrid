<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/monProfil.css" rel="stylesheet">
<title>Profil de ${utilisateur.pseudo}</title>
</head>

<body>
	
	<header >
		<div class="eni-encheres">
			<a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a>
		</div>

	</header>
	
	<div class="container-formulaire">

		<label>Pseudo :</label>
		<p>${utilisateur.pseudo}</p>


	</div>

	<div class="container-formulaire">

		<label>Nom :</label>
		<p>${utilisateur.nom}</p>

	</div>

	<div class="container-formulaire">

		<label>Prénom :</label>
		<p>${utilisateur.prenom}</p>

	</div>

	<div class="container-formulaire">

		<label>Email :</label>
		<p>${utilisateur.email}</p>

	</div>

	<div class="container-formulaire">

		<label>Téléphone :</label>
		<p>${utilisateur.telephone}</p>

	</div>

	<div class="container-formulaire">

		<label>Rue :</label>
		<p>${utilisateur.rue}</p>

	</div>

	<div class="container-formulaire">

		<label>Code postal :</label>
		<p>${utilisateur.codePostal}</p>

	</div>

	<div class="container-formulaire">

		<label>Ville :</label>
		<p>${utilisateur.ville}</p>

	</div>

	<div class="container-formulaire">

		<form action="<%=request.getContextPath()%>/Accueil" method="get">
			<button type="submit" class="cancel" value="accueil">Accueil</button>
		</form>


	</div>
	
</body>
</html>