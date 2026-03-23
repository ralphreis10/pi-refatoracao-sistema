CREATE DATABASE IF NOT EXISTS Sistema;
USE Sistema;

CREATE TABLE IF NOT EXISTS Raca (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Personagem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE,
    vida INT NOT NULL,
    raca_id INT NOT NULL,
    idade INT NOT NULL,
    CONSTRAINT fk_personagem_raca FOREIGN KEY (raca_id) REFERENCES Raca(id)
);

INSERT IGNORE INTO Raca (id, nome) VALUES
(1, 'Anão'),
(2, 'Elfo'),
(3, 'Bruxo'),
(4, 'Humano');

INSERT IGNORE INTO Personagem (id, nome, vida, raca_id, idade) VALUES
(1, 'Geralt', 150, 3, 95),
(2, 'Ciri', 120, 4, 21),
(3, 'Thorin', 180, 1, 195),
(4, 'Legolas', 140, 2, 2931);
