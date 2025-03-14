-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-03-2025 a las 03:41:47
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `microservices`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `view_accounttransactions` (IN `identification_param` VARCHAR(20), IN `start_date` DATE, IN `end_date` DATE)   BEGIN
SELECT
b.date AS date,
	d.name AS name,
	d.last_name AS last_name,
	a.account_number AS account_number,
    a.initial_balance AS initial_balance,
	a.account_type AS account_type,
    b.transaction_type AS transaction_type,
    b.description AS description,
	b.amount AS amount,
	b.balance AS balance
    FROM account a
    INNER JOIN transaction b ON a.account_id = b.account_id
    INNER JOIN client c ON a.client_id = c.client_id
    INNER JOIN person d ON c.person_id = d.person_id
    WHERE d.identification = identification_param
    AND b.date BETWEEN start_date AND DATE_ADD(end_date,interval 1 day)
	order by b.date DESC;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `account`
--

CREATE TABLE `account` (
  `account_id` bigint(20) NOT NULL,
  `account_number` bigint(20) NOT NULL,
  `account_type` enum('AHORRO','CORRIENTE') NOT NULL,
  `client_id` int(11) NOT NULL,
  `initial_balance` double NOT NULL,
  `status` enum('FALSE','TRUE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `account`
--

INSERT INTO `account` (`account_id`, `account_number`, `account_type`, `client_id`, `initial_balance`, `status`) VALUES
(1, 953347, 'AHORRO', 1, 2000, 'TRUE'),
(2, 345625, 'CORRIENTE', 2, 100, 'TRUE'),
(3, 345246, 'AHORRO', 3, 10, 'TRUE'),
(4, 701433, 'AHORRO', 2, 540, 'TRUE'),
(5, 895878, 'CORRIENTE', 1, 1000, 'TRUE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `account_transactions`
--

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `client_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` enum('FALSE','TRUE') NOT NULL,
  `person_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`client_id`, `email`, `password`, `status`, `person_id`) VALUES
(1, 'joselema@gmail.com', 'DlRoh4aN6HWX3xJ+iuH0lA==', 'TRUE', 1),
(2, 'marianelamontalvo@gmail.com', 'DlRoh4aN6HWX3xJ+iuH0lA==', 'TRUE', 2),
(3, 'juanosorio@gmail.com', 'DlRoh4aN6HWX3xJ+iuH0lA==', 'TRUE', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `person_id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `age` bigint(20) NOT NULL,
  `gender` enum('F','M') NOT NULL,
  `identification` varchar(255) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`person_id`, `address`, `age`, `gender`, `identification`, `last_name`, `name`, `phone`) VALUES
(1, 'Otavalo sn y principal', 23, 'M', '1738579354', 'Lema', 'Jose', '0982547851'),
(2, 'Amazonas y  NNUU', 54, 'F', '1727363846', 'Montalvo', 'Marianela', '0975489652'),
(3, '13 junio y Equinoccial', 35, 'M', '1728378467', 'Osorio', 'Juan', '0988745873');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `balance` double NOT NULL,
  `date` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `transaction_type` enum('DEBITO','DEPOSITO') DEFAULT NULL,
  `account_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `amount`, `balance`, `date`, `description`, `transaction_type`, `account_id`) VALUES
(1, 2000, 2000, '2025-03-13 21:04:42.000000', 'APERTURA DE CUENTA', 'DEPOSITO', 1),
(2, 100, 100, '2025-03-13 21:06:48.000000', 'APERTURA DE CUENTA', 'DEPOSITO', 2),
(3, 10, 10, '2025-03-13 21:12:16.000000', 'APERTURA DE CUENTA', 'DEPOSITO', 3),
(4, 540, 540, '2025-03-13 21:18:28.000000', 'APERTURA DE CUENTA', 'DEPOSITO', 4),
(5, 1000, 1000, '2025-03-13 21:19:27.000000', 'APERTURA DE CUENTA', 'DEPOSITO', 5),
(6, -575, 1425, '2025-03-13 21:24:33.000000', 'RETIRO', 'DEBITO', 1),
(7, 600, 700, '2025-03-13 21:26:30.000000', 'DEPOSITO', 'DEPOSITO', 2),
(8, 150, 160, '2025-03-13 21:26:55.000000', 'DEPOSITO', 'DEPOSITO', 3),
(9, -540, 0, '2025-03-13 21:30:00.000000', 'RETIRO', 'DEBITO', 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`),
  ADD UNIQUE KEY `UKflt7u0k5t3citp7q2or59b4ii` (`account_number`);

--
-- Indices de la tabla `account_transactions`
--
ALTER TABLE `account_transactions`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`person_id`),
  ADD UNIQUE KEY `UKbfgjs3fem0hmjhvih80158x29` (`email`),
  ADD UNIQUE KEY `UKbfjdoy2dpussylq7g1s3s1tn8` (`client_id`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`person_id`),
  ADD UNIQUE KEY `UK4r2cs4eybw7joyi0u8v7vywhg` (`identification`);

--
-- Indices de la tabla `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `FK6g20fcr3bhr6bihgy24rq1r1b` (`account_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `account`
--
ALTER TABLE `account`
  MODIFY `account_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `person`
--
ALTER TABLE `person`
  MODIFY `person_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKkxflpsue6s9kscgmuwt7ob1f3` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`);

--
-- Filtros para la tabla `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK6g20fcr3bhr6bihgy24rq1r1b` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
