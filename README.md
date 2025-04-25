# **Gerenciamento de Despesas e Compras Familiares**

Este sistema foi desenvolvido para facilitar o controle financeiro familiar, proporcionando ao usuário uma visão clara e detalhada sobre seus gastos mensais.

Com ele, é possível cadastrar o salário e gerenciar despesas fixas ou pontuais, editá-las, excluí-las e marcar como pagas. O sistema calcula o saldo restante com base no salário e nas despesas pagas, permitindo melhor planejamento financeiro.

Além disso, o sistema envia notificações para lembrar o usuário sobre contas próximas do vencimento.

Também está disponível um módulo de **lista de compras** com totalizador, que permite cadastrar produtos e estimar o valor a ser pago no caixa. Isso ajuda a evitar cobranças indevidas, comparando os valores registrados com os do sistema do estabelecimento.

O sistema conta com um totalizador de compras mensais, onde o usuário poderá cadastrar itens da sua lista de compras e totalizar o quanto deverá pagar no caixa, evitando pagar a mais por erros de conferência na passagem dos produtos no caixa.  
Assim, o usuário pode conferir o valor do totalizador com o valor do caixa e informar ao atendente caso haja um erro nos valores.
A lista de compras pode ser transformada em um **estoque**, permitindo o controle de produtos disponíveis em casa. O sistema permite definir limites de estoque e envia alertas quando os itens estiverem acabando. Também sugere listas de compras com base no que está em falta.

Com o tempo, o sistema registra o histórico de preços dos produtos e calcula o percentual de economia ou prejuízo mês a mês. Todas essas informações são apresentadas em um dashboard interativo com gráficos.

A lista criada em um momento de compras poderá ser salva como estoque, pois a lista de compras no totalizador será apagada após 24 horas.  
O usuário poderá salvar a lista e editar itens, adicionar, remover e definir um limite de estoque para os itens. Assim, o sistema poderá notificar o usuário quando algum item estiver acabando ou já estiver em falta.  
O usuário também poderá usar o estoque como lista de compras para o mês que desejar. O sistema sugerirá uma lista de compras com itens faltantes no estoque.

Ao longo do uso da lista de compras como estoque e atualização dos valores dos produtos, o sistema exibirá abaixo de cada item um percentual de economia ou prejuízo no mês atual das compras que o usuário realizar.  
O usuário poderá ver um dashboard com todas as suas despesas, percentuais e valores economizados em gráficos.

## **Tecnologias utilizadas:**
- **Backend:** Java, Spring Boot
- **Banco de Dados:** MySQL
- **Frontend:** Thymeleaf
- **Autenticação:** OAuth2 (Login com Google)
- **Versão de controle:** Git
- **Gerenciamento de migrações de banco:** Flyway

## 🛠️ **Funcionalidades**
### 1. **Gestão de Usuários:**
- Cadastro, login, logout.
- Autenticação com Google via OAuth2.
- Assinatura (trial, free, premium)

### 2. **Gestão de Despesas:**
- Cadastro de despesas.
- Edição e exclusão de despesas.
- Filtros: por vencimento, pago/não pago, recorrente.
- Alertas de despesas prestes a vencer.

### 3. **Gestão de Compras:**
- Cadastro de compras.
- Totalizador de lista de compras com valor total e percentual de economia.
- Armazenamento em estoque.

### 4. **Gestão de Estoque:**
- Edição, exclusão e adição de item no estoque.
- Controle de limite de estoque
- Alertas de estoque baixo.

### 5. **Notificações:**
- Notificação para despesas prestes a vencer e estoque baixo.

### 6. **Gráficos:**
- Despesas.
- Economia nas compras.

---

## 📜 **Requisitos**

### Funcionais:
- Cadastro, autenticação e assinatura de usuários.
- CRUD completo para despesas e compras.
- Alerta de vencimento de despesas.
- Totalizador de compras com validade, estoque e percentual de economia.
- Sugestões de compras baseadas no estoque.
- Gráficos de despesas e economia.

### Não Funcionais:
- Sistema responsivo para mobile e desktop.
- OAuth como protocolo de segurança.
- Migrações de banco com Flyway.

---

## 📊 **Diagrama:**

