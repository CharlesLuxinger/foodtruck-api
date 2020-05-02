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
                            `endereco_bairro` varchar(100) DEFAULT NULL,
                            `endereco_cep` varchar(9) DEFAULT NULL,
                            `endereco_complemento` varchar(20) DEFAULT NULL,
                            `endereco_logradouro` varchar(100) DEFAULT NULL,
                            `endereco_numero` varchar(10) DEFAULT NULL,
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
                               `data_cadastro` datetime NOT NULL,
                               `data_atualizacao` datetime NOT NULL,
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
                           `email` varchar(20) NOT NULL,
                           `nome` varchar(60) NOT NULL,
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

create table pedido (
                        `id` bigint(10) NOT NULL AUTO_INCREMENT,
                        `subtotal` decimal(10,2) NOT NULL,
                        `taxa_frete` decimal(5,2) NOT NULL,
                        `valor_total` decimal(10,2) NOT NULL,
                        `restaurante_id` bigint NOT NULL,
                        `usuario_cliente_id` bigint NOT NULL,
                        `forma_pagamento_id` bigint NOT NULL,
                        `endereco_id` bigint(10) DEFAULT NULL,
                        `status` varchar(10) NOT NULL,
                        `data_criacao` datetime NOT NULL,
                        `data_confirmacao` datetime null,
                        `data_cancelamento` datetime null,
                        `data_entrega` datetime null,
                        PRIMARY KEY (id),
                        CONSTRAINT `fk_pedido_restaurante` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`),
                        CONSTRAINT `fk_pedido_usuario_cliente` FOREIGN KEY (`usuario_cliente_id`) REFERENCES `usuario` (`id`),
                        CONSTRAINT `fk_pedido_forma_pagamento` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

create table item_pedido (
                             `id` bigint NOT NULL auto_increment,
                             `quantidade` smallint(6) NOT NULL,
                             `preco_unitario` decimal(10,2) NOT NULL,
                             `preco_total` decimal(10,2) NOT NULL,
                             `observacao` varchar(255) null,
                             `pedido_id` bigint NOT NULL,
                             `produto_id` bigint NOT NULL,
                             PRIMARY KEY (id),
                             unique KEY `uk_item_pedido_produto` (`pedido_id`, `produto_id`),
                             CONSTRAINT `fk_item_pedido_pedido` FOREIGN KEY (`pedido_id`) REFERENCES pedido (`id`),
                             CONSTRAINT `fk_item_pedido_produto` FOREIGN KEY (`produto_id`) REFERENCES produto (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;