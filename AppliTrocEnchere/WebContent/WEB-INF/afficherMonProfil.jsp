<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon profil</title>
</head>
<body>
<div class="container-column">

<div class="container-formulaire">

<label type="text" value="pseudo">Pseudo :</label> 
<p>${utilisateur.pseudo }</p>


</div>

<div class="container-formulaire">

<label type="text" value="Nom">Nom :</label>
<p>${utilisateur.nom }</p>

</div>

<div class="container-formulaire">

<label type="text" value="Prenom">Prénom :</label>
<p>${utilisateur.prenom }</p>

</div>

<div class="container-formulaire">

<label type="text" value="Email">Email :</label>
<p>${utilisateur.email }</p>

</div>

<div class="container-formulaire">

<label type="text" value="pseudo">Téléphone :</label>
<p>${utilisateur.telephone }</p>

</div>

<div class="container-formulaire">

<label type="text" value="pseudo">Rue :</label>
<p>${utilisateur.rue }</p>

</div>

<div class="container-formulaire">

<label type="text" value="pseudo">Code postal :</label>
<p>${utilisateur.codePostal }</p>

</div>

<div class="container-formulaire">

<label type="text" value="pseudo">ville :</label>
<p>${utilisateur.ville }</p>

</div>
<div class="contenu">
		<a href="<%=request.getContextPath()%>/modifierProfil"><input
			type="button" class="btn btn-primary"
			value="Modifier" /></a>
</div>
						

</div>
</body>
</html>