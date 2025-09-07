# 🚀 API Check-in e Check-out de Motos no Pátio

Este projeto é uma API REST desenvolvida em Java utilizando Spring Boot, que tem como objetivo gerenciar o check-in e check-out de motos em um pátio. O sistema permite o cadastro de motos e o controle de suas movimentações (entrada e saída).

## 🏗️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (banco em memória)
- Bean Validation
- Lombok
- Maven
- Tratamento centralizado de erros com ExceptionHandler

## 🔗 Endpoints Disponíveis

### 🏍️ Motos
- `GET /motos` — Lista todas as motos (com paginação e ordenação)
- `POST /motos` — Cadastra uma nova moto
- `GET /motos/search?placa={placa}` — Busca moto pela placa (com paginação)

### 📄 Registros (Movimentações)
- `POST /registros/checkin` — Realiza o check-in da moto no pátio
- `POST /registros/checkout` — Realiza o check-out da moto
- `GET /registros?placa={placa}` — Lista os registros de uma moto (com paginação)

## 📦 Entidades

- `Moto`: placa, modelo
- `Registro`: data e hora de check-in e check-out, associado à moto

## 🔗 Relacionamento

- Uma moto pode ter vários registros de entrada e saída.

## ✅ Funcionalidades Atendidas

- CRUD completo de `Moto` e `Registro`
- Relacionamento entre entidades
- Validação de campos com Bean Validation
- Paginação, ordenação e busca por parâmetros
- Tratamento centralizado de erros
- Uso de DTOs
- Banco H2 configurado para testes