### **Diagrama de Casos de Uso**
```mermaid
graph LR
   A[Usuário] --> B[Cadastrar/Logar]
   A --> C[Cadastrar/Editar/Excluir Despesas]
   A --> D[Cadastrar/Editar/Excluir Compras]
   A --> E[Cadastrar/Editar/Excluir Estoque]
   A --> F[Ver Totalizador de Compras]
   A --> G[Ver Percentual de Economia nos Itens do Totalizador]
   A --> H[Salvar Listas de Compras no Estoque]
   A --> I[Ser Notificado sobre Despesas Vencidas e Estoque Baixo]
   A --> J[Ver Sugestões de Compras para Itens em Estoque Baixo]
   A --> K[Usar Estoque como Lista de Compras]
   A --> L[Assinar um Plano]

```
### **Diagrama de Classes**
```mermaid
classDiagram
   class Usuario {
      +Long id
      +String nome
      +String email
      +String senha
      +String authProvider
      +Set<Grupo> grupos
      +OffsetDateTime dataCriacao
      +OffsetDateTime dataAtualizacao
   }

   class Grupo {
      +Long id
      +String nome
      +Set<Permissao> permissoes
      +OffsetDateTime dataCriacao
      +OffsetDateTime dataAtualizacao
   }

   class Permissao {
      +Long id
      +String descricao
      +OffsetDateTime dataCriacao
      +OffsetDateTime dataAtualizacao
   }

   class Noticicacao {
      +Long id
      +Usuario usuario
      +String tipo
      +String descricao
      +OffsetDateTime dataCriacao
      +OffsetDateTime dataAtualizacao
   }

   class Despesa {
      +Long id
      +Usuario usuario
      +String nome
      +BigDecimal valor
      +LocalDate dataVencimento
      +LocalDate dataPagamento
      +boolean statusPagamento
      +Recorrencia recorrencia
      +OffsetDateTime dataCriacao
      +OffsetDateTime dataAtualizacao
   }

   class Recorrencia {
      +Long id
      +String tipo
      +Integer intervalo
      +OffsetDateTime dataInicio
      +OffsetDateTime dataFim
   }

   class Produto {
      +Long id
      +String nome
      +String categoria
      +int quantidade
      +BigDecimal valorUnitario
      +OffsetDateTime dataCriacao
      +OffsetDateTime dataAtualizacao
   }

   class Compra {
      +Long id
      +Usuario usuario
      +LocalDate data
      +BigDecimal total
      +Set<Produto> itens
   }

   class Estoque {
      +Long id
      +Usuario usuario
      +Integer quantidade
      +Integer limiteEstoque
      +Set<Produto> produtos
      +OffsetDateTime dataCriacao
      +OffsetDateTime dataAtualizacao
   }

   class HistoricoPreco {
      +Long id
      +Produto produto
      +BigDecimal precoAnterior
      +BigDecimal precoAtual
      +double percentual
      +OffsetDateTime dataCriacao
      +OffsetDateTime dataAtualizacao
   }

   class Plano {
      +Long id
      +Usuario usuario
      +String nome
      +BigDecimal valor
      +OffsetDateTime inicio
      +OffsetDateTime fim
   }

   Usuario --> Despesa : possui
   Usuario --> Compra : realiza
   Usuario --> Estoque : gerencia
   Usuario --> Noticicacao : recebe
   Usuario --> Plano : assina
   Usuario --> Grupo : pertence
   Grupo --> Permissao : possui
   Despesa --> Recorrencia : tem
   Compra --> Produto : contem
   Estoque --> Produto : possui
   Produto --> HistoricoPreco : tem
```

### **Diagrama de ER**
```mermaid
erDiagram
   USUARIO {
      BIGINT id
      VARCHAR nome
      VARCHAR email
      VARCHAR senha
      VARCHAR auth_provider
      TIMESTAMP data_criacao
      TIMESTAMP data_atualizacao
   }

   GRUPO {
      BIGINT id
      VARCHAR nome
      TIMESTAMP data_criacao
      TIMESTAMP data_atualizacao
   }

   USUARIO_GRUPO {
      BIGINT usuario_id
      BIGINT grupo_id
   }

   PERMISSAO {
      BIGINT id
      VARCHAR descricao
      TIMESTAMP data_criacao
      TIMESTAMP data_atualizacao
   }

   GRUPO_PERMISSAO {
      BIGINT grupo_id
      BIGINT permissao_id
   }

   DESPESA {
      BIGINT id
      BIGINT usuario_id
      VARCHAR nome
      DECIMAL valor
      TIMESTAMP data_vencimento
      TIMESTAMP data_pagamento
      BOOLEAN status_pagamento
      TIMESTAMP data_cadastro
      TIMESTAMP data_atualizacao
   }

   RECORRENCIA {
      BIGINT id
      BIGINT despesa_id
      VARCHAR tipo
      INT intervalo
      TIMESTAMP data_inicio
      TIMESTAMP data_fim
   }

   PRODUTO {
      BIGINT id
      VARCHAR nome
      VARCHAR categoria
      INT quantidade
      DECIMAL valor_unitario
      TIMESTAMP data_criacao
      TIMESTAMP data_atualizacao
   }

   COMPRA {
      BIGINT id
      BIGINT usuario_id
      TIMESTAMP data_compra
      DECIMAL total
   }

   COMPRA_PRODUTO {
      BIGINT compra_id
      BIGINT produto_id
   }

   ESTOQUE {
      BIGINT id
      BIGINT usuario_id
      INT quantidade
      INT limite_estoque
      TIMESTAMP data_criacao
      TIMESTAMP data_atualizacao
   }

   ESTOQUE_PRODUTO {
      BIGINT estoque_id
      BIGINT produto_id
   }

   HISTORICO_PRECO {
      BIGINT id
      BIGINT produto_id
      DECIMAL preco_anterior
      DECIMAL preco_atual
      DOUBLE percentual
      TIMESTAMP data_cadastro
      TIMESTAMP data_atualizacao
   }

   PLANO {
      BIGINT id
      BIGINT usuario_id
      VARCHAR nome
      DECIMAL valor
      TIMESTAMP data_inicio
      TIMESTAMP data_fim
   }

%% RELACIONAMENTOS

   USUARIO ||--o{ DESPESA: possui
   USUARIO ||--o{ COMPRA: realiza
   USUARIO ||--o| ESTOQUE: gerencia
   USUARIO ||--o| PLANO: assina
   USUARIO ||--o{ USUARIO_GRUPO: participa
   GRUPO  ||--o{ USUARIO_GRUPO: possui
   GRUPO  ||--o{ GRUPO_PERMISSAO: possui
   PERMISSAO ||--o{ GRUPO_PERMISSAO: pertence
   DESPESA ||--o| RECORRENCIA: tem
   COMPRA ||--o{ COMPRA_PRODUTO: contem
   PRODUTO ||--o{ COMPRA_PRODUTO: contem
   ESTOQUE ||--o{ ESTOQUE_PRODUTO: possui
   PRODUTO ||--o{ ESTOQUE_PRODUTO: possui
   PRODUTO ||--o| HISTORICO_PRECO: tem
```


