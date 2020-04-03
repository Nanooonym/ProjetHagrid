INSERT INTO UTILISATEURS VALUES ('Bobby', 'Dylan','Bob','bobdylan@email.com','02.22.22.22.22','rue de la guitare',35000,'Guitarville','12345','50',0);
INSERT INTO UTILISATEURS VALUES ('Le Vieux', 'Cash','Johnny','oldman@email.com','02.33.33.33.33','rue du desert',75000,'Old Man City','azert','200',1);

INSERT INTO CATEGORIES VALUES ('Informatique');
INSERT INTO CATEGORIES VALUES ('Ameublement');
INSERT INTO CATEGORIES VALUES ('Vêtement');
INSERT INTO CATEGORIES VALUES ('Sport&Loisirs');

INSERT INTO ARTICLES_VENDUS VALUES ('Tablette tactile Samsung', 'Galaxy Tab A 2019, WiFi, 32Go, Android 9.0, Argent','Creee', '2020-03-27','2020-04-03',262,150,1,1);
INSERT INTO ARTICLES_VENDUS VALUES ('Nespresso Inissia', 'cafetiere dosettes - Rouge rubis - Krups YY1531FD', 'Creee', '2020-03-28','2020-04-04',80,40,2,2);

INSERT INTO ENCHERES VALUES (1,2, GETDATE(),45);
INSERT INTO ENCHERES VALUES (2,1,GETDATE(),105);

INSERT INTO RETRAITS VALUES (1,'coucou',35000,'Rennes');
INSERT INTO RETRAITS VALUES (2,'bonjour',60000,'Jsaispasou');