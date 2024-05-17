CREATE TABLE IF NOT EXISTS funcionario(
	id_func SERIAL PRIMARY KEY,
	nome VARCHAR(255),
	senha VARCHAR(8),
	cpf INTEGER,
	dt_nasc DATE,
	cargo VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cliente(
	id_cliente SERIAL PRIMARY KEY,
	cpf INTEGER,
	nome VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS pedido(
	id_pedido SERIAL PRIMARY KEY,
	id_cliente INTEGER,
	id_func INTEGER,
	CONSTRAINT fk_func FOREIGN KEY (id_func) 
	REFERENCES funcionario (id_func),
	CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
	REFERENCES cliente (id_cliente)
);

CREATE TABLE IF NOT EXISTS prato(
	id_prato SERIAL PRIMARY KEY,
	nome VARCHAR(255),
	preco INTEGER,
	descricao VARCHAR(255),
	avaliacao NUMERIC (10,2)
);

CREATE TABLE IF NOT EXISTS itemPedido(
	id_itemPedido SERIAL PRIMARY KEY,
	qtd INTEGER, 
	id_prato INTEGER,
	id_pedido INTEGER,
	CONSTRAINT fk_prato FOREIGN KEY (id_prato)
	REFERENCES prato (id_prato),
	CONSTRAINT fk_pedido FOREIGN KEY (id_pedido)
	REFERENCES pedido (id_pedido)
);



