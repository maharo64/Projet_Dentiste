CREATE  TABLE "public".materiel ( 
	idmateriel           integer  NOT NULL  ,
	materiel                  varchar  NOT NULL  ,
	CONSTRAINT pk_materiel PRIMARY KEY ( idmateriel )
 );
   
CREATE  TABLE "public".servicedepense ( 
	idservice            integer  NOT NULL  ,
	idmateriel           integer  NOT NULL  ,
	quantite               integer  NOT NULL  
 );
CREATE  TABLE "public".service ( 
	idservice            integer  NOT NULL  ,
	nomservice           varchar  NOT NULL  ,
	CONSTRAINT pk_reparation PRIMARY KEY ( idservice )
 );
ALTER TABLE "public".servicedepense ADD CONSTRAINT fk_servicedepense_service FOREIGN KEY ( idservice ) REFERENCES "public".service( idservice );

ALTER TABLE "public".servicedepense ADD CONSTRAINT fk_servicedepensemateriel_materiel FOREIGN KEY ( idmateriel ) REFERENCES "public".materiel( idmateriel );
