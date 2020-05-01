use foodtruck;

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

CREATE TABLE `endereco` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `endereco_bairro` varchar(255) DEFAULT NULL,
                            `endereco_cep` varchar(255) DEFAULT NULL,
                            `endereco_complemento` varchar(255) DEFAULT NULL,
                            `endereco_logradouro` varchar(255) DEFAULT NULL,
                            `endereco_numero` varchar(255) DEFAULT NULL,
                            `endereco_cidade_id` bigint(20) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKckypcp27tyiumw3tijodfj471` (`endereco_cidade_id`),
                            CONSTRAINT `FKckypcp27tyiumw3tijodfj471` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `cozinha` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `nome` varchar(30) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `restaurante` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `nome` varchar(255) DEFAULT NULL,
                               `taxa_frete` decimal(19,2) NOT NULL,
                               `cozinha_id` bigint(20) NOT NULL,
                               `endereco_id` bigint(20) DEFAULT NULL,
                               `data_cadastro` datetime not null,
                               `data_atualizacao` datetime not null,
                               PRIMARY KEY (`id`),
                               KEY `FK76grk4roudh659skcgbnanthi` (`cozinha_id`),
                               KEY `FKnc0aps7r9c6euyqrs6d2v04qa` (`endereco_id`),
                               CONSTRAINT `FK76grk4roudh659skcgbnanthi` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`),
                               CONSTRAINT `FKnc0aps7r9c6euyqrs6d2v04qa` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`)
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

CREATE TABLE `restaurante_forma_pagamento` (
                                               `restaurante_id` bigint(20) NOT NULL,
                                               `forma_pagamento_id` bigint(20) NOT NULL,
                                               KEY `FK7aln770m80358y4olr03hyhh2` (`forma_pagamento_id`),
                                               KEY `FKa30vowfejemkw7whjvr8pryvj` (`restaurante_id`),
                                               CONSTRAINT `FK7aln770m80358y4olr03hyhh2` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`),
                                               CONSTRAINT `FKa30vowfejemkw7whjvr8pryvj` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `produto` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `ativo` bit(1) NOT NULL,
                           `descricao` varchar(255) NOT NULL,
                           `nome` varchar(255) NOT NULL,
                           `preco` decimal(19,2) NOT NULL,
                           `restaurante_id` bigint(20) NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKb9jhjyghjcn25guim7q4pt8qx` (`restaurante_id`),
                           CONSTRAINT `FKb9jhjyghjcn25guim7q4pt8qx` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `grupo` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `nome` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `grupo_permissao` (
                                   `grupo_id` bigint(20) NOT NULL,
                                   `permissao_id` bigint(20) NOT NULL,
                                   KEY `FKh21kiw0y0hxg6birmdf2ef6vy` (`permissao_id`),
                                   KEY `FKta4si8vh3f4jo3bsslvkscc2m` (`grupo_id`),
                                   CONSTRAINT `FKh21kiw0y0hxg6birmdf2ef6vy` FOREIGN KEY (`permissao_id`) REFERENCES `permissao` (`id`),
                                   CONSTRAINT `FKta4si8vh3f4jo3bsslvkscc2m` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `usuario` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `data_atualizacao` datetime NOT NULL,
                           `data_cadastro` datetime NOT NULL,
                           `email` varchar(255) NOT NULL,
                           `nome` varchar(255) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `usuario_grupo` (
                                 `usuario_id` bigint(20) NOT NULL,
                                 `grupo_id` bigint(20) NOT NULL,
                                 KEY `FKk30suuy31cq5u36m9am4om9ju` (`grupo_id`),
                                 KEY `FKdofo9es0esuiahyw2q467crxw` (`usuario_id`),
                                 CONSTRAINT `FKdofo9es0esuiahyw2q467crxw` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
                                 CONSTRAINT `FKk30suuy31cq5u36m9am4om9ju` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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

insert into endereco(id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_id) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);