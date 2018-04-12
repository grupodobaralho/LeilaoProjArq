-- Geracao de Modelo fisico
-- Sql ANSI 2003 - brModelo.



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
