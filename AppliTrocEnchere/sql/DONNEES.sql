INSERT INTO UTILISATEURS VALUES ('Bobby', 'Dylan','Bob','bobdylan@email.com','02.22.22.22.22','rue de la guitare',35000,'Guitarville','12345','4000',0);
INSERT INTO UTILISATEURS VALUES ('Le Vieux', 'Cash','Johnny','oldman@email.com','02.33.33.33.33','rue du desert',75000,'Old Man City','azert','4000',1);
INSERT INTO UTILISATEURS VALUES ('Un Mec', 'Mec','Un','unmec@email.com','02.55.55.55.55','rue du gars',35700,'Rennes','123','4000',0);

INSERT INTO CATEGORIES VALUES ('Informatique');
INSERT INTO CATEGORIES VALUES ('Ameublement');
INSERT INTO CATEGORIES VALUES ('Vêtement');
INSERT INTO CATEGORIES VALUES ('Sport&Loisirs');

INSERT INTO ARTICLES_VENDUS VALUES ('Tablette tactile Samsung', 'Galaxy Tab A 2019, WiFi, 32Go, Android 9.0, Argent','Creee', '2020-03-27','2020-04-03',150,150,1,1);
INSERT INTO ARTICLES_VENDUS VALUES ('Nespresso Inissia', 'cafetiere dosettes - Rouge rubis - Krups YY1531FD', 'Creee', '2020-03-28','2020-04-04',20,40,2,2);
INSERT INTO ARTICLES_VENDUS VALUES ('Huawei P30', 'Un téléphone Top Qualite','En cours', '2020-04-02','2020-04-08',100,100,1,1);
INSERT INTO ARTICLES_VENDUS VALUES ('Un billet de 1 million de dollar', 'Un billet qui vaut au moins 1 million de dollar', 'Terminee', '2020-04-01','2020-04-02',400,400,1,2);

INSERT INTO ENCHERES VALUES (2,1, GETDATE(),50);
INSERT INTO ENCHERES VALUES (3,1, GETDATE(),150);
INSERT INTO ENCHERES VALUES (2,4, GETDATE(),300);
INSERT INTO ENCHERES VALUES (3,4, GETDATE(),400);

INSERT INTO RETRAITS VALUES (1,'coucou',35000,'Rennes');
INSERT INTO RETRAITS VALUES (2,'bonjour',60000,'Jsaispasou');
INSERT INTO RETRAITS VALUES (3,'rue des pommiers',35000,'Rennes');
INSERT INTO RETRAITS VALUES (4,'allee de la thune',80000,'Piscouville');