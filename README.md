# Catalogo API

API REST de catalogo de produtos e categorias desenvolvida com **Spring Boot 4.0.3** e **Java 21**.
Projeto didatico para aula de desenvolvimento de APIs REST.

## Como rodar a aplicacao
```bash
./mvnw spring-boot:run
```

A aplicacao vai iniciar na porta **8080**.

## Swagger UI (Documentacao interativa)

Apos iniciar a aplicacao, acesse:
```
http://localhost:8080/swagger-ui/index.html
```

## Endpoints disponíveis

### Produtos

| Metodo | Endpoint              | Descricao       | Status de sucesso |
|--------|-----------------------|-----------------|-------------------|
| GET    | /api/v1/produtos      | Listar todos    | 200               |
| GET    | /api/v1/produtos/{id} | Buscar por ID   | 200               |
| POST   | /api/v1/produtos      | Criar produto   | 201               |
| PUT    | /api/v1/produtos/{id} | Atualizar produto | 200             |
| DELETE | /api/v1/produtos/{id} | Deletar produto | 204               |

### Categorias

| Metodo | Endpoint                | Descricao         | Status de sucesso |
|--------|-------------------------|-------------------|-------------------|
| GET    | /api/v1/categorias      | Listar todas      | 200               |
| GET    | /api/v1/categorias/{id} | Buscar por ID     | 200               |
| POST   | /api/v1/categorias      | Criar categoria   | 201               |

## Exemplos com curl

### Listar todos os produtos
```bash
curl -s http://localhost:8080/api/v1/produtos | json_pp
```

### Buscar produto por ID
```bash
curl -s http://localhost:8080/api/v1/produtos/1 | json_pp
```

### Criar um novo produto
```bash
curl -s -X POST http://localhost:8080/api/v1/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Relogio Digital",
    "descricao": "Relogio digital a prova d agua",
    "preco": 89.90
  }' | json_pp
```

### Atualizar um produto
```bash
curl -s -X PUT http://localhost:8080/api/v1/produtos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Camiseta Basica Premium",
    "descricao": "Camiseta 100% algodao premium",
    "preco": 69.90
  }' | json_pp
```

### Deletar um produto
```bash
curl -s -X DELETE http://localhost:8080/api/v1/produtos/3 -w "\nHTTP Status: %{http_code}\n"
```

### Listar todas as categorias
```bash
curl -s http://localhost:8080/api/v1/categorias | json_pp
```

### Buscar categoria por ID
```bash
curl -s http://localhost:8080/api/v1/categorias/1 | json_pp
```

### Criar uma nova categoria
```bash
curl -s -X POST http://localhost:8080/api/v1/categorias \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Monitores",
    "descricao": "Monitores e displays para computadores"
  }' | json_pp
```

### Buscar categoria com ID inexistente (retorna 404)
```bash
curl -s http://localhost:8080/api/v1/categorias/999 | json_pp
```

## Colecao Postman

Importe o JSON abaixo no Postman (File > Import > Raw text):
```json
{
  "info": {
    "name": "Catalogo API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "variable": [
    {
      "key": "baseUrl",
      "value": "http://localhost:8080/api/v1"
    }
  ],
  "item": [
    {
      "name": "Produtos",
      "item": [
        {
          "name": "Listar todos os produtos",
          "request": {
            "method": "GET",
            "url": "{{baseUrl}}/produtos"
          }
        },
        {
          "name": "Buscar produto por ID",
          "request": {
            "method": "GET",
            "url": "{{baseUrl}}/produtos/1"
          }
        },
        {
          "name": "Criar novo produto",
          "request": {
            "method": "POST",
            "url": "{{baseUrl}}/produtos",
            "header": [
              {
                "key": "Content-Type",
                "value": "application/json"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"nome\": \"Relogio Digital\",\n  \"descricao\": \"Relogio digital a prova d agua\",\n  \"preco\": 89.90\n}"
            }
          }
        },
        {
          "name": "Atualizar produto",
          "request": {
            "method": "PUT",
            "url": "{{baseUrl}}/produtos/1",
            "header": [
              {
                "key": "Content-Type",
                "value": "application/json"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"nome\": \"Camiseta Basica Premium\",\n  \"descricao\": \"Camiseta 100% algodao premium\",\n  \"preco\": 69.90\n}"
            }
          }
        },
        {
          "name": "Deletar produto",
          "request": {
            "method": "DELETE",
            "url": "{{baseUrl}}/produtos/3"
          }
        }
      ]
    },
    {
      "name": "Categorias",
      "item": [
        {
          "name": "Listar todas as categorias",
          "request": {
            "method": "GET",
            "url": "{{baseUrl}}/categorias"
          }
        },
        {
          "name": "Buscar categoria por ID",
          "request": {
            "method": "GET",
            "url": "{{baseUrl}}/categorias/1"
          }
        },
        {
          "name": "Criar nova categoria",
          "request": {
            "method": "POST",
            "url": "{{baseUrl}}/categorias",
            "header": [
              {
                "key": "Content-Type",
                "value": "application/json"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"nome\": \"Monitores\",\n  \"descricao\": \"Monitores e displays para computadores\"\n}"
            }
          }
        },
        {
          "name": "Buscar categoria inexistente (404)",
          "request": {
            "method": "GET",
            "url": "{{baseUrl}}/categorias/999"
          }
        }
      ]
    }
  ]
}
```

## Estrutura do projeto
```
src/main/java/com/saas/catalogoapi/
├── controller/
│   ├── ProdutoController.java          # Endpoints REST de produtos
│   └── CategoriaController.java        # Endpoints REST de categorias
├── service/
│   ├── ProdutoService.java             # Logica de negocio de produtos
│   └── CategoriaService.java           # Logica de negocio de categorias
├── dto/
│   ├── ProdutoRequest.java             # Dados de entrada de produto
│   ├── ProdutoResponse.java            # Dados de saida de produto
│   ├── CategoriaRequest.java           # Dados de entrada de categoria
│   └── CategoriaResponse.java          # Dados de saida de categoria
├── exception/
│   ├── ProdutoNotFoundException.java        # Excecao de produto nao encontrado
│   ├── CategoriaNotFoundException.java      # Excecao de categoria nao encontrada
│   └── GlobalExceptionHandler.java          # Tratamento global de erros
└── CatalogoApiApplication.java         # Classe principal
```