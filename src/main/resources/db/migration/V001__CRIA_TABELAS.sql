create table usuario (
    id bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(100) not null unique,
    senha varchar(255) not null,
    auth_provider varchar(30) not null,
    data_criacao datetime default current_timestamp,
    data_atualizacao datetime default current_timestamp on update current_timestamp,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table grupo (
    id bigint not null auto_increment,
    nome varchar(60) not null unique,
    data_criacao datetime default current_timestamp,
    data_atualizacao datetime default current_timestamp on update current_timestamp,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table recorrencia (
    id bigint not null auto_increment,
    tipo varchar(60) not null,
    intervalo smallint not null,
    data_inicio datetime not null,
    data_fim datetime,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table permissao (
    id bigint not null auto_increment,
    descricao varchar(100) not null unique,
    data_criacao datetime default current_timestamp,
    data_atualizacao datetime default current_timestamp on update current_timestamp,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table produto (
    id bigint not null auto_increment,
    nome varchar(60) not null unique,
    categoria varchar(30) not null,
    quantidade int not null,
    valor_unitario decimal(10,2) not null,
    data_criacao datetime default current_timestamp,
    data_atualizacao datetime default current_timestamp on update current_timestamp,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table estoque (
    id bigint not null auto_increment,
    quantidade int not null,
    limite_estoque int not null,
    data_criacao datetime default current_timestamp,
    data_atualizacao datetime default current_timestamp on update current_timestamp,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table compra (
    id bigint not null auto_increment,
    usuario_id bigint not null,
    data_compra datetime not null,
    valor_total decimal(10,2) not null,
    primary key (id),
    constraint fk_compra_usuario foreign key (usuario_id) references usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table usuario_grupo (
    usuario_id bigint not null,
    grupo_id bigint not null,
    primary key (usuario_id, grupo_id),
    constraint fk_usuario_grupo_usuario foreign key (usuario_id) references usuario(id),
    constraint fk_usuario_grupo_grupo foreign key (grupo_id) references grupo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table grupo_permissao (
    grupo_id bigint not null,
    permissao_id bigint not null,
    primary key (grupo_id, permissao_id),
    constraint fk_grupo_permissao_grupo foreign key (grupo_id) references grupo(id),
    constraint fk_grupo_permissao_permissao foreign key (permissao_id) references permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table estoque_produto (
    estoque_id bigint not null,
    produto_id bigint not null,
    primary key (estoque_id, produto_id),
    constraint fk_estoque_produto_estoque foreign key (estoque_id) references estoque(id),
    constraint fk_estoque_produto_produto foreign key (produto_id) references produto(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table compra_produto (
    compra_id bigint not null,
    produto_id bigint not null,
    primary key (compra_id, produto_id),
    constraint fk_compra_produto_compra foreign key (compra_id) references compra(id),
    constraint fk_compra_produto_produto foreign key (produto_id) references produto(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table plano (
    id bigint not null auto_increment,
    usuario_id bigint not null,
    nome varchar(100) not null,
    data_inicio datetime not null,
    data_fim datetime not null,
    primary key (id),
    constraint fk_plano_usuario foreign key (usuario_id) references usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table notificacao (
    id bigint not null auto_increment,
    usuario_id bigint not null,
    tipo varchar(60) not null,
    descricao varchar(100) not null,
    data_criacao datetime default current_timestamp,
    data_atualizacao datetime default current_timestamp on update current_timestamp,
    primary key (id),
    constraint fk_notificacao_usuario foreign key (usuario_id) references usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table despesa (
    id bigint not null auto_increment,
    recorrencia_id bigint not null,
    usuario_id bigint not null,
    nome varchar(60) not null,
    valor decimal(10,2) not null,
    data_vencimento datetime not null,
    data_pagamento datetime,
    status_pagamento boolean,
    data_criacao datetime default current_timestamp,
    data_atualizacao datetime default current_timestamp on update current_timestamp,
    primary key (id),
    constraint fk_despesa_usuario foreign key (usuario_id) references usuario(id),
    constraint fk_despesa_recorrencia foreign key (recorrencia_id) references recorrencia(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table historico_preco (
    id bigint not null auto_increment,
    produto_id bigint not null,
    preco_anterior decimal(10,2) not null,
    preco_atual decimal(10,2) not null,
    percentual decimal(5,2) not null,
    data_criacao datetime default current_timestamp,
    data_atualizacao datetime default current_timestamp on update current_timestamp,
    primary key (id),
    constraint fk_historico_preco_produto foreign key (produto_id) references produto(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;