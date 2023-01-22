CREATE DATABASE ProjetS5;
\C ProjetS5;

CREATE TABLE Genre(
    idGenre SERIAL PRIMARY KEY,
    Sexe VARCHAR(20)
);
INSERT INTO GENRE(sexe) VALUES('Homme'), ('Femme');

CREATE TABLE NiveauEtude(
    idNiveauEtude SERIAL PRIMARY KEY,
    niveau VARCHAR(20)
);
INSERT INTO NiveauEtude(niveau) VALUES('CP'), ('BEPC'), ('BACC'), ('LICENCE'), ('MASTER I'), ('MASTER II'), ('DOCTORAT');


CREATE TABLE Emp(
    idEmp SERIAL PRIMARY KEY,
    Nom VARCHAR(40),
    Prenom VARCHAR(40),
    idGenre INT references Genre(idGenre),
    naissance TIMESTAMP, 
    idNiveauEtude INT references NiveauEtude(idNiveauEtude),
	salaire INT
);

INSERT INTO EMP(Nom, prenom, idGenre, naissance, idNiveauEtude,salaire)
    VALUES('RAKOTO', 'Jean', 1, '2001-11-11', 1,1000);
INSERT INTO EMP(Nom, prenom, idGenre, naissance, idNiveauEtude,salaire)
    VALUES('RABE', 'Jule', 1, '2001-06-21', 3,1200);
INSERT INTO EMP(Nom, prenom, idGenre, naissance, idNiveauEtude,salaire)
    VALUES('RAHARIMANANA', 'Annie', 2, '2001-01-11', 5,1500);
INSERT INTO EMP(Nom, prenom, idGenre,naissance, idNiveauEtude,salaire)
    VALUES('RAKOTO', 'Betty', 2, '2002-01-14', 4,1290);  

CREATE TABLE Specialite(
    idSpecialite SERIAL PRIMARY KEY,
    Specialite VARCHAR(40)
);

CREATE TABLE Special(
    idSpecial SERIAL PRIMARY KEY,
    idEmp INT references Emp(idEmp),
    idSpecialite INT references Specialite(idSpecialite)
);

CREATE VIEW V_EMPLOYE AS 
    SELECT  EMP.idemp idEmp, EMP.nom nom, EMP.prenom prenom, EMP.idGenre idgenre, GENRE.sexe sexe, EMP.naissance datenaissance, emp.idniveauetude idniveauetude, niveauetude.niveau niveauetude
        FROM EMP 
            JOIN Genre 
                ON genre.idGenre=emp.idGenre
            JOIN NiveauEtude 
                ON Emp.idNiveauEtude = niveauetude.idniveauetude
;
CREATE VIEW V_SPECIALITE AS
    SELECT Special.idSpecial idSpecial, Emp.idEmp idEmp,Specialite.idSpecialite idSpecialite,Specialite.Specialite Specialite 
        FROM Special
            JOIN Emp on Special.idEmp=Emp.idEmp
            JOIN Specialite on Special.idSpecialite=Specialite.idSpecialite

;