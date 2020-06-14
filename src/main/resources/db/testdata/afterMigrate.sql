insert into cozinha (id, nome) values
(1, 'Tailandesa'),
(2, 'Indiana'),
(3, 'Argentina'),
(4, 'Brasileira');

insert into estado (id, nome) values
(1, 'Minas Gerais'),
(2, 'São Paulo'),
(3, 'Ceará');

insert into cidade (id, nome, estado_id) values
(1, 'Uberlândia', 1),
(2, 'Belo Horizonte', 1),
(3, 'São Paulo', 2),
(4, 'Campinas', 2),
(5, 'Fortaleza', 3);

insert into endereco(id, endereco_cidade_id, cep, logradouro, numero, bairro) values (1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_id) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, 1, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values
(2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, 1, 1),
(3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, 1, 1),
(4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, 1, 1),
(5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, 1, 1),
(6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, 1, 1);

insert into forma_pagamento (id, descricao) values
(1, 'Cartão de crédito'),
(2, 'Cartão de débito'),
(3, 'Dinheiro');

insert into permissao (id, nome, descricao) values
(1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'),
(2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into usuario (id, nome, email, senha, data_cadastro, data_atualizacao) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp, utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp, utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp, utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp, utc_timestamp),
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp, utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values
('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1),
('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1),
('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2),
('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3),
('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3),
('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4),
('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4),
('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5),
('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_id, status, data_criacao, subtotal, taxa_frete, valor_total) values
(1, uuid(), 1, 1, 1, 1, 'CRIADO', utc_timestamp, 298.90, 10, 308.90),
(2, uuid(), 4, 1, 2, 1, 'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values
(1, 1, 1, 1, 78.9, 78.9, null),
(2, 1, 2, 2, 110, 220, 'Menos picante, por favor'),
(3, 2, 6, 1, 79, 79, 'Ao ponto');