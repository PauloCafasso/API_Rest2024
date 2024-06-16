DROP TABLE IF EXISTS Coquetel;
DROP TABLE IF EXISTS Preparo;
DROP TABLE IF EXISTS Copo;
DROP TABLE IF EXISTS Guarnicao;

CREATE TABLE Preparo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    preparo VARCHAR(255) NOT NULL
);

CREATE TABLE Copo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE Guarnicao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_guarnicao VARCHAR(255) NOT NULL
);

CREATE TABLE Coquetel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    lista_ingredientes VARCHAR(300) NOT NULL,
    preparo_id BIGINT,
    guarnicao_id BIGINT,
    copo_id BIGINT,
    FOREIGN KEY (preparo_id) REFERENCES Preparo(id),
    FOREIGN KEY (guarnicao_id) REFERENCES Guarnicao(id),
    FOREIGN KEY (copo_id) REFERENCES Copo(id)
);

INSERT INTO Copo (nome, descricao) VALUES ('Taça Martini', 'Taça para coquetéis da fámilia Martini e outros classicos como Cosmopolitan, pode ser substituido pela Taça Coupe');
INSERT INTO Copo (nome, descricao) VALUES ('Copo Collins', 'Copo alto e cilindrico para coquetéis longos como LongIsland Iced Tea');
INSERT INTO Copo (nome, descricao) VALUES ('Copo Baixo', 'Copo para coquetéis médios ou degustação de destilados tanto puros como sobre gelo');

INSERT INTO Guarnicao (tipo_guarnicao) VALUES ('Azeitona');
INSERT INTO Guarnicao (tipo_guarnicao) VALUES ('Cereja de Maraschino');
INSERT INTO Guarnicao (tipo_guarnicao) VALUES ('Rodela de limão');

INSERT INTO Preparo (preparo) VALUES ('Mexido');
INSERT INTO Preparo (preparo) VALUES ('Batido');
INSERT INTO Preparo (preparo) VALUES ('Montado');
INSERT INTO Preparo (preparo) VALUES ('Throw');

INSERT INTO Coquetel (nome, descricao, lista_ingredientes, preparo_id, guarnicao_id, copo_id)
VALUES ('Martini', 'Coquetel clássico à base de gin e vermute', 'Gin, Vermute seco', 1, 1, 1);
INSERT INTO Coquetel (nome, descricao, lista_ingredientes, preparo_id, guarnicao_id, copo_id)
VALUES ('Mojito', 'Coquetel cubano refrescante à base de rum, limão e hortelã', 'Rum, Suco de limão, Hortelã, Água com gás', 2, 2, 2);
INSERT INTO Coquetel (nome, descricao, lista_ingredientes, preparo_id, guarnicao_id, copo_id)
VALUES ('Caipirinha', 'Coquetel brasileiro a base de cachaça, limão e açúcar', 'Cachaça, Meio limão, Açúcar', 3, 3, 3);