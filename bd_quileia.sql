-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2021 at 09:01 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bd_quileia`
--

-- --------------------------------------------------------

--
-- Table structure for table `electrodomesticos`
--

CREATE TABLE `electrodomesticos` (
  `mac` varchar(17) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `ip` varchar(20) NOT NULL,
  `conexionActual` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `electrodomesticos`
--

INSERT INTO `electrodomesticos` (`mac`, `tipo`, `ip`, `conexionActual`) VALUES
('A2:3B:1D:54:6B:32', 'Computador', '196.5.4.44', 2),
('A2:3B:1D:54:6B:36', 'celular', '196.5.4.88', 4),
('B2:3B:1D:54:8B:32', 'celular', '195.5.4.44', 2),
('C2:3B:4D:56:6B:38', 'Computador', '192.12.4.04', 2);

-- --------------------------------------------------------

--
-- Stand-in structure for view `electroh`
-- (See below for the actual view)
--
CREATE TABLE `electroh` (
`mac` varchar(17)
,`tipo` varchar(20)
,`ip` varchar(20)
,`conexionActual` int(11)
,`conectadoRed` tinyint(1)
,`fecha` datetime
);

-- --------------------------------------------------------

--
-- Table structure for table `historial`
--

CREATE TABLE `historial` (
  `mac` varchar(17) NOT NULL,
  `conectadoRed` tinyint(1) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `historial`
--

INSERT INTO `historial` (`mac`, `conectadoRed`, `fecha`) VALUES
('A2:3B:1D:54:6B:32', 1, '2021-03-26 09:19:39'),
('A2:3B:1D:54:6B:36', 1, '2021-03-26 09:19:50'),
('A2:3B:1D:54:6B:32', 0, '2021-03-26 09:19:59'),
('A2:3B:1D:54:6B:32', 1, '2021-03-26 09:20:07'),
('A2:3B:1D:54:6B:32', 0, '2021-03-26 09:22:17'),
('A2:3B:1D:54:6B:32', 1, '2021-03-26 09:22:29'),
('A2:3B:1D:54:6B:32', 0, '2021-03-26 09:33:28'),
('B2:3B:1D:54:8B:32', 1, '2021-03-26 09:41:58'),
('C2:3B:4D:56:6B:38', 1, '2021-03-26 12:34:14'),
('A2:3B:1D:54:6B:32', 1, '2021-03-26 13:52:22'),
('A2:3B:1D:54:6B:32', 0, '2021-03-26 13:52:43'),
('A2:3B:1D:54:6B:32', 1, '2021-03-26 13:56:32'),
('A2:3B:1D:54:6B:32', 0, '2021-03-26 14:25:43'),
('A2:3B:1D:54:6B:32', 1, '2021-03-26 14:27:02'),
('A2:3B:1D:54:6B:32', 0, '2021-03-26 14:46:39');

--
-- Triggers `historial`
--
DELIMITER $$
CREATE TRIGGER `fecha` BEFORE INSERT ON `historial` FOR EACH ROW BEGIN
  SET NEW.fecha=CURRENT_TIMESTAMP();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `red`
--

CREATE TABLE `red` (
  `nombreRed` varchar(50) NOT NULL,
  `tipo` int(11) NOT NULL,
  `tipoCifrado` varchar(5) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contrasena` varchar(250) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `red`
--

INSERT INTO `red` (`nombreRed`, `tipo`, `tipoCifrado`, `usuario`, `contrasena`, `id`) VALUES
('Marlon', 2, 'LAN', 'mscastanom@correo.udistrital.edu.co', 'lacasaestaenfuego', 1),
('Miller barahona', 1, 'WEP', 'miller@correo.udistrital.edu.co', 'lacasaestaenfuego', 2),
('Julian Sarmiento', 1, 'WPA2', 'Julian@correo.udistrital.edu.co', 'lacasaestaenfuego', 3),
('Paco', 1, 'WPA', 'Paco@correo.udistrital.edu.co', '123456', 4);

-- --------------------------------------------------------

--
-- Structure for view `electroh`
--
DROP TABLE IF EXISTS `electroh`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `electroh`  AS  select `electrodomesticos`.`mac` AS `mac`,`electrodomesticos`.`tipo` AS `tipo`,`electrodomesticos`.`ip` AS `ip`,`electrodomesticos`.`conexionActual` AS `conexionActual`,`historial`.`conectadoRed` AS `conectadoRed`,`historial`.`fecha` AS `fecha` from (`electrodomesticos` join `historial`) where `electrodomesticos`.`mac` = `historial`.`mac` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `electrodomesticos`
--
ALTER TABLE `electrodomesticos`
  ADD PRIMARY KEY (`mac`),
  ADD KEY `conexionActual` (`conexionActual`);

--
-- Indexes for table `historial`
--
ALTER TABLE `historial`
  ADD KEY `mac` (`mac`);

--
-- Indexes for table `red`
--
ALTER TABLE `red`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `red`
--
ALTER TABLE `red`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `electrodomesticos`
--
ALTER TABLE `electrodomesticos`
  ADD CONSTRAINT `electrodomesticos_ibfk_1` FOREIGN KEY (`conexionActual`) REFERENCES `red` (`id`);

--
-- Constraints for table `historial`
--
ALTER TABLE `historial`
  ADD CONSTRAINT `historial_ibfk_1` FOREIGN KEY (`mac`) REFERENCES `electrodomesticos` (`mac`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
