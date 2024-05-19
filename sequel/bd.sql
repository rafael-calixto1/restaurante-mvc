CREATE TABLE IF NOT EXISTS funcionario(
	id_func SERIAL PRIMARY KEY,
	nome VARCHAR(255),
	senha VARCHAR(255),
	cpf VARCHAR(14),
	dt_nasc DATE,
	cargo VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS cliente(
	id_cliente SERIAL PRIMARY KEY,
	nome VARCHAR(255),
	cpf VARCHAR(14)
);

CREATE TABLE IF NOT EXISTS pedido(
	id_pedido SERIAL PRIMARY KEY,
	id_cliente INTEGER NOT NULL,
	id_func INTEGER NOT NULL,
	data_pedido DATE,
	valor_total NUMERIC(10,2),
	pagamento VARCHAR(255),
	status VARCHAR(20),
	
	CONSTRAINT fk_func FOREIGN KEY (id_func) 
	REFERENCES funcionario (id_func),
	CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
	REFERENCES cliente (id_cliente)
);

CREATE TABLE IF NOT EXISTS prato(
	id_prato SERIAL PRIMARY KEY,
	nome VARCHAR(255),
	preco NUMERIC(10,2),
	descricao VARCHAR(255),
	avaliacao NUMERIC(1,1)
);

CREATE TABLE IF NOT EXISTS itemPedido(
	id_itemPedido SERIAL PRIMARY KEY,
	id_pedido INTEGER NOT NULL,
	id_prato INTEGER NOT NULL,
	qtd INTEGER, 
	CONSTRAINT fk_prato FOREIGN KEY (id_prato)
	REFERENCES prato (id_prato),
	CONSTRAINT fk_pedido FOREIGN KEY (id_pedido)
	REFERENCES pedido (id_pedido)
);


