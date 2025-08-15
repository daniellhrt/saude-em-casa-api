# 🩺 Saúde em Casa API

<p align="center">
  <img src="logo.png" alt="Logo Saúde em Casa" width="250" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3%2B-brightgreen" alt="Spring Boot 3+" />
  <img src="https://img.shields.io/badge/JWT-Security-blue" alt="JWT Security" />
  <img src="https://img.shields.io/badge/Gradle-Build Tool-02303A" alt="Gradle" />
  <img src="https://img.shields.io/badge/Database-H2-informational" alt="H2 Database" />
</p>

> Plataforma de agendamento de serviços de enfermagem domiciliar, conectando clientes a profissionais de saúde de forma segura e eficiente — no estilo **Uber/iFood**.

---

## 📑 Sumário

- [🚀 Stack de Tecnologias](#-stack-de-tecnologias)
- [✅ Sprint 1 — Autenticação e Segurança](#-sprint-1--autenticação-e-segurança-concluída)
- [✅ Sprint 2 — Gestão de Serviços e Agendamentos](#-sprint-2--gestão-de-serviços-e-agendamentos-concluída)
- [🔑 Endpoints da API](#-endpoints-da-api)
- [⚙️ Como Rodar o Projeto](#️-como-rodar-o-projeto)
- [🛣️ Próximos Passos (Roadmap)](#️-próximos-passos-roadmap)

---

## 🚀 Stack de Tecnologias

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3+
- **Banco de Dados:** H2 Database (em memória para desenvolvimento)
- **Segurança:** Spring Security, JWT (JSON Web Tokens)
- **Build Tool:** Gradle
- **IDE:** IntelliJ IDEA

---

## ✅ Sprint 1 — Autenticação e Segurança (Concluída)

- **Sistema de Autenticação:** Registro de novos usuários (`CLIENTE` e `PROFISSIONAL`) e login.
- **Segurança com JWT:** Apenas usuários autenticados acessam rotas protegidas.
- **Mapeamento de Dados:** Persistência com JPA + Hibernate.
- **Boas Práticas:** DTOs (Records), tratamento global de exceções, arquitetura em camadas.

---

## ✅ Sprint 2 — Gestão de Serviços e Agendamentos (Concluída)

- **Modelagem de Dados:** Entidades `Servico` e `Agendamento` para catálogo e solicitações.
- **Fluxo de Agendamento:** Criar, aceitar e encerrar (`PENDENTE`, `CONFIRMADO`, `CONCLUIDO`).
- **APIs:**
    - Listagem de serviços disponíveis.
    - Solicitação e gestão de agendamentos.
- **Boas Práticas:** DTOs de requisição e resposta para desacoplamento e fluxo limpo.

---

## 🔑 Endpoints da API

### 🔐 Autenticação
| Método | Endpoint          | Descrição                                                  | Autenticação |
|--------|-------------------|------------------------------------------------------------|--------------|
| POST   | `/auth/register`  | Registra um novo **CLIENTE** ou **PROFISSIONAL**           | ❌ Não       |
| POST   | `/auth/login`     | Autentica e retorna um token JWT                           | ❌ Não       |

### 📋 Serviços e Agendamentos
| Método | Endpoint                          | Descrição                                                                 | Autenticação |
|--------|------------------------------------|---------------------------------------------------------------------------|--------------|
| GET    | `/servicos`                        | Lista todos os serviços disponíveis                                       | ✅ Sim       |
| GET    | `/agendamentos/pendentes`          | Lista agendamentos com status `PENDENTE`                                  | ✅ Sim       |
| POST   | `/agendamentos`                    | Cria novo agendamento (`PENDENTE`)                                        | ✅ Sim       |
| PATCH  | `/agendamentos/aceitar/{id}`       | Altera status para `CONFIRMADO` e associa profissional                    | ✅ Sim       |
| PATCH  | `/agendamentos/encerrar/{id}`      | Altera status para `CONCLUIDO`                                            | ✅ Sim       |

---

## ⚙️ Como Rodar o Projeto

```bash
# 1️⃣ Clone o repositório
git clone [URL_DO_REPOSITORIO]

# 2️⃣ Abra no IntelliJ IDEA como projeto Gradle

# 3️⃣ Configure application.properties
#    - Adicione chave secreta para JWT
#    - Configure acesso ao H2

# 4️⃣ Rode a aplicação
./gradlew bootRun
# ou execute a classe principal: SaudeEmCasaApplication.java

# 5️⃣ Acesse o console H2
http://localhost:8080/h2-console
