# Client_Person


## Introducción

El `client_person` se encarga de gestionar los datos de los clientes.

## Estructura del Proyecto

### client_person


![image](https://github.com/freddyrubentorres/img/blob/main/client_person/proyecto.png?raw=true)


## Configuraciones

#### `application.properties`

```properties
  ## server
  server.port=8082

  ## spring.application
  spring.application.name=cliente_persona

  ## spring.datasource
  spring.datasource.url=jdbc:mysql://localhost/microservices
  spring.datasource.driverClassName=com.mysql.jdbc.Driver
  spring.datasource.username=root
  spring.datasource.password=

  ## spring.JPA
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.format_sql=true
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
  spring.jpa.properties.hibernate.transaction.manager_lookup_class=org.hibernate.transaction.JDBCTransactionFactory

  ## rabbitmq.queue
  rabbitmq.queue.name=ms.clientes.queue
  rabbitmq.queue.exchange=ms.clientes.exchange
  rabbitmq.queue.routing.key=ms.clientes.routingKey

  ## AES
  aes.secret.key=mBTohJeHzhN2dJmPZY0qBlnOWMkF9shDVs1EO7RrwEs=
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

Se han implementado pruebas unitarias para el `client_person`, configuradas con Jacoco.

![image](https://github.com/freddyrubentorres/img/blob/main/client_person/jacoco.png?raw=true)

## Comunicación entre Servicios

### Sincrónica

Se utiliza OpenFeign para la comunicación sincrónica entre `account_transaction` y `client_person`.

### Asincrónica

Se utiliza RabbitMQ para la comunicación asincrónica entre los servicios. Cuando se inserta un cliente, se envía un mensaje a RabbitMQ que es consumido por `account_transaction` donde crea la cuenta y el respectivo movimiento.

## RESTful API ##

&nbsp;
**1. API Description for Project**

METHOD | PATH | DESCRIPTION 
------------|-----|------------
GET | /api/clients | Extrae todos los clientes.
GET | /api/clients/identification/{id} | Extrae el cliente que presenta una identificacion especifica.
POST | /api/clients | Crea un cliente
DELETE | /api/clients/{code} | Borra un cliente por su codigo.
PATCH | /api/clients/{code} | Actualiza los campos del cliente.


## Ejecución & Validaciones

### Validaciones

![image](https://github.com/freddyrubentorres/img/blob/main/client_person/validaciones.png?raw=true)

### Ejecución

GET CLIENT

![image](https://github.com/freddyrubentorres/img/blob/main/client_person/getClient.png?raw=true)

GET CLIENT NO EXIST

![image](https://github.com/freddyrubentorres/img/blob/main/client_person/getClientByIdentificationNoExist.png?raw=true)

POST CLIENT

![image](https://github.com/freddyrubentorres/img/blob/main/client_person/postClient.png?raw=true)

POST CLIENT OK

![image](https://github.com/freddyrubentorres/img/blob/main/client_person/postClientOK.png?raw=true)

GET ACCOUNT OK

![image](https://github.com/freddyrubentorres/img/blob/main/client_person/getAccount.png?raw=true)

REPORT
![image](https://github.com/freddyrubentorres/img/blob/main/client_person/report.png?raw=true)

UPDATE  
![image](https://github.com/freddyrubentorres/img/blob/main/client_person/updateClient.png?raw=true)