### **Diagrama de Sequência de Autenticação com o Google utilizando OAuth**
```mermaid
sequenceDiagram
    autonumber
    actor U as Usuário
    participant G as Google
    participant S as Sistema
    
    U->>S: Acessa a página de login
    U->>S: Clica em "Login com Google"
    S->>G: Redireciona para o Google OAuth2
    G->>U: Solicita credenciais
    U->>G: Envia credenciais
    G->>S: Retorna token de acesso
    S->>S: Verifica se o usuário existe
    S->>S: Cria nova conta se necessário
    S->>U: Cria sessão e redireciona para a página inicial
    U->>S: Acessa o sistema
```

### **Diagrama de Sequência de Cadastro de Despesa**
```mermaid
sequenceDiagram
   autonumber
   actor U as Usuário
   participant S as Sistema

   U->>S: Acessa "Nova Despesa"
   U->>S: Preenche dados
   alt Recorrente
      U->>S: Define tipo e intervalo de recorrência
   end
   U->>S: Clica em "Salvar"
   S->>S: Valida dados
   alt Válido
      S->>DB: Salva despesa (e recorrência, se houver)
      S->>U: Exibe confirmação
   else Inválido
      S->>U: Exibe erro e solicita correção
   end
```

### **Diagrama de Sequência de Sugestão de Itens**
```mermaid
sequenceDiagram
   autonumber
   actor U as Usuário
   participant S as Sistema
   U->>S: Acessa o totalizador
   S->>DB: Consulta estoque
   DB->>S: retorna os produtos
   loop para cada produto
   S->>S: Verifica se quantidade < limite
   alt Está abaixo
   S->>S: Adiciona à lista de sugestão
   end
   end
   S->>U: Exibe lista de produtos faltantes
```

### **Diagrama de Sequência: Exibir Economia ou Prejuízo**
```mermaid
sequenceDiagram
    autonumber
    actor U as Usuário
    participant S as Sistema

    U->>S: Acessa o totalizador
    S->>DB: Busca produtos comprados
    loop para cada produto
       S->>DB: Busca histórico de preço
       S->>S: Calcula diferença e percentual
    end
    S->>U: Exibe em cada item o percentual de economia/prejuízo
```

### **Diagrama de Sequência: Armazenamento de compras em estoque**
```mermaid
sequenceDiagram
    autonumber
    actor U as Usuário
    participant S as Sistema

    U->>S: Salva itens do totalizador no estoque
    loop para cada item no totalizador
        S->>DB: Salva no estoque
    end
```

## 🧑‍💻 **Tecnologias Usadas**

- **Java 17+**
- **Spring Boot**
- **MySQL**
- **Thymeleaf**
- **Flyway**
- **Spring Security (OAuth2)**
- **Docker**

---

## 🔧 **Instalação**

1. Clone o repositório:
    ```bash
    git clone https://github.com/usuario/sistema-gerenciar.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd gerenciar
    ```

3. Compile o projeto com Maven:
    ```bash
    mvn clean install
    ```

4. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```

5. Acesse a aplicação no navegador em `http://localhost:8080`.


---
