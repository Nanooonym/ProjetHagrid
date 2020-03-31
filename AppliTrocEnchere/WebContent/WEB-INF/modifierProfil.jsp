<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modification du profil</title>
<link href="css/page.css" rel="stylesheet">
</head>
<body>

	<header style="">
		<h2>ENI-Enchères</h2>
	</header>

	<h1 class="title">Mon profil</h1>
	<div class="container-column">

		<form action="./ModificationProfil" method="post">
			<div class="container-row">

				<div class="container-column">
					<div class="container-formulaire">
						<label>Pseudo :</label> <input type="text" id="textfield"
							name="pseudo" value="${sessionScope.utilisateur.pseudo}">
					</div>

					<div class="container-formulaire">
						<label>Prénom :</label> <input type="text" id="textfield"
							name="prenom" value="${sessionScope.utilisateur.prenom}">
					</div>

					<div class="container-formulaire">
						<label>Téléphone :</label> <input type="text" id="textfield"
							name="telephone" value="${sessionScope.utilisateur.telephone}">
					</div>

					<div class="container-formulaire">
						<label>Code postal :</label> <input type="text" id="textfield"
							name="codePostal" value="${sessionScope.utilisateur.codePostal}">
					</div>

					<div class="container-formulaire">
<<<<<<< HEAD
						<label>Mot de passe actuel:</label> <input type="password" id="textfield"
							name="motDePasseActuel">
					</div>
					
					<div class="container-formulaire">
						<label>Nouveau mot de passe:</label> <input type="password" id="textfield" name="nouveauMotDePasse">
=======
						<label>Mot de passe actuel:</label> <input type="text" id="textfield"
							name="motDePasseActuel">
					</div>
					
					<div class="container-formulaire">
						<label>Nouveau mot de passe:</label> <input type="text" id="textfield" name="nouveauMotDePasse">
>>>>>>> refs/heads/master
					</div>
				</div>

				<div class="container-column">
					<div class="container-formulaire">
						<label>Nom :</label> <input type="text" id="textfield" name="nom"
							value="${sessionScope.utilisateur.nom}">
					</div>

					<div class="container-formulaire">
						<label>Email :</label> <input type="text" id="textfield"
							name="email" value="${sessionScope.utilisateur.email}">
					</div>

					<div class="container-formulaire">
						<label>Rue :</label> <input type="text" id="textfield" name="rue"
							value="${sessionScope.utilisateur.rue}">
					</div>

					<div class="container-formulaire">
						<label>Ville :</label> <input type="text" id="textfield"
							name="ville" value="${sessionScope.utilisateur.ville}">
					</div>

					<div class="container-formulaire">
						<label>Confirmation :</label> <input type="password" id="textfield"
							name="confirmation">
					</div>
							<c:out value="${sessionScope.utilisateur.credit}"/>
				</div>

			</div>
			<input type="submit" value="Enregistrer" class="btn btn-primary">
		</form>


		<form action="<%=request.getContextPath()%>/SupprimerCompte" method="get">
			<button type="submit" value="supprimerCompte">Supprimer mon compte</button>
		</form>
		<form action="<%=request.getContextPath()%>/MonProfil" method="get">
			<button type="submit" value="monProfil">Retour</button>
		</form>

	</div>
</body>
</html>