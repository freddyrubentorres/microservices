# Microservicios

## Introducción

Este proyecto consiste en dos microservicios: `account_transaction` y `client_person`. El `client_person` maneja información de clientes y el `account_transaction` gestiona cuentas y transacciones. Ambos servicios se comunican de forma sincrónica y asincrónica, utilizando OpenFeign y RabbitMQ respectivamente.

![image](https://github.com/freddyrubentorres/img/blob/main/principal/rabbitMQ.png?raw=true)

## Requerimientos

- **Java 17**
- **Spring Boot 3.3.1**
- **MySQL**
- **RabbitMQ**
- **Docker y Docker Compose**
- **Mapstruct**
- **Spring-boot-starter-validation**
- **Lombok**
- **Jacoco**

## Estructura del Proyecto

### account_transaction

![image](https://github.com/freddyrubentorres/img/blob/main/account_transaction/project.png?raw=true)

### client_person

![image](https://github.com/freddyrubentorres/img/blob/main/client_person/proyecto.png?raw=true)

## Configuraciones

## Levantamiento del Ambiente

### Docker Compose

#### `docker-compose.yml`

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: mysql
    environment:
      MYSQL_ROOT_PASSWORD: 
      MYSQL_DATABASE: microservices
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql
      - ./init-db:/docker-entrypoint-initdb.d
    networks:
      - frtt-network

  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    networks:
      - frtt-network

  account_transaction:
    build:
      context: ./account_transaction
      dockerfile: Dockerfile
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/microservices
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: 1234
      SPRING_RABBITMQ_HOST: rabbitmq
    ports:
      - "8080:8080"
    depends_on:
      - mysql
      - rabbitmq
    networks:
      - frtt-network

  client_person:
    build:
      context: ./client_person
      dockerfile: Dockerfile
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/microservices
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: 
      SPRING_RABBITMQ_HOST: rabbitmq
      CLIENT_SERVICE_URL: http://client_person:8080/client-service
    ports:
      - "8090:8090"
    depends_on:
      - mysql
      - rabbitmq
      - client_person
    networks:
      - frtt-network

networks:
  frtt-network:

volumes:
  mysql-data:
```

### Base de Datos

```sql
-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS microservices;
USE microservices;

-- Table account
CREATE TABLE `account` (
  `account_id` bigint(20) NOT NULL,
  `account_number` bigint(20) NOT NULL,
  `account_type` enum('AHORRO','CORRIENTE') NOT NULL,
  `client_id` int(11) NOT NULL,
  `initial_balance` double NOT NULL,
  `status` enum('FALSE','TRUE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Table account_transactions
CREATE TABLE `account_transactions` (
  `id` bigint(20) NOT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `initial_balance` double DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Table client
CREATE TABLE `client` (
  `client_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` enum('FALSE','TRUE') NOT NULL,
  `person_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Table person
CREATE TABLE `person` (
  `person_id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `age` bigint(20) NOT NULL,
  `gender` enum('F','M') NOT NULL,
  `identification` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


-- Table transaction
CREATE TABLE `transaction` (
  `transaction_id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `balance` double NOT NULL,
  `date` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `transaction_type` enum('DEBITO','DEPOSITO') DEFAULT NULL,
  `account_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

```
![image](https://github.com/freddyrubentorres/img/blob/main/principal/modelo.png?raw=true)


