set foreign_key_checks = 0;

delete from usuario;
delete from grupo;
delete from permissao;
delete from recorrencia;
delete from produto;
delete from estoque;
delete from compra;
delete from usuario_grupo;
delete from grupo_permissao;
delete from estoque_produto;
delete from compra_produto;
delete from plano;
delete from notificacao;
delete from despesa;
delete from historico_preco;

set foreign_key_checks = 1;

alter table usuario auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table recorrencia auto_increment = 1;
alter table produto auto_increment = 1;
alter table estoque auto_increment = 1;
alter table compra auto_increment = 1;
alter table plano auto_increment = 1;
alter table notificacao auto_increment = 1;
alter table despesa auto_increment = 1;
alter table historico_preco auto_increment = 1;

-- Inserindo permissões básicas
insert into permissao (nome,descricao) values
('GERENCIAR_USUARIOS','Permite gerenciar usuários'),
('GERENCIAR_PRODUTOS','Permite gerenciar produtos'),
('GERENCIAR_ESTOQUE','Permite gerenciar estoque'),
('GERENCIAR_TOTALIZADOR','Permite gerenciar totalizador'),
('VISUALIZAR_RELATORIOS','Permite visualizar relatórios');

-- Inserindo grupo Administrador
insert into grupo (nome) values ('ADMINISTRADOR');

-- Vinculando permissões ao grupo ADMINISTRADOR
insert into grupo_permissao (grupo_id, permissao_id)
select g.id, p.id
from grupo g, permissao p
where g.nome = 'ADMINISTRADOR';

-- Inserindo usuário trial
insert into usuario (nome, email, senha, auth_provider, imagem_url, ativo)
values ('Trial', 'trial@sistema.com', '$2a$12$VhE1kR.G35Xqy6X2IAkZLeGMob1Lz4ET223Q7K3FTweXCuhAtvl22', 'LOCAL', '#', true);

-- Inserindo usuário free
insert into usuario (nome, email, senha, auth_provider, imagem_url, ativo)
values ('Free', 'free@sistema.com', '$2a$12$VhE1kR.G35Xqy6X2IAkZLeGMob1Lz4ET223Q7K3FTweXCuhAtvl22', 'LOCAL', '#', true);

-- Inserindo usuário premium
insert into usuario (nome, email, senha, auth_provider, imagem_url, ativo)
values ('Premium', 'premium@sistema.com', '$2a$12$VhE1kR.G35Xqy6X2IAkZLeGMob1Lz4ET223Q7K3FTweXCuhAtvl22', 'LOCAL', '#', true);

-- Inserindo usuário admin
insert into usuario (nome, email, senha, auth_provider, imagem_url, ativo)
values ('Admin', 'admin@sistema.com', '$2a$12$VhE1kR.G35Xqy6X2IAkZLeGMob1Lz4ET223Q7K3FTweXCuhAtvl22', 'LOCAL', '#', true);

-- Associando o usuário admin ao grupo ADMINISTRADOR
insert into usuario_grupo (usuario_id, grupo_id)
select u.id, g.id
from usuario u, grupo g
where u.email = 'admin@sistema.com' and g.nome = 'ADMINISTRADOR';

-- Inserindo alguns produtos iniciais
insert into produto (nome, categoria, quantidade, valor_unitario) values
('Café', 'Alimento', 3, 12.90),
('Leite', 'Bebida', 2, 4.75),
('Arroz', 'Alimento', 3, 21.50);

insert into compra (usuario_id, data_compra, valor_total)
select
    u.id,
    now(),
    coalesce((select sum(p.valor_unitario * p.quantidade) from produto p), 0)
from usuario u
where u.email = 'admin@sistema.com';

insert into compra (usuario_id, data_compra, valor_total)
select
    u.id,
    now(),
    coalesce((select sum(p.valor_unitario * p.quantidade) from produto p), 0)
from usuario u
where u.email = 'premium@sistema.com';

insert into compra_produto (compra_id, produto_id)
select c.id, p.id from compra c, produto p;

-- Inserindo estoque padrão e vinculando produtos
insert into estoque (quantidade, limite_estoque) values (500, 100);

insert into estoque_produto (estoque_id, produto_id)
select e.id, p.id from estoque e, produto p;

-- Inserindo tipo de recorrência mensal e anual
insert into recorrencia (tipo, intervalo, data_inicio) values
('MENSAL', 1, now()),
('ANUAL', 12, now());

-- Plano padrão para usuário premium
-- Plano padrão para usuário trial
-- Plano padrão para usuário free
insert into plano (usuario_id, nome, data_inicio, data_fim)
select
    u.id,
    case
        when u.email = 'premium@sistema.com' then 'premium'
        when u.email = 'admin@sistema.com' then 'premium'
        when u.email = 'trial@sistema.com' then 'trial'
        when u.email = 'free@sistema.com' then 'free'
        else 'indefinido'
    end as nome,
    now() as data_inicio,
    case
        when u.email = 'premium@sistema.com' then date_add(now(), interval 30 day)
        when u.email = 'admin@sistema.com' then date_add(now(), interval 30 day)
        when u.email = 'trial@sistema.com' then date_add(now(), interval 7 day)
        when u.email = 'free@sistema.com' then now()
        else now()
    end as data_fim
from usuario u
where u.email in ('premium@sistema.com', 'trial@sistema.com', 'free@sistema.com','admin@sistema.com');

-- Notificação de boas-vindas
insert into notificacao (usuario_id, tipo, descricao)
select id, 'INICIAL', 'Bem-vindo ao sistema!' from usuario where email = 'admin@sistema.com';
