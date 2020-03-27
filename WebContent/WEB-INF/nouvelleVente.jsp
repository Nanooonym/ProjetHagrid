<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle Vente</title>

</head>
<body>

<h1>Nouvelle Vente</h1>
	
	<form action="./AjoutArticle" method="post">
	
	<div>
	
		<div>
		
		<label  id="labelArticle" for="article">Article : </label>
		 <input type="text" name="nom_article" id="nom_article" autofocus="" required=""><br>
		
		</div>
			<label  id="labelDescription" for="description">Description : </label>
			<textarea name="description" rows="3" cols="40" required> </textarea><br>
		<div>
			<label id="categorie" for="categorie">Catégories</label> 
		    <select id="categorie" name="categorie">
		    <option value="toutes">Toutes</option>
            <option value="cat1">Informatique</option>
            <option value="cat2">Ameublement</option>
            <option value="cat3">Vêtement</option>
            <option value="cat4">Sport et Loisirs</option>
        </select>
			<br>
		</div>
		
		
		
		
		<div >
			<fieldset>
				<legend>
					<label  for="lieuDeRetrait">Retrait</label>
				</legend>
				<label  for="rue">Rue :</label>
				 <input type="text" name="rue" value="${utilisateur.getRue()}"required><br>
				 <label  for="code_postal"> Code Postal :</label>
				  <input type="text" name="code_postal" value="${utilisateur.getCodePostal()}" required><br>
				<label  for="ville">Ville :</label>
				 <input type="text" name="ville"
					value="${utilisateur.getVille()}" required>
			</fieldset>
		</div>
		
			<div>
			<input value="submit" type="submit">
						<a href="<%=request.getContextPath()%>/Accueil"><input type="button"value="Annuler"></a>
		</div>
</div>
	</form>
		
	
	
	
	
	
	

</body>
</html>