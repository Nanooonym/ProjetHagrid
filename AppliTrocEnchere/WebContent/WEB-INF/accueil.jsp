<title>Accueil</title>
</head>
<body>

<header style=flex>
<p>ENI-Enchères</p>


<a href="<%=request.getContextPath()%>/Connexion">S'inscrire - Se connecter</a>
</header>

<h1>Liste des Enchères</h1>

<div>
<p>Filtres :</p>

<form>
<input type="text" id="textfield" name="filtre_nom_article">
<label>Catégories : </label>
		 <select id="categorie" name="categorie">
		    <option value="toutes">toutes</option>
            <option value="cat1">cat1</option>
            <option value="cat2">cat2</option>
            <option value="cat3">cat3</option>
            <option value="cat4">cat4</option>
        </select>
        <button type="submit">Rechercher</button>
        </form>
</div>
<div style=flex>
	<p>Enchere 1</p>
	<p>Enchere 2</p>
</div>
</body>
</html>