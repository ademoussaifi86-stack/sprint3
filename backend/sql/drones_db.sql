-- Base de données du projet Gestion des Drones (MySQL)
-- Import : mysql -u root -p < drones_db.sql   (ou via phpMyAdmin / MySQL Workbench)

CREATE DATABASE IF NOT EXISTS drones_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE drones_db;

DROP TABLE IF EXISTS drone;
DROP TABLE IF EXISTS type_drone;

CREATE TABLE type_drone (
  id_type  BIGINT NOT NULL AUTO_INCREMENT,
  nom_type VARCHAR(255),
  PRIMARY KEY (id_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE drone (
  id_drone      BIGINT NOT NULL AUTO_INCREMENT,
  nom_drone     VARCHAR(255),
  marque_drone  VARCHAR(255),
  prix_drone    DOUBLE,
  date_creation DATE,
  id_type       BIGINT,
  PRIMARY KEY (id_drone),
  CONSTRAINT fk_drone_type FOREIGN KEY (id_type) REFERENCES type_drone (id_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO type_drone (nom_type) VALUES
  ('Quadricoptère'),
  ('Hexacoptère'),
  ('Octocoptère'),
  ('Aile fixe'),
  ('FPV Racing'),
  ('Agricole'),
  ('Photographie'),
  ('Industriel');

INSERT INTO drone (nom_drone, marque_drone, prix_drone, date_creation, id_type) VALUES
  ('DJI Mavic 3',   'DJI',    9000,  '2020-01-14', 7),
  ('Parrot Anafi',  'Parrot', 3500,  '2021-02-20', 1),
  ('DJI Agras T40', 'DJI',    45000, '2022-03-10', 6);
