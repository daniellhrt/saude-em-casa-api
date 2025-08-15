# 🩺 Saúde em Casa API

<img src="logo.png" alt="Logo Saúde em Casa" width="250" />

Uma plataforma de agendamento de serviços de enfermagem domiciliar, conectando clientes a profissionais de saúde de forma segura e eficiente, no estilo Uber/iFood.

---

### 🚀 Stack de Tecnologias

Esta aplicação foi desenvolvida utilizando as seguintes tecnologias:

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3+
* **Banco de Dados:** H2 Database (em memória para desenvolvimento)
* **Segurança:** Spring Security, JWT (JSON Web Tokens)
* **Build Tool:** Gradle
* **Ambiente de Desenvolvimento:** IntelliJ IDEA

---

### ✅ Sprint 1: Autenticação e Segurança (Concluída)

Nesta primeira fase, o foco foi estabelecer uma base sólida e segura para a API. As seguintes funcionalidades foram implementadas:

* **Sistema de Autenticação:** Registro de novos usuários (Clientes e Profissionais) e login.
* **Segurança com JWT:** Implementação de um fluxo de autenticação via JWT, garantindo que apenas usuários autenticados possam acessar rotas protegidas.
* **Mapeamento de Dados:** Modelagem e persistência de dados dos usuários com JPA e Hibernate.
* **Boas Práticas:** Utilização de DTOs (Records), tratamento de exceções global e uma arquitetura em camadas.

---
### ✅ Sprint 2: Gestão de Serviços e Agendamentos (Concluída)

Nesta sprint, o foco foi na implementação da lógica central da plataforma, permitindo que clientes solicitem serviços e profissionais gerenciem o fluxo de atendimento. As seguintes funcionalidades foram implementadas:

* **Modelagem de Dados:** Criação das entidades `Servico` e `Agendamento` para representar o catálogo de serviços e as solicitações de atendimento.
* **Fluxo de Agendamento:** Lógica completa para criar, aceitar e encerrar agendamentos. A entidade `Agendamento` agora possui um ciclo de vida (`PENDENTE`, `CONFIRMADO`, `CONCLUIDO`).
* **API de Listagem:** Endpoint para listar todos os serviços disponíveis na plataforma.
* **API de Gestão:** Endpoints para que clientes solicitem agendamentos e profissionais aceitem ou encerrem o atendimento.
* **Boas Práticas:** Uso de DTOs de Requisição e Resposta para desacoplar a API das entidades do banco de dados, resolvendo problemas de serialização e garantindo um fluxo de dados limpo.

---

### 🔑 Endpoints da API

#### Autenticação
* `POST /auth/register`
    * **Descrição:** Registra um novo `CLIENTE` ou `PROFISSIONAL`.
    * **Exige autenticação:** Não
* `POST /auth/login`
    * **Descrição:** Autentica um usuário e retorna um JWT para uso nas próximas requisições.
    * **Exige autenticação:** Não

#### Serviços e Agendamentos
* `GET /servicos`
    * **Descrição:** Retorna a lista completa de serviços disponíveis.
    * **Exige autenticação:** Sim
* `GET /agendamentos/pendentes`
    * **Descrição:** Retorna a lista de todos os agendamentos com o status `PENDENTE`.
    * **Exige autenticação:** Sim
* `POST /agendamentos`
    * **Descrição:** Cria um novo agendamento com o status `PENDENTE`.
    * **Exige autenticação:** Sim
* `PATCH /agendamentos/aceitar/{id}`
    * **Descrição:** Altera o status de um agendamento para `CONFIRMADO` e associa um profissional.
    * **Exige autenticação:** Sim
* `PATCH /agendamentos/encerrar/{id}`
    * **Descrição:** Altera o status de um agendamento para `CONCLUIDO`.
    * **Exige autenticação:** Sim

---

### ⚙️ Como Rodar o Projeto

1.  **Clone o repositório:** `git clone [URL_DO_REPOSITORIO]`
2.  **Abra o projeto:** Importe o projeto no IntelliJ IDEA como um projeto Gradle.
3.  **Configure o arquivo `application.properties`:** Adicione a chave secreta para o JWT e as configurações do H2.
4.  **Execute a aplicação:** Rode a classe `SaudeEmCasaApplication.java`.
5.  **Acesse o console do banco de dados (H2):** `http://localhost:8080/h2-console`

---

### 🛣️ Próximos Passos (Roadmap)

A próxima sprint será focada em funcionalidades de visualização para o profissional e o cliente:

* Endpoint para o profissional visualizar os agendamentos que ele aceitou.
* Endpoint para o cliente visualizar o status de seus próprios agendamentos.
* Implementação de filtros e paginação para as listagens.