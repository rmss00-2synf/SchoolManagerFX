-- Table Personne
CREATE TABLE
    Personne (
        id SERIAL PRIMARY KEY,
        nom VARCHAR(255) NOT NULL,
        prenom VARCHAR(255) NOT NULL
    );

-- Table Utilisateur (hérite de Personne)
CREATE TABLE
    Utilisateur (
        id SERIAL PRIMARY KEY,
        username VARCHAR(255) UNIQUE NOT NULL,
        password VARCHAR(255) NOT NULL,
        role VARCHAR(50) CHECK (role IN ('ADMIN', 'TEACHER', 'SECRETARY')),
        CONSTRAINT fk_personne_utilisateur FOREIGN KEY (id) REFERENCES Personne (id) ON DELETE CASCADE
    );

-- Table Etudiant (hérite de Personne)
CREATE TABLE
    Etudiant (
        id SERIAL PRIMARY KEY,
        matricule VARCHAR(255) UNIQUE NOT NULL,
        date_naissance DATE NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL,
        promotion INT NOT NULL,
        CONSTRAINT fk_personne_etudiant FOREIGN KEY (id) REFERENCES Personne (id) ON DELETE CASCADE
    );

-- Table Professeur (hérite de Personne)
CREATE TABLE
    Professeur (
        id SERIAL PRIMARY KEY,
        specialite VARCHAR(255) NOT NULL,
        CONSTRAINT fk_personne_professeur FOREIGN KEY (id) REFERENCES Personne (id) ON DELETE CASCADE
    );

-- Table Module
CREATE TABLE
    Module (
        id SERIAL PRIMARY KEY,
        nom_module VARCHAR(255) UNIQUE NOT NULL,
        code_module VARCHAR(255) UNIQUE NOT NULL,
        id_professeur INT NOT NULL,
        CONSTRAINT fk_professeur_module FOREIGN KEY (id_professeur) REFERENCES Professeur (id) ON DELETE SET NULL
    );

-- Table Inscription
CREATE TABLE
    Inscription (
        id SERIAL PRIMARY KEY,
        date_inscription DATE NOT NULL,
        id_etudiant INT NOT NULL,
        id_module INT NOT NULL,
        CONSTRAINT fk_etudiant_inscription FOREIGN KEY (id_etudiant) REFERENCES Etudiant (id) ON DELETE CASCADE,
        CONSTRAINT fk_module_inscription FOREIGN KEY (id_module) REFERENCES Module (id) ON DELETE CASCADE
    );