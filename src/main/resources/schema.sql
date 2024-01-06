

CREATE TABLE cliente (
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE produto (
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    valor_unitario NUMERIC(20,2)
);

CREATE TABLE pedido (
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    cliente_id INTEGER REFERENCES cliente (id),
    data_pedido TIMESTAMP,
    valor_total NUMERIC(20,2)
);

CREATE TABLE item_pedido (
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    pedido_id INTEGER REFERENCES pedido (id),
    produto_id INTEGER REFERENCES produto (id),
    quantidade INTEGER NOT NULL
);