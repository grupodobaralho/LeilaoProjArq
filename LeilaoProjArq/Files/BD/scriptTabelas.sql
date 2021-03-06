-- Geracao de Modelo fisico
-- Sql ANSI 2003 - brModelo.
-- Israel Deorce, Hercilio Ortiz e Pedro Ortiz. 16-04-2018

DROP TABLE Lance;
DROP TABLE Produto;

CREATE TABLE Lance (
id_lance NUMERIC(8) PRIMARY KEY,
id_produto NUMERIC(8),
valor NUMERIC(10)
);

CREATE TABLE Produto (
id_produto NUMERIC(8) PRIMARY KEY,
nome VARCHAR(100),
valor_inicial NUMERIC(10),
maiorLance NUMERIC(10),
descricao VARCHAR(200)
);

ALTER TABLE Lance ADD FOREIGN KEY(id_produto) REFERENCES Produto (id_produto);
ALTER TABLE Lance MODIFY valor DECIMAL(10,2);
ALTER TABLE Produto MODIFY valor_inicial DECIMAL(10,2);
ALTER TABLE Produto MODIFY maiorLance DECIMAL(10,2);
