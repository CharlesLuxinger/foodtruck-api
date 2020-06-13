CREATE TABLE `estado` (
                          `id` bigint(10) NOT NULL AUTO_INCREMENT,
                          `nome` varchar(60) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `cidade` (
                          `id` bigint(10) NOT NULL AUTO_INCREMENT,
                          `nome` varchar(60) NOT NULL,
                          `estado_id` bigint(10) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKkworrwk40xj58kevvh3evi500` (`estado_id`),
                          CONSTRAINT `FKkworrwk40xj58kevvh3evi500` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `endereco` (
                            `id` bigint(10) NOT NULL AUTO_INCREMENT,
                            `bairro` varchar(100) DEFAULT NULL,
                            `cep` varchar(9) DEFAULT NULL,
                            `complemento` varchar(20) DEFAULT NULL,
                            `logradouro` varchar(100) DEFAULT NULL,
                            `numero` varchar(10) DEFAULT NULL,
                            `endereco_cidade_id` bigint(10) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKckypcp27tyiumw3tijodfj471` (`endereco_cidade_id`),
                            CONSTRAINT `FKckypcp27tyiumw3tijodfj471` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `cozinha` (
                           `id` bigint(10) NOT NULL AUTO_INCREMENT,
                           `nome` varchar(30) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `restaurante` (
                               `id` bigint(10) NOT NULL AUTO_INCREMENT,
                               `nome` varchar(60) DEFAULT NULL,
                               `taxa_frete` decimal(5,2) NOT NULL,
                               `cozinha_id` bigint(10) NOT NULL,
                               `endereco_id` bigint(10) DEFAULT NULL,
                               `data_cadastro` datetime not null,
                               `data_atualizacao` datetime not null,
                               `ativo` tinyint(1) NOT NULL,
                               `aberto` tinyint(1) NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FK76grk4roudh659skcgbnanthi` (`cozinha_id`),
                               KEY `FKnc0aps7r9c6euyqrs6d2v04qa` (`endereco_id`),
                               CONSTRAINT `FK76grk4roudh659skcgbnanthi` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`),
                               CONSTRAINT `FKnc0aps7r9c6euyqrs6d2v04qa` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `forma_pagamento` (
                                   `id` bigint(10) NOT NULL AUTO_INCREMENT,
                                   `descricao` varchar(20) NOT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `permissao` (
                             `id` bigint(10) NOT NULL AUTO_INCREMENT,
                             `descricao` varchar(60) NOT NULL,
                             `nome` varchar(20) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `restaurante_forma_pagamento` (
                                               `restaurante_id` bigint(10) NOT NULL,
                                               `forma_pagamento_id` bigint(10) NOT NULL,
                                               KEY `FK7aln770m80358y4olr03hyhh2` (`forma_pagamento_id`),
                                               KEY `FKa30vowfejemkw7whjvr8pryvj` (`restaurante_id`),
                                               CONSTRAINT `FK7aln770m80358y4olr03hyhh2` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`),
                                               CONSTRAINT `FKa30vowfejemkw7whjvr8pryvj` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `produto` (
                           `id` bigint(10) NOT NULL AUTO_INCREMENT,
                           `ativo` bit(1) NOT NULL,
                           `descricao` varchar(150) NOT NULL,
                           `nome` varchar(60) NOT NULL,
                           `preco` decimal(5,2) NOT NULL,
                           `restaurante_id` bigint(10) NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKb9jhjyghjcn25guim7q4pt8qx` (`restaurante_id`),
                           CONSTRAINT `FKb9jhjyghjcn25guim7q4pt8qx` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `grupo` (
                         `id` bigint(10) NOT NULL AUTO_INCREMENT,
                         `nome` varchar(20) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `grupo_permissao` (
                                   `grupo_id` bigint(10) NOT NULL,
                                   `permissao_id` bigint(10) NOT NULL,
                                   KEY `FKh21kiw0y0hxg6birmdf2ef6vy` (`permissao_id`),
                                   KEY `FKta4si8vh3f4jo3bsslvkscc2m` (`grupo_id`),
                                   CONSTRAINT `FKh21kiw0y0hxg6birmdf2ef6vy` FOREIGN KEY (`permissao_id`) REFERENCES `permissao` (`id`),
                                   CONSTRAINT `FKta4si8vh3f4jo3bsslvkscc2m` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `usuario` (
                           `id` bigint(10) NOT NULL AUTO_INCREMENT,
                           `data_atualizacao` datetime NOT NULL,
                           `data_cadastro` datetime NOT NULL,
                           `email` varchar(100) NOT NULL,
                           `nome` varchar(60) NOT NULL,
                           `senha` varchar(60) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `usuario_grupo` (
                                 `usuario_id` bigint(10) NOT NULL,
                                 `grupo_id` bigint(10) NOT NULL,
                                 KEY `FKk30suuy31cq5u36m9am4om9ju` (`grupo_id`),
                                 KEY `FKdofo9es0esuiahyw2q467crxw` (`usuario_id`),
                                 CONSTRAINT `FKdofo9es0esuiahyw2q467crxw` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
                                 CONSTRAINT `FKk30suuy31cq5u36m9am4om9ju` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE restaurante_usuario_responsavel (
                        restaurante_id bigint NOT NULL,
                        usuario_id bigint NOT NULL,
                        PRIMARY KEY (restaurante_id, usuario_id)
                        KEY `key_restaurante_id` (`restaurante_id`),
                        KEY `key_usuario_id` (`usuario_id`),
                        CONSTRAINT `fk_usuario_id_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
                        CONSTRAINT `fk_grupo_id_grupo` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into endereco(id, endereco_cidade_id, cep, logradouro, numero, bairro) values (1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, status, aberto, endereco_id) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, 1, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, status, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, 1, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, status, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, 1, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, status, aberto) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, 1, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, status, aberto) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, 1, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, status, aberto) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, 1, 1);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total) values
(1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CRIADO', utc_timestamp, 298.90, 10, 308.90),
(2, 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values
(1, 1, 1, 1, 78.9, 78.9, null),
(2, 1, 2, 2, 110, 220, 'Menos picante, por favor'),
(3, 2, 6, 1, 79, 79, 'Ao ponto');

insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into usuario (id, nome, email, senha, data_cadastro, data_atualizacao) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp, utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp, utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp, utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp, utc_timestamp);
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp, utc_timestamp);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);