CREATE TABLE person
(
    nom VARCHAR(255) PRIMARY KEY
);

CREATE TABLE patrimoine
(
    nom        VARCHAR(255) PRIMARY KEY,
    person_nom VARCHAR(255) REFERENCES person (nom),
    t          DATE
);

CREATE TABLE devise
(
    code VARCHAR(50),
    nom  VARCHAR(50),
    PRIMARY KEY (code)
);

CREATE TABLE materiel
(
    nom                          VARCHAR PRIMARY KEY,
    t                            TIMESTAMP,
    patrimoine_nom               VARCHAR REFERENCES patrimoine (nom),
    valeur_comptable             INT,
    devise_code                  INT REFERENCES devise (code),
    date_acquisition             DATE,
    taux_d_appreciation_annuelle DOUBLE PRECISION
);


CREATE TABLE argent
(
    nom              VARCHAR,
    t                DATE,
    patrimoine_nom   VARCHAR REFERENCES patrimoine (nom),
    valeur_comptable INT,
    devise_code      INT REFERENCES devise (code),
    date_ouverture   DATE,
    type             VARCHAR(50) CHECK (type IN ('DETTE', 'CREANCE', 'AUTRES'))
)


CREATE TABLE flux_argent
(
    nom              VARCHAR PRIMARY KEY,
    t                TIMESTAMP,
    patrimoine_nom   VARCHAR REFERENCES patrimoine (nom),
    valeur_comptable INT,
    devise_code      INT REFERENCES devise (code),
    debut            DATE,
    fin              DATE,
    date_operation   INT,
    flux_mensuel     INT,
    flux_impossibles DATE REFERENCES flux_impossibles (date),
    argent_nom       VARCHAR REFERENCES argent (nom)
);

CREATE TABLE flux_impossibles
(
    date          DATE,
    nom_argent    VARCHAR(255),
    valeur_argent INTEGER
);
