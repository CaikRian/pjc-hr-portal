# PJC HR Portal — Front-end + API (Java + MySQL)

Projeto de portfólio: **Front-end Vanilla (HTML/CSS/JS + Bootstrap)** e **API Java Spring Boot** com **MySQL**. Ideal para demonstrar:
- Domínio de **HTML, CSS e JavaScript** (sem frameworks no front).
- **Criação e manutenção de APIs** REST (Java/Spring Boot).
- **MySQL** com JPA e migração automática.
- Padrões **MVC** (Controller/Service/Repository) e noções de **DDD** (camadas de domínio/aplicação).
- Uso opcional de **jQuery** para interação simples.
- Docker Compose para subir rapidamente (MySQL + API).

> **Atenção**: Dados e exemplos são fictícios. Use para demonstração.

---

## 🚀 Como rodar (Docker – recomendado)
Requisitos: Docker Desktop.

```bash
# 1) Subir MySQL + API
docker compose up --build

# 2) Acessar API (Swagger-like JSON):
# Listar candidatos
curl http://localhost:8080/api/candidates
```

- MySQL: `localhost:3306` (db: `pjc_hr`, user: `pjc`, pass: `pjc`)
- API: `http://localhost:8080`

Para abrir o **Front-end** (estático), basta abrir `frontend/index.html` no navegador (clique duplo) ou servir localmente (ex.: VSCode Live Server).

> O front consome `http://localhost:8080/api/...`. Se usar outra porta/host, edite `frontend/assets/js/app.js`.

---

## 🧪 Como rodar local (sem Docker)
Requisitos: JDK 17+, Maven, MySQL 8+

1. Crie o banco e usuário:
```sql
CREATE DATABASE pjc_hr CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'pjc'@'%' IDENTIFIED BY 'pjc';
GRANT ALL PRIVILEGES ON pjc_hr.* TO 'pjc'@'%';
FLUSH PRIVILEGES;
```

2. Ajuste `backend/src/main/resources/application.properties` se necessário.

3. Rode a API:
```bash
cd backend
mvn spring-boot:run
```

4. Abra `frontend/index.html` no navegador.

---

## 📚 Endpoints principais
- `GET /api/health` — healthcheck
- `GET /api/candidates` — lista candidatos
- `POST /api/candidates` — cria candidato
- `GET /api/candidates/{id}` — busca por id
- `PUT /api/candidates/{id}` — atualiza
- `DELETE /api/candidates/{id}` — remove

Payload exemplo (POST/PUT):
```json
{
  "name": "Ana Silva",
  "email": "ana@exemplo.com",
  "role": "Front-end Jr",
  "seniority": "Junior"
}
```

---

## 🧱 Estrutura
```
pjc-hr-portal/
├── backend/                 # Spring Boot API (Java 17)
│   ├── src/main/java/com/pjc/hrportal
│   │   ├── api/controller   # Controllers (MVC)
│   │   ├── application      # DTOs/Services (DDD app layer)
│   │   ├── domain           # Model/Repository (DDD domain)
│   │   └── config           # CORS e afins
│   └── src/main/resources   # application.properties
├── frontend/                # HTML/CSS/JS (Bootstrap + jQuery opcional)
│   ├── index.html
│   └── assets/
│       ├── css/styles.css
│       └── js/{app.js, jquery-demo.js}
├── scripts/init.sql         # seed opcional para MySQL
└── docker-compose.yml       # MySQL + API
```

---

## ✅ O que este projeto comprova para a vaga
- **Front-end**: HTML semântico, CSS responsivo, JS com `fetch` e jQuery.
- **Back-end/API**: CRUD completo com Spring Boot, JPA/Hibernate e validação.
- **Banco de dados**: MySQL com mapeamento ORM.
- **Padrões**: camadas MVC + noções de DDD (domain, application, api).
- **Integração**: front consome API real (CORS configurado).
- **Agilidade**: Docker Compose para setup rápido.

---

## 📝 Próximos passos sugeridos
- Adicionar autenticação JWT.
- Paginação e filtros no endpoint de candidatos.
- Testes unitários (JUnit/MockMvc) e CI.
- Documentação OpenAPI/Swagger com springdoc.
