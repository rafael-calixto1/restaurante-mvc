CREATE TABLE IF NOT EXISTS funcionario(
	id_func SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	cpf VARCHAR(14) UNIQUE NOT NULL,
	dt_nasc DATE NOT NULL,
	cargo VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente(
	id_cliente SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	cpf VARCHAR(14) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS pedido(
	id_pedido SERIAL PRIMARY KEY,
	id_cliente INTEGER NOT NULL,
	id_func   INTEGER NOT NULL,
	data_pedido DATE NOT NULL,
	valor_total NUMERIC NOT NULL,
	pagamento VARCHAR(255) NOT NULL,
	status VARCHAR(20) NOT NULL,
	CONSTRAINT fk_func FOREIGN KEY (id_func) 
	REFERENCES funcionario (id_func),
	CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
	REFERENCES cliente (id_cliente)
);

CREATE TABLE IF NOT EXISTS prato(
	id_prato SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	preco NUMERIC NOT NULL,
	descricao VARCHAR(255),
	avaliacao NUMERIC
);

CREATE TABLE IF NOT EXISTS item_pedido(
	id_item_pedido SERIAL PRIMARY KEY,
	id_pedido INTEGER NOT NULL,
	id_prato INTEGER NOT NULL,
	qtd INTEGER NOT NULL CHECK (qtd > 0),
	CONSTRAINT fk_prato FOREIGN KEY (id_prato)
	REFERENCES prato (id_prato),
	CONSTRAINT fk_pedido FOREIGN KEY (id_pedido)
	REFERENCES pedido (id_pedido)
	ON DELETE CASCADE
);