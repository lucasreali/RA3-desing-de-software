# Documentação da API - Sistema de Gerenciamento de Locadora de Veículos

## Informações Gerais
- **Base URL**: `http://localhost:8081`
- **Formato de dados**: JSON
- **Banco de dados**: MySQL na porta 3306

---

## Índice
1. [Customer (Cliente)](#customer-cliente)
2. [Car (Carro)](#car-carro)
3. [Payment Method (Método de Pagamento)](#payment-method-método-de-pagamento)
4. [Franchise (Franquia)](#franchise-franquia)
5. [Location (Locação)](#location-locação)
6. [Purchase (Compra)](#purchase-compra)
7. [Service (Serviço)](#service-serviço)
8. [Fluxo de Teste Completo](#fluxo-de-teste-completo)

---

## Customer (Cliente)

### 1. Criar Cliente
**POST** `/customer`

**Request Body:**
```json
{
  "name": "João Silva",
  "cpf": "123.456.789-00",
  "email": "joao.silva@email.com"
}
```

**Response:** `201 Created`
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440001",
  "name": "João Silva",
  "cpf": "123.456.789-00",
  "email": "joao.silva@email.com",
  "createdAt": "2025-11-14T10:30:00",
  "updatedAt": "2025-11-14T10:30:00"
}
```

### 2. Buscar Cliente por ID
**GET** `/customer/{id}`

**Exemplo:** `GET /customer/550e8400-e29b-41d4-a716-446655440001`

**Response:** `200 OK`
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440001",
  "name": "João Silva",
  "cpf": "123.456.789-00",
  "email": "joao.silva@email.com",
  "createdAt": "2025-11-14T10:30:00",
  "updatedAt": "2025-11-14T10:30:00"
}
```

### 3. Atualizar Cliente
**PUT** `/customer/{id}`

**Request Body:** (todos os campos são opcionais)
```json
{
  "name": "João Silva Santos",
  "cpf": "123.456.789-00",
  "email": "joao.santos@email.com"
}
```

**Response:** `200 OK`
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440001",
  "name": "João Silva Santos",
  "cpf": "123.456.789-00",
  "email": "joao.santos@email.com",
  "createdAt": "2025-11-14T10:30:00",
  "updatedAt": "2025-11-14T11:15:00"
}
```

### 4. Deletar Cliente
**DELETE** `/customer/{id}`

**Response:** `204 No Content`

---

## Car (Carro)

### 1. Criar Carro
**POST** `/car`

**Request Body:**
```json
{
  "brand": "Toyota",
  "model": "Corolla",
  "category": "Sedan",
  "color": "Prata",
  "price": 150000,
  "year": "2024"
}
```

**Response:** `201 Created`
```json
{
  "id": "660e8400-e29b-41d4-a716-446655440002",
  "brand": "Toyota",
  "model": "Corolla",
  "category": "Sedan",
  "color": "Prata",
  "price": 150000,
  "year": "2024",
  "createdAt": "2025-11-14T10:35:00",
  "updatedAt": "2025-11-14T10:35:00"
}
```

### 2. Buscar Carro por ID
**GET** `/car/{id}`

**Exemplo:** `GET /car/660e8400-e29b-41d4-a716-446655440002`

**Response:** `200 OK`
```json
{
  "id": "660e8400-e29b-41d4-a716-446655440002",
  "brand": "Toyota",
  "model": "Corolla",
  "category": "Sedan",
  "color": "Prata",
  "price": 150000,
  "year": "2024",
  "createdAt": "2025-11-14T10:35:00",
  "updatedAt": "2025-11-14T10:35:00"
}
```

### 3. Atualizar Carro
**PUT** `/car/{id}`

**Request Body:** (todos os campos são opcionais)
```json
{
  "brand": "Toyota",
  "model": "Corolla XEI",
  "category": "Sedan",
  "color": "Preto",
  "price": 155000,
  "year": "2024"
}
```

**Response:** `200 OK`
```json
{
  "id": "660e8400-e29b-41d4-a716-446655440002",
  "brand": "Toyota",
  "model": "Corolla XEI",
  "category": "Sedan",
  "color": "Preto",
  "price": 155000,
  "year": "2024",
  "createdAt": "2025-11-14T10:35:00",
  "updatedAt": "2025-11-14T11:20:00"
}
```

### 4. Deletar Carro
**DELETE** `/car/{id}`

**Response:** `204 No Content`

---

## Payment Method (Método de Pagamento)

### 1. Criar Método de Pagamento
**POST** `/paymentMethod`

**Request Body:**
```json
{
  "name": "Cartão de Crédito",
  "description": "Visa ou Mastercard com parcelamento em até 12x"
}
```

**Response:** `201 Created`
```json
{
  "id": "770e8400-e29b-41d4-a716-446655440003",
  "name": "Cartão de Crédito",
  "description": "Visa ou Mastercard com parcelamento em até 12x",
  "createdAt": "2025-11-14T10:40:00",
  "updatedAt": "2025-11-14T10:40:00"
}
```

### 2. Buscar Método de Pagamento por ID
**GET** `/paymentMethod/{id}`

**Exemplo:** `GET /paymentMethod/770e8400-e29b-41d4-a716-446655440003`

**Response:** `200 OK`
```json
{
  "id": "770e8400-e29b-41d4-a716-446655440003",
  "name": "Cartão de Crédito",
  "description": "Visa ou Mastercard com parcelamento em até 12x",
  "createdAt": "2025-11-14T10:40:00",
  "updatedAt": "2025-11-14T10:40:00"
}
```

### 3. Atualizar Método de Pagamento
**PUT** `/paymentMethod/{id}`

**Request Body:** (todos os campos são opcionais)
```json
{
  "name": "Cartão de Crédito Premium",
  "description": "Visa, Mastercard ou Elo com parcelamento em até 18x"
}
```

**Response:** `200 OK`
```json
{
  "id": "770e8400-e29b-41d4-a716-446655440003",
  "name": "Cartão de Crédito Premium",
  "description": "Visa, Mastercard ou Elo com parcelamento em até 18x",
  "createdAt": "2025-11-14T10:40:00",
  "updatedAt": "2025-11-14T11:25:00"
}
```

### 4. Deletar Método de Pagamento
**DELETE** `/paymentMethod/{id}`

**Response:** `204 No Content`

---

## Franchise (Franquia)

### 1. Criar Franquia
**POST** `/franchise`

**Request Body:**
```json
{
  "localization": "Curitiba - Centro"
}
```

**Response:** `201 Created`
```json
{
  "id": "880e8400-e29b-41d4-a716-446655440004",
  "localization": "Curitiba - Centro",
  "createdAt": "2025-11-14T10:45:00",
  "updatedAt": "2025-11-14T10:45:00"
}
```

### 2. Buscar Franquia por ID
**GET** `/franchise/{id}`

**Exemplo:** `GET /franchise/880e8400-e29b-41d4-a716-446655440004`

**Response:** `200 OK`
```json
{
  "id": "880e8400-e29b-41d4-a716-446655440004",
  "localization": "Curitiba - Centro",
  "createdAt": "2025-11-14T10:45:00",
  "updatedAt": "2025-11-14T10:45:00"
}
```

### 3. Atualizar Franquia
**PUT** `/franchise/{id}`

**Request Body:**
```json
{
  "localization": "Curitiba - Batel"
}
```

**Response:** `200 OK`
```json
{
  "id": "880e8400-e29b-41d4-a716-446655440004",
  "localization": "Curitiba - Batel",
  "createdAt": "2025-11-14T10:45:00",
  "updatedAt": "2025-11-14T11:30:00"
}
```

### 4. Deletar Franquia
**DELETE** `/franchise/{id}`

**Response:** `204 No Content`

---

## Location (Locação)

### 1. Criar Locação
**POST** `/location`

**Request Body:**
```json
{
  "value": 1500,
  "expiration": "2025-12-14T10:00:00",
  "customerId": "550e8400-e29b-41d4-a716-446655440001",
  "carId": "660e8400-e29b-41d4-a716-446655440002",
  "paymentMethodId": "770e8400-e29b-41d4-a716-446655440003"
}
```

**Observações:**
- `customerId`, `carId` e `paymentMethodId` devem ser IDs válidos de registros já existentes
- O sistema busca os objetos relacionados no banco de dados
- O campo `value` é calculado automaticamente como `carPrice / 100` no método `@PrePersist`

**Response:** `201 Created`
```json
{
  "id": "990e8400-e29b-41d4-a716-446655440005",
  "value": 1500,
  "expiration": "2025-12-14T10:00:00",
  "customer": {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "name": "João Silva",
    "cpf": "123.456.789-00",
    "email": "joao.silva@email.com",
    "createdAt": "2025-11-14T10:30:00",
    "updatedAt": "2025-11-14T10:30:00"
  },
  "car": {
    "id": "660e8400-e29b-41d4-a716-446655440002",
    "brand": "Toyota",
    "model": "Corolla",
    "category": "Sedan",
    "color": "Prata",
    "price": 150000,
    "year": "2024",
    "createdAt": "2025-11-14T10:35:00",
    "updatedAt": "2025-11-14T10:35:00"
  },
  "paymentMethod": {
    "id": "770e8400-e29b-41d4-a716-446655440003",
    "name": "Cartão de Crédito",
    "description": "Visa ou Mastercard com parcelamento em até 12x",
    "createdAt": "2025-11-14T10:40:00",
    "updatedAt": "2025-11-14T10:40:00"
  },
  "createdAt": "2025-11-14T10:50:00",
  "updatedAt": "2025-11-14T10:50:00"
}
```

### 2. Buscar Locação por ID
**GET** `/location/{id}`

**Exemplo:** `GET /location/990e8400-e29b-41d4-a716-446655440005`

**Response:** `200 OK`
```json
{
  "id": "990e8400-e29b-41d4-a716-446655440005",
  "value": 1500,
  "expiration": "2025-12-14T10:00:00",
  "customer": {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "name": "João Silva",
    "cpf": "123.456.789-00",
    "email": "joao.silva@email.com",
    "createdAt": "2025-11-14T10:30:00",
    "updatedAt": "2025-11-14T10:30:00"
  },
  "car": {
    "id": "660e8400-e29b-41d4-a716-446655440002",
    "brand": "Toyota",
    "model": "Corolla",
    "category": "Sedan",
    "color": "Prata",
    "price": 150000,
    "year": "2024",
    "createdAt": "2025-11-14T10:35:00",
    "updatedAt": "2025-11-14T10:35:00"
  },
  "paymentMethod": {
    "id": "770e8400-e29b-41d4-a716-446655440003",
    "name": "Cartão de Crédito",
    "description": "Visa ou Mastercard com parcelamento em até 12x",
    "createdAt": "2025-11-14T10:40:00",
    "updatedAt": "2025-11-14T10:40:00"
  },
  "createdAt": "2025-11-14T10:50:00",
  "updatedAt": "2025-11-14T10:50:00"
}
```

### 3. Atualizar Locação
**PUT** `/location/{id}`

**Request Body:** (todos os campos são opcionais)
```json
{
  "value": 1800,
  "expiration": "2025-12-20T10:00:00",
  "customerId": "550e8400-e29b-41d4-a716-446655440001",
  "carId": "660e8400-e29b-41d4-a716-446655440002",
  "paymentMethodId": "770e8400-e29b-41d4-a716-446655440003"
}
```

**Response:** `200 OK`
```json
{
  "id": "990e8400-e29b-41d4-a716-446655440005",
  "value": 1800,
  "expiration": "2025-12-20T10:00:00",
  "customer": {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "name": "João Silva",
    "cpf": "123.456.789-00",
    "email": "joao.silva@email.com",
    "createdAt": "2025-11-14T10:30:00",
    "updatedAt": "2025-11-14T10:30:00"
  },
  "car": {
    "id": "660e8400-e29b-41d4-a716-446655440002",
    "brand": "Toyota",
    "model": "Corolla",
    "category": "Sedan",
    "color": "Prata",
    "price": 150000,
    "year": "2024",
    "createdAt": "2025-11-14T10:35:00",
    "updatedAt": "2025-11-14T10:35:00"
  },
  "paymentMethod": {
    "id": "770e8400-e29b-41d4-a716-446655440003",
    "name": "Cartão de Crédito",
    "description": "Visa ou Mastercard com parcelamento em até 12x",
    "createdAt": "2025-11-14T10:40:00",
    "updatedAt": "2025-11-14T10:40:00"
  },
  "createdAt": "2025-11-14T10:50:00",
  "updatedAt": "2025-11-14T11:35:00"
}
```

### 4. Deletar Locação
**DELETE** `/location/{id}`

**Response:** `204 No Content`

---

## Purchase (Compra)

### 1. Criar Compra
**POST** `/purchase`

**Request Body:**
```json
{
  "installment": 12,
  "customerId": "550e8400-e29b-41d4-a716-446655440001",
  "carId": "660e8400-e29b-41d4-a716-446655440002",
  "paymentMethodId": "770e8400-e29b-41d4-a716-446655440003"
}
```

**Observações:**
- `customerId`, `carId` e `paymentMethodId` devem ser IDs válidos de registros já existentes
- O sistema busca os objetos relacionados no banco de dados
- `installment` representa o número de parcelas

**Response:** `201 Created`
```json
{
  "id": "aa0e8400-e29b-41d4-a716-446655440006",
  "installment": 12,
  "customer": {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "name": "João Silva",
    "cpf": "123.456.789-00",
    "email": "joao.silva@email.com",
    "createdAt": "2025-11-14T10:30:00",
    "updatedAt": "2025-11-14T10:30:00"
  },
  "car": {
    "id": "660e8400-e29b-41d4-a716-446655440002",
    "brand": "Toyota",
    "model": "Corolla",
    "category": "Sedan",
    "color": "Prata",
    "price": 150000,
    "year": "2024",
    "createdAt": "2025-11-14T10:35:00",
    "updatedAt": "2025-11-14T10:35:00"
  },
  "paymentMethod": {
    "id": "770e8400-e29b-41d4-a716-446655440003",
    "name": "Cartão de Crédito",
    "description": "Visa ou Mastercard com parcelamento em até 12x",
    "createdAt": "2025-11-14T10:40:00",
    "updatedAt": "2025-11-14T10:40:00"
  },
  "createdAt": "2025-11-14T10:55:00",
  "updatedAt": "2025-11-14T10:55:00"
}
```

### 2. Buscar Compra por ID
**GET** `/purchase/{id}`

**Exemplo:** `GET /purchase/aa0e8400-e29b-41d4-a716-446655440006`

**Response:** `200 OK`
```json
{
  "id": "aa0e8400-e29b-41d4-a716-446655440006",
  "installment": 12,
  "customer": {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "name": "João Silva",
    "cpf": "123.456.789-00",
    "email": "joao.silva@email.com",
    "createdAt": "2025-11-14T10:30:00",
    "updatedAt": "2025-11-14T10:30:00"
  },
  "car": {
    "id": "660e8400-e29b-41d4-a716-446655440002",
    "brand": "Toyota",
    "model": "Corolla",
    "category": "Sedan",
    "color": "Prata",
    "price": 150000,
    "year": "2024",
    "createdAt": "2025-11-14T10:35:00",
    "updatedAt": "2025-11-14T10:35:00"
  },
  "paymentMethod": {
    "id": "770e8400-e29b-41d4-a716-446655440003",
    "name": "Cartão de Crédito",
    "description": "Visa ou Mastercard com parcelamento em até 12x",
    "createdAt": "2025-11-14T10:40:00",
    "updatedAt": "2025-11-14T10:40:00"
  },
  "createdAt": "2025-11-14T10:55:00",
  "updatedAt": "2025-11-14T10:55:00"
}
```

### 3. Atualizar Compra
**PUT** `/purchase/{id}`

**Request Body:** (todos os campos são opcionais)
```json
{
  "installment": 18,
  "customerId": "550e8400-e29b-41d4-a716-446655440001",
  "carId": "660e8400-e29b-41d4-a716-446655440002",
  "paymentMethodId": "770e8400-e29b-41d4-a716-446655440003"
}
```

**Response:** `200 OK`
```json
{
  "id": "aa0e8400-e29b-41d4-a716-446655440006",
  "installment": 18,
  "customer": {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "name": "João Silva",
    "cpf": "123.456.789-00",
    "email": "joao.silva@email.com",
    "createdAt": "2025-11-14T10:30:00",
    "updatedAt": "2025-11-14T10:30:00"
  },
  "car": {
    "id": "660e8400-e29b-41d4-a716-446655440002",
    "brand": "Toyota",
    "model": "Corolla",
    "category": "Sedan",
    "color": "Prata",
    "price": 150000,
    "year": "2024",
    "createdAt": "2025-11-14T10:35:00",
    "updatedAt": "2025-11-14T10:35:00"
  },
  "paymentMethod": {
    "id": "770e8400-e29b-41d4-a716-446655440003",
    "name": "Cartão de Crédito",
    "description": "Visa ou Mastercard com parcelamento em até 12x",
    "createdAt": "2025-11-14T10:40:00",
    "updatedAt": "2025-11-14T10:40:00"
  },
  "createdAt": "2025-11-14T10:55:00",
  "updatedAt": "2025-11-14T11:40:00"
}
```

### 4. Deletar Compra
**DELETE** `/purchase/{id}`

**Response:** `204 No Content`

---

## Service (Serviço)

### 1. Criar Serviço
**POST** `/service`

**Request Body:**
```json
{
  "name": "Troca de Óleo",
  "description": "Troca completa de óleo e filtro",
  "price": 250,
  "carId": "660e8400-e29b-41d4-a716-446655440002"
}
```

**Observações:**
- `carId` deve ser um ID válido de um carro já existente
- O sistema busca o objeto Car no banco de dados

**Response:** `201 Created`
```json
{
  "id": "bb0e8400-e29b-41d4-a716-446655440007",
  "name": "Troca de Óleo",
  "description": "Troca completa de óleo e filtro",
  "price": 250,
  "carId": "660e8400-e29b-41d4-a716-446655440002",
  "createdAt": "2025-11-14T11:00:00",
  "updatedAt": "2025-11-14T11:00:00"
}
```

### 2. Buscar Serviço por ID
**GET** `/service/{id}`

**Exemplo:** `GET /service/bb0e8400-e29b-41d4-a716-446655440007`

**Response:** `200 OK`
```json
{
  "id": "bb0e8400-e29b-41d4-a716-446655440007",
  "name": "Troca de Óleo",
  "description": "Troca completa de óleo e filtro",
  "price": 250,
  "carId": "660e8400-e29b-41d4-a716-446655440002",
  "createdAt": "2025-11-14T11:00:00",
  "updatedAt": "2025-11-14T11:00:00"
}
```

### 3. Atualizar Serviço
**PUT** `/service/{id}`

**Request Body:** (todos os campos são opcionais)
```json
{
  "name": "Troca de Óleo Premium",
  "description": "Troca completa de óleo sintético e filtro premium",
  "price": 350,
  "carId": "660e8400-e29b-41d4-a716-446655440002"
}
```

**Response:** `200 OK`
```json
{
  "id": "bb0e8400-e29b-41d4-a716-446655440007",
  "name": "Troca de Óleo Premium",
  "description": "Troca completa de óleo sintético e filtro premium",
  "price": 350,
  "carId": "660e8400-e29b-41d4-a716-446655440002",
  "createdAt": "2025-11-14T11:00:00",
  "updatedAt": "2025-11-14T11:45:00"
}
```

### 4. Deletar Serviço
**DELETE** `/service/{id}`

**Response:** `204 No Content`

---

## Fluxo de Teste Completo

### Cenário: Criar uma locação completa de veículo

#### Passo 1: Criar um Cliente
```bash
POST http://localhost:8081/customer
Content-Type: application/json

{
  "name": "Maria Oliveira",
  "cpf": "987.654.321-00",
  "email": "maria.oliveira@email.com"
}
```
**Guarde o `id` retornado:** `550e8400-e29b-41d4-a716-446655440001`

#### Passo 2: Criar um Carro
```bash
POST http://localhost:8081/car
Content-Type: application/json

{
  "brand": "Honda",
  "model": "Civic",
  "category": "Sedan",
  "color": "Azul",
  "price": 180000,
  "year": "2024"
}
```
**Guarde o `id` retornado:** `660e8400-e29b-41d4-a716-446655440002`

#### Passo 3: Criar um Método de Pagamento
```bash
POST http://localhost:8081/paymentMethod
Content-Type: application/json

{
  "name": "PIX",
  "description": "Pagamento instantâneo via PIX"
}
```
**Guarde o `id` retornado:** `770e8400-e29b-41d4-a716-446655440003`

#### Passo 4: Criar a Locação
```bash
POST http://localhost:8081/location
Content-Type: application/json

{
  "value": 1800,
  "expiration": "2025-12-31T23:59:59",
  "customerId": "550e8400-e29b-41d4-a716-446655440001",
  "carId": "660e8400-e29b-41d4-a716-446655440002",
  "paymentMethodId": "770e8400-e29b-41d4-a716-446655440003"
}
```

#### Passo 5: Criar uma Compra (opcional)
```bash
POST http://localhost:8081/purchase
Content-Type: application/json

{
  "installment": 24,
  "customerId": "550e8400-e29b-41d4-a716-446655440001",
  "carId": "660e8400-e29b-41d4-a716-446655440002",
  "paymentMethodId": "770e8400-e29b-41d4-a716-446655440003"
}
```

#### Passo 6: Criar um Serviço para o Carro
```bash
POST http://localhost:8081/service
Content-Type: application/json

{
  "name": "Revisão Completa",
  "description": "Revisão de 10.000 km incluindo troca de óleo e filtros",
  "price": 800,
  "carId": "660e8400-e29b-41d4-a716-446655440002"
}
```

#### Passo 7: Criar uma Franquia
```bash
POST http://localhost:8081/franchise
Content-Type: application/json

{
  "localization": "São Paulo - Paulista"
}
```

---

## Validações e Restrições

### Customer
- `name`: obrigatório, não pode ser vazio
- `cpf`: obrigatório, não pode ser vazio, deve ser único
- `email`: obrigatório, não pode ser vazio, deve ser um email válido, deve ser único

### Car
- `brand`: obrigatório, não pode ser vazio
- `model`: obrigatório, não pode ser vazio
- `category`: obrigatório, não pode ser vazio
- `color`: obrigatório, não pode ser vazio
- `price`: obrigatório, deve ser um número inteiro
- `year`: obrigatório, não pode ser vazio

### Payment Method
- `name`: obrigatório, não pode ser vazio
- `description`: opcional

### Franchise
- `localization`: obrigatório, não pode ser vazio

### Location
- `value`: obrigatório, deve ser um número inteiro
- `expiration`: obrigatório, formato ISO 8601 (YYYY-MM-DDTHH:mm:ss)
- `customerId`: obrigatório, deve existir na base de dados
- `carId`: obrigatório, deve existir na base de dados
- `paymentMethodId`: obrigatório, deve existir na base de dados

### Purchase
- `installment`: obrigatório, deve ser um número inteiro
- `customerId`: obrigatório, deve existir na base de dados
- `carId`: obrigatório, deve existir na base de dados
- `paymentMethodId`: obrigatório, deve existir na base de dados

### Service
- `name`: obrigatório, não pode ser vazio
- `description`: obrigatório, não pode ser vazio
- `price`: obrigatório, deve ser um número inteiro positivo
- `carId`: obrigatório, deve existir na base de dados

---

## Códigos de Status HTTP

| Código | Descrição |
|--------|-----------|
| 200 OK | Requisição bem-sucedida (GET, PUT) |
| 201 Created | Recurso criado com sucesso (POST) |
| 204 No Content | Recurso deletado com sucesso (DELETE) |
| 400 Bad Request | Dados inválidos na requisição |
| 404 Not Found | Recurso não encontrado |
| 500 Internal Server Error | Erro interno do servidor |

---

## Exemplos de Erros

### Erro 404 - Recurso não encontrado
```json
{
  "timestamp": "2025-11-14T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Customer not found",
  "path": "/customer/invalid-id"
}
```

### Erro 400 - Validação
```json
{
  "timestamp": "2025-11-14T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": [
    {
      "field": "email",
      "message": "must be a well-formed email address"
    }
  ],
  "path": "/customer"
}
```

---

## Notas Importantes

1. **IDs**: Todos os IDs são gerados automaticamente como UUID
2. **Timestamps**: Os campos `createdAt` e `updatedAt` são gerenciados automaticamente
3. **Relacionamentos**: Location e Purchase requerem entidades relacionadas (Customer, Car, PaymentMethod) já existentes
4. **Service**: Requer um Car já existente
5. **Campos Opcionais em Update**: Todos os campos em operações de UPDATE são opcionais - apenas os campos enviados serão atualizados
6. **Formato de Data**: Use o formato ISO 8601 para datas: `YYYY-MM-DDTHH:mm:ss`

---

## Ferramentas Recomendadas para Teste

- **Postman**: Para testes manuais de API
- **Insomnia**: Alternativa ao Postman
- **cURL**: Para testes via linha de comando
- **HTTPie**: Cliente HTTP amigável para linha de comando

### Exemplo com cURL:
```bash
# Criar um cliente
curl -X POST http://localhost:8081/customer \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "cpf": "123.456.789-00",
    "email": "joao.silva@email.com"
  }'

# Buscar um cliente
curl -X GET http://localhost:8081/customer/{id}

# Atualizar um cliente
curl -X PUT http://localhost:8081/customer/{id} \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva Santos"
  }'

# Deletar um cliente
curl -X DELETE http://localhost:8081/customer/{id}
```

---

**Última atualização:** 14 de Novembro de 2025
**Versão da API:** 1.0
**Porta:** 8081

