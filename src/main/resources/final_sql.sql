CREATE TABLE Personne
(
    nom VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Devise
(
    nom  VARCHAR(255),
    code VARCHAR(10),
    PRIMARY KEY (nom, code)
);


CREATE TABLE Argent
(
    possession_id    INTEGER,
    date_d_ouverture DATE,
    type             VARCHAR(50),
    FOREIGN KEY (possession_id) REFERENCES Possession (id),
    CONSTRAINT chk_argent_type CHECK (type IN ('DETTE', 'CREANCE', 'AUTRES'))
);

CREATE TABLE Materiel
(
    possession_id             INTEGER,
    date_d_acquisition        DATE,
    taux_dappreciation_annuel DOUBLE PRECISION,
    FOREIGN KEY (possession_id) REFERENCES Possession (id)
);

CREATE TABLE FluxArgent
(
    possession_id    INTEGER,
    argent_id        INTEGER,
    debut            DATE,
    fin              DATE,
    flux_mensuel     INTEGER,
    date_d_operation INTEGER,
    FOREIGN KEY (possession_id) REFERENCES Possession (id),
    FOREIGN KEY (argent_id) REFERENCES Argent (id)
);



CREATE TABLE Patrimoine
(
    nom              VARCHAR(255) PRIMARY KEY,
    possesseur_nom   VARCHAR(255),
    t                DATE,
    valeur_comptable INTEGER,
    FOREIGN KEY (possesseur_nom) REFERENCES Personne (nom)
);

CREATE TABLE Possession
(
    id               SERIAL PRIMARY KEY,
    t                DATE,
    nom              VARCHAR(255),
    patrimoine_nom   VARCHAR(255),
    valeur_comptable INTEGER,
    devise_nom       VARCHAR(255),
    devise_code      VARCHAR(10),
    type             VARCHAR(50),
    FOREIGN KEY (devise_nom, devise_code) REFERENCES Devise (nom, code),
    FOREIGN KEY (patrimoine_nom) REFERENCES Patrimoine (nom),
    CONSTRAINT chk_possession_type CHECK (type IN ('ARGENT', 'MATERIEL', 'FLUXARGENT'))
);

CREATE TABLE FluxImpossibles
(
    id            SERIAL PRIMARY KEY,
    nom_argent    VARCHAR(255),
    date          DATE,
    valeur_argent INTEGER
);

CREATE TABLE FluxImpossibles_FluxArgents
(
    flux_impossibles_id INTEGER,
    flux_argent_id      INTEGER,
    FOREIGN KEY (flux_impossibles_id) REFERENCES FluxImpossibles (id),
    FOREIGN KEY (flux_argent_id) REFERENCES FluxArgent (id)
);

CREATE TABLE TransfertArgent
(
    id               SERIAL PRIMARY KEY,
    nom              VARCHAR(255),
    depuis_argent_id INTEGER,
    vers_argent_id   INTEGER,
    debut            DATE,
    fin              DATE,
    flux_mensuel     INTEGER,
    date_operation   INTEGER,
    devise_nom       VARCHAR(255),
    devise_code      VARCHAR(10),
    FOREIGN KEY (depuis_argent_id) REFERENCES Argent (id),
    FOREIGN KEY (vers_argent_id) REFERENCES Argent (id),
    FOREIGN KEY (devise_nom, devise_code) REFERENCES Devise (nom, code)
);