# BemComido API 🍽️

API REST para gerenciamento do cardápio do sistema BemComido.

## Tecnologias
- **Java 17** + **Spring Boot 3.2**
- **PostgreSQL** (banco de dados)
- **Spring Data JPA** (ORM)
- **Lombok** (redução de boilerplate)
- **Docker Compose** (banco local)

## Padrões de Projeto
- **DTO** — `PratoRequestDTO` e `PratoResponseDTO` separam transporte de domínio
- **GoF Strategy** — `FiltroCardapioStrategy` permite trocar o algoritmo de filtragem em tempo de execução (disponibilidade, faixa de preço, etc.)

---

## Como rodar

### 1. Subir o banco de dados
```bash
docker-compose up -d
```

### 2. Rodar a API
```bash
./mvnw spring-boot:run
```
> A API sobe em `http://localhost:8080`

---

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/api/pratos` | Criar prato |
| `GET` | `/api/pratos` | Listar todos |
| `GET` | `/api/pratos/{id}` | Buscar por ID |
| `GET` | `/api/pratos/disponiveis` | Listar disponíveis (Strategy) |
| `GET` | `/api/pratos/categoria/{cat}` | Filtrar por categoria |
| `GET` | `/api/pratos/preco?min=10&max=50` | Filtrar por faixa de preço (Strategy) |
| `PUT` | `/api/pratos/{id}` | Atualizar prato |
| `DELETE` | `/api/pratos/{id}` | Remover prato |

### Categorias disponíveis
`ENTRADA` · `PRATO_PRINCIPAL` · `SOBREMESA` · `BEBIDA` · `LANCHE`

---

## Exemplos de uso

### Criar prato
```bash
curl -X POST http://localhost:8080/api/pratos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Frango Grelhado",
    "descricao": "Frango com legumes e arroz integral",
    "preco": 28.90,
    "categoria": "PRATO_PRINCIPAL",
    "disponivel": true
  }'
```

### Listar todos
```bash
curl http://localhost:8080/api/pratos
```

### Atualizar
```bash
curl -X PUT http://localhost:8080/api/pratos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Frango Grelhado Premium",
    "preco": 32.90,
    "categoria": "PRATO_PRINCIPAL",
    "disponivel": true
  }'
```

### Deletar
```bash
curl -X DELETE http://localhost:8080/api/pratos/1
```

---

## Estrutura do projeto
```
src/main/java/com/bemcomido/api/
├── BemComidoApplication.java
├── controller/
│   ├── PratoController.java      ← Endpoints REST
│   ├── CorsConfig.java           ← Configuração CORS
│   └── GlobalExceptionHandler.java
├── service/
│   └── PratoService.java         ← Regras de negócio
├── repository/
│   └── PratoRepository.java      ← Acesso ao banco
├── model/
│   ├── Prato.java                ← Entidade JPA
│   └── CategoriaPrato.java       ← Enum de categorias
├── dto/
│   ├── PratoRequestDTO.java      ← Entrada (com validações)
│   └── PratoResponseDTO.java     ← Saída
└── strategy/
    ├── FiltroCardapioStrategy.java      ← Interface GoF Strategy
    ├── FiltroDisponivelStrategy.java    ← Filtra disponíveis
    └── FiltroFaixaPrecoStrategy.java    ← Filtra por preço
```

## Repositório Frontend
[https://github.com/Biazonx/bemcomido_web_01](https://github.com/Biazonx/bemcomido_web_01)

## Funcionamento

<img width="1919" height="906" alt="Captura de tela 2026-07-04 125032" src="https://github.com/user-attachments/assets/cbb84b04-b4e7-4be2-b1ef-7d64f69993fd" />

---

<img width="1919" height="870" alt="Captura de tela 2026-07-04 125933" src="https://github.com/user-attachments/assets/e7b0fe53-5318-486a-a721-1562d8406c93" />

---

<img width="1919" height="910" alt="Captura de tela 2026-07-04 125924" src="https://github.com/user-attachments/assets/85c93b7a-f6be-4839-9248-3376991951d0" />

---

<img width="1919" height="905" alt="Captura de tela 2026-07-04 125949" src="https://github.com/user-attachments/assets/e190634c-e79e-4cf5-a93d-cab08a2b847b" />

---

<img width="1919" height="907" alt="Captura de tela 2026-07-04 125956" src="https://github.com/user-attachments/assets/3c5a5eaf-1e3b-46e3-b13a-f53367a6d522" />

---

<img width="445" height="140" alt="Captura de tela 2026-07-04 130006" src="https://github.com/user-attachments/assets/decbb3bf-3176-4b4c-a8b0-78380c4496fb" />

---

<img width="1919" height="906" alt="Captura de tela 2026-07-04 125032" src="https://github.com/user-attachments/assets/ab6bc2d7-120a-4182-bd05-935df75c0c94" />
