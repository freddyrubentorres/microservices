# Account_Transaction


## Introducción

El `account_transaction` se encarga de gestionar los datos de las cuentas y respectivos movimientos.

## Estructura del Proyecto

### account_transaction


![image](https://github.com/freddyrubentorres/img/blob/main/account_transaction/project.png?raw=true)


## Configuraciones

#### `application.properties`

```properties
  ## server
server.port=8081

## spring.application
spring.application.name=account_transaction

## spring.datasource
spring.datasource.url=jdbc:mysql://localhost/microservices
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=

## spring.JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

## rabbitmq.queue
rabbitmq.queue.name=ms.account_transaction.queue
rabbitmq.queue.exchange=ms.account_transaction.exchange
rabbitmq.queue.routing.key=ms.account_transaction.routingKey
```


### Inicialización de la Base de Datos

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

## Pruebas

### Pruebas Unitarias

Se han implementado pruebas unitarias para el `account_transaction`, configuradas con Jacoco.

![image](https://github.com/freddyrubentorres/img/blob/main/account_transaction/jacoco.png?raw=true)

## Comunicación entre Servicios

### Sincrónica

Se utiliza OpenFeign para la comunicación sincrónica entre `account_transaction` y `client_person`.

### Asincrónica

Se utiliza RabbitMQ para la comunicación asincrónica entre los servicios. Cuando se inserta un cliente, se envía un mensaje a RabbitMQ que es consumido por `account_transaction` donde crea la cuenta y el respectivo movimiento.

## RESTful API ##

&nbsp;
**1. API Description for Project**

ACCOUNT

METHOD | PATH | DESCRIPTION 
------------|-----|------------
GET | /api/accounts | Extrae todas las cuentas registradas.
GET | /api/accounts/id/{id} | Extrae la información de una cuenta que presenta un id especifica.
POST | /api/accounts | Crea un nueva cuenta.
DELETE | /api/accounts/{code} | Borra un cuenta por su codigo.

TRANSACTION

METHOD | PATH | DESCRIPTION 
------------|-----|------------
GET | /api/transactions/client/{id} | Extrae la información de un movimiento que presenta un id especifico.
POST | /api/transactions | Crea un nuevo movimiento.

REPORT

METHOD | PATH                                                                                 | DESCRIPTION 
------------|--------------------------------------------------------------------------------------|------------
GET | /api/reports?startDate={startDate}&endDate={endDate}&identification={identification} | Extrae la información del reporte que presenta una identificación en un ranfo de fechas.


## Ejecución & Validaciones

### Ejecución

GET ACCOUNT

![image](https://github.com/freddyrubentorres/img/blob/main/account_transaction/getAccount.png?raw=true)

GET ACCOUNT BY ID

![image](https://github.com/freddyrubentorres/img/blob/main/account_transaction/getAccountById.png?raw=true)

POST ACCOUNT

![image](https://github.com/freddyrubentorres/img/blob/main/account_transaction/createAccount.png?raw=true)

TRANSACTION CREATE

![image](https://github.com/freddyrubentorres/img/blob/main/account_transaction/transactionRetiro.png?raw=true)

GET TRANSACTION BY CLIENT

![image](https://github.com/freddyrubentorres/img/blob/main/account_transaction/transactionByClient.png?raw=true)

REPORT

![image](https://github.com/freddyrubentorres/img/blob/main/account_transaction/report.png?raw=true)