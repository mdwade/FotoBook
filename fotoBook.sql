-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 01, 2020 at 03:32 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fotoBook`
--

-- --------------------------------------------------------

--
-- Table structure for table `Album`
--

CREATE TABLE `Album` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `theme` varchar(50) NOT NULL,
  `access` varchar(10) NOT NULL,
  `creation_date` date NOT NULL,
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Album`
--

INSERT INTO `Album` (`id`, `name`, `theme`, `access`, `creation_date`, `idUser`) VALUES
(30, 'Maroc', 'Vacances', 'public', '2020-03-09', 2),
(37, 'Senegal', 'Vacances', 'prive', '2020-03-10', 2),
(38, 'Senegal', 'Vacances', 'prive', '2020-03-10', 2),
(69, 'mon deuxième album', 'Vacances', 'prive', '2020-04-01', 31),
(70, 'ATOS', 'travail', 'prive', '2020-04-01', 31),
(71, 'mon premier album', 'Famille', 'prive', '2020-04-01', 31),
(72, 'Thies 2015', 'Vacances', 'prive', '2020-04-01', 31);

-- --------------------------------------------------------

--
-- Table structure for table `ALBUM`
--

CREATE TABLE `ALBUM` (
  `ID` int(11) NOT NULL,
  `ACCESS` varchar(255) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `THEME` varchar(255) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `IMAGE`
--

CREATE TABLE `IMAGE` (
  `ID` int(11) NOT NULL,
  `creation_date` date DEFAULT NULL,
  `DESCRIPTION` longtext DEFAULT NULL,
  `HEIGHT` int(11) DEFAULT NULL,
  `IMAGEFILE` varchar(255) DEFAULT NULL,
  `KEYWORDS` longtext DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `WIDTH` int(11) DEFAULT NULL,
  `idAlbum` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Image`
--

CREATE TABLE `Image` (
  `id` int(11) NOT NULL,
  `title` varchar(40) NOT NULL,
  `description` text NOT NULL,
  `keyWords` text NOT NULL,
  `height` float NOT NULL,
  `width` float NOT NULL,
  `creation_date` date NOT NULL,
  `updated_date` date DEFAULT NULL,
  `imageFile` varchar(255) NOT NULL,
  `idAlbum` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Image`
--

INSERT INTO `Image` (`id`, `title`, `description`, `keyWords`, `height`, `width`, `creation_date`, `updated_date`, `imageFile`, `idAlbum`) VALUES
(78, 'gg', 'er', 'ert', 682, 803, '2020-03-15', NULL, '1584292634796.png', 69),
(79, 'java', 'jjk', 'jk', 682, 803, '2020-03-15', '2020-03-16', '1584296514021.png', 70),
(80, 'Paysage france', 'mon pays', 'france', 350, 1000, '2020-03-16', '2020-03-16', '1584349793775.jpg', 69),
(81, 'ok', 'ok', 'ok', 784, 1250, '2020-03-16', NULL, '1584350015803.jpg', 69),
(82, 'mouhamed', 'mouhamed diop wade', 'ATOS', 810, 1080, '2020-03-16', NULL, '1584355999705.JPG', 70);

-- --------------------------------------------------------

--
-- Table structure for table `shared_album`
--

CREATE TABLE `shared_album` (
  `id` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idAlbum` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `shared_album`
--

INSERT INTO `shared_album` (`id`, `idUser`, `idAlbum`) VALUES
(33, 2, 71);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `age` int(2) NOT NULL,
  `address` text NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(13) NOT NULL,
  `password` varchar(60) NOT NULL,
  `user_type` varchar(10) NOT NULL,
  `register_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`id`, `last_name`, `first_name`, `age`, `address`, `email`, `phone_number`, `password`, `user_type`, `register_date`) VALUES
(2, 'Wade', 'Fatou', 18, 'Marocco', 'fnwade01@gmail.com', '781724964', 'passer', 'ADMIN', '2020-02-21'),
(31, 'Wade', 'Mouhamed', 23, 'GT', 'mdwade37@gmail.com', '781828961', 'Passer@123', 'ADMIN', '2020-03-10'),
(36, 'uiuiu', 'uiuiui', 19, 'Cité Lamy', 'rmmbow@gmail.com', '780165009', 'Ramata@2001', 'SAMPLE', '2020-03-30');

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE `USER` (
  `ID` int(11) NOT NULL,
  `ADDRESS` longtext DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `register_date` date DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`ID`, `ADDRESS`, `AGE`, `EMAIL`, `first_name`, `last_name`, `PASSWORD`, `phone_number`, `register_date`, `user_type`) VALUES
(1, 'Dakar', 23, 'ASDDFSDF', 'SDFDSFSDFD', 'SDFSDFSD', 'SDFSDFD', 'SFDFD', '2020-02-20', 'SDFSDFDSF');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Album`
--
ALTER TABLE `Album`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `ALBUM`
--
ALTER TABLE `ALBUM`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ALBUM_idUser` (`idUser`);

--
-- Indexes for table `IMAGE`
--
ALTER TABLE `IMAGE`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_IMAGE_idAlbum` (`idAlbum`);

--
-- Indexes for table `Image`
--
ALTER TABLE `Image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idAlbum` (`idAlbum`);

--
-- Indexes for table `shared_album`
--
ALTER TABLE `shared_album`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUser` (`idUser`,`idAlbum`),
  ADD KEY `shared_album_ibfk_2` (`idAlbum`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`,`phone_number`);

--
-- Indexes for table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Album`
--
ALTER TABLE `Album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT for table `ALBUM`
--
ALTER TABLE `ALBUM`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `IMAGE`
--
ALTER TABLE `IMAGE`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Image`
--
ALTER TABLE `Image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT for table `shared_album`
--
ALTER TABLE `shared_album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `USER`
--
ALTER TABLE `USER`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Album`
--
ALTER TABLE `Album`
  ADD CONSTRAINT `Album_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `User` (`id`);

--
-- Constraints for table `ALBUM`
--
ALTER TABLE `ALBUM`
  ADD CONSTRAINT `FK_ALBUM_idUser` FOREIGN KEY (`idUser`) REFERENCES `USER` (`ID`);

--
-- Constraints for table `IMAGE`
--
ALTER TABLE `IMAGE`
  ADD CONSTRAINT `FK_IMAGE_idAlbum` FOREIGN KEY (`idAlbum`) REFERENCES `ALBUM` (`ID`);

--
-- Constraints for table `Image`
--
ALTER TABLE `Image`
  ADD CONSTRAINT `Image_ibfk_1` FOREIGN KEY (`idAlbum`) REFERENCES `Album` (`id`);

--
-- Constraints for table `shared_album`
--
ALTER TABLE `shared_album`
  ADD CONSTRAINT `shared_album_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `shared_album_ibfk_2` FOREIGN KEY (`idAlbum`) REFERENCES `Album` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
