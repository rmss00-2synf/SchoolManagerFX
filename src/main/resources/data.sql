-- Insertion dans la table Personne
INSERT INTO Personne (id,nom, prenom) VALUES
                                          (1,'El Amrani', 'Omar'),
                                          (2,'Benhaddou', 'Fatima'),
                                          (3,'Essadi', 'Youssef'),
                                          (4,'Mouline', 'Rachida'),
                                          (5,'El Fassi', 'Salma');

-- Insertion dans la table Utilisateur
INSERT INTO Utilisateur (id, username, password, role) VALUES
                                                           (1, 'omar.admin', 'admin123', 'admin'),
                                                           (2, 'fatima.secretary', 'secret123', 'secretary');

-- Insertion dans la table Etudiant
INSERT INTO Etudiant (id, matricule, date_naissance, email, promotion) VALUES
                                                                           (3, 'ENSA12345', '2000-03-15', 'youssef.essadi@ensa.ac.ma', 2024),
                                                                           (4, 'ENSA54321', '1999-11-25', 'rachida.mouline@ensa.ac.ma', 2024);

-- Insertion dans la table Professeur
INSERT INTO Professeur (id, specialite) VALUES
                                            (5, 'Informatique'),
                                            (4, 'Mathématiques');

-- Insertion dans la table Module
INSERT INTO Module (id,nom_module, code_module, id_professeur) VALUES
                                                                   (1,'Programmation Orientée Objet', 'POO101', 5),
                                                                   (2,'Analyse Numérique', 'AN102', 4),
                                                                   (3,'Bases de Données Avancées', 'BDA103', 5);

-- Insertion dans la table Inscription
INSERT INTO Inscription (date_inscription, id_etudiant, id_module) VALUES
                                                                       ('2025-01-05', 3, 1), -- Youssef inscrit à POO101
                                                                       ('2025-01-05', 3, 2), -- Youssef inscrit à AN102
                                                                       ('2025-01-05', 4, 1), -- Rachida inscrite à POO101
                                                                       ('2025-01-05', 4, 3); -- Rachida inscrite à BDA103
