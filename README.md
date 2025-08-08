# 🩺 Saúde em Casa API

![Saúde em Casa](http://googleusercontent.com/image_generation_content/39).

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

### 🔑 Endpoints da API (Sprint 1)

Os seguintes endpoints de autenticação e teste foram disponibilizados:

#### Autenticação
- `POST /auth/register`
    - **Descrição:** Registra um novo `CLIENTE` ou `PROFISSIONAL`.
    - **Exige autenticação:** Não
- `POST /auth/login`
    - **Descrição:** Autentica um usuário e retorna um JWT para uso nas próximas requisições.
    - **Exige autenticação:** Não

#### Teste de Acesso
- `GET /test/public`
    - **Descrição:** Endpoint público, acessível sem token.
    - **Exige autenticação:** Não
- `GET /test/protected`
    - **Descrição:** Endpoint protegido, acessível apenas com um token JWT válido no cabeçalho `Authorization`.
    - **Exige autenticação:** Sim

---

### ⚙️ Como Rodar o Projeto

1.  **Clone o repositório:** `git clone [URL_DO_REPOSITORIO]`
2.  **Abra o projeto:** Importe o projeto no IntelliJ IDEA como um projeto Gradle.
3.  **Configure o arquivo `application.properties`:** Adicione a chave secreta para o JWT.
    ```properties
    api.security.token.secret=SUA_CHAVE_SECRETA_MUITO_FORTE
    ```
4.  **Execute a aplicação:** Rode a classe `SaudeEmCasaApplication.java`.

---

### 🛣️ Próximos Passos (Roadmap)

A próxima sprint será focada na implementação da regra de negócio central da plataforma:

* Criação de tipos de serviço (ex: "Aplicação de Injeção", "Curativo").
* Endpoints para clientes solicitarem serviços com base em localização.
* Lógica de `matching` para notificar profissionais disponíveis.
* Endpoints para profissionais aceitarem ou rejeitarem as solicitações.
