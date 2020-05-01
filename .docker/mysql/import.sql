CREATE TABLE `estado` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `nome` varchar(255) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `cidade` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `nome` varchar(255) NOT NULL,
                          `estado_id` bigint(20) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKkworrwk40xj58kevvh3evi500` (`estado_id`),
                          CONSTRAINT `FKkworrwk40xj58kevvh3evi500` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `cozinha` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `nome` varchar(30) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `restaurante` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `endereco_bairro` varchar(255) DEFAULT NULL,
                               `endereco_cep` varchar(255) DEFAULT NULL,
                               `endereco_complemento` varchar(255) DEFAULT NULL,
                               `endereco_logradouro` varchar(255) DEFAULT NULL,
                               `endereco_numero` varchar(255) DEFAULT NULL,
                               `nome` varchar(30) NOT NULL,
                               `taxa_frete` decimal(19,2) NOT NULL,
                               `cozinha_id` bigint(20) NOT NULL,
                               `endereco_cidade_id` bigint(20) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FK76grk4roudh659skcgbnanthi` (`cozinha_id`),
                               KEY `FKbc0tm7hnvc96d8e7e2ulb05yw` (`endereco_cidade_id`),
                               CONSTRAINT `FK76grk4roudh659skcgbnanthi` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`),
                               CONSTRAINT `FKbc0tm7hnvc96d8e7e2ulb05yw` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `forma_pagamento` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `descricao` varchar(255) NOT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `permissao` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `descricao` varchar(255) NOT NULL,
                             `nome` varchar(255) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');