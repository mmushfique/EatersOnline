-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 24, 2022 at 03:22 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eatersonline`
--
CREATE DATABASE IF NOT EXISTS `eatersonline` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `eatersonline`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `username`, `password`, `role`, `status`) VALUES
(1, 'admin', 'admin', '$2a$12$x4bWgpwiD.soI39MGMMy/.cMUpxyXWyoPsAArDmpJQj4IDC/qCok2', 'ADMIN', 1);

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `billno` varchar(20) DEFAULT NULL,
  `ordereduser` varchar(64) NOT NULL,
  `shopusername` varchar(20) NOT NULL,
  `foodcode` varchar(64) NOT NULL,
  `foodname` varchar(20) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `qty` int(11) NOT NULL,
  `total` double NOT NULL,
  `pending` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`id`, `billno`, `ordereduser`, `shopusername`, `foodcode`, `foodname`, `date`, `qty`, `total`, `pending`) VALUES
(22, '0', 'mmushfique12@gmail.com', 'KFC_mw', 'crispychicken', 'Crispy Chicken', '2022-02-24 01:20:14', 1, 800, 1),
(23, '0', 'sanda@gmail.com', 'pizzaHut_mw', 'pizza03', 'BBQ Lover\'s Pizza.', '2022-02-24 01:23:19', 2, 800, 0),
(24, '0', 'sanda@gmail.com', 'pizzaHut_mw', 'pizza03', 'BBQ Lover\'s Pizza.', '2022-02-24 01:24:07', 1, 400, 0),
(25, '0', 'sanda@gmail.com', 'pizzaHut_mw', 'pizza02', 'chicken pizza', '2022-02-24 01:25:53', 1, 700, 0),
(26, '0', 'sanda@gmail.com', 'pizzaHut_mw', 'pizza02', 'chicken pizza', '2022-02-24 01:27:35', 1, 700, 0),
(27, '0', 'mmushfique12@gmail.com', 'pizzaHut_mw', 'pizza02', 'chicken pizza', '2022-02-24 03:07:36', 1, 700, 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `deposit` double NOT NULL,
  `role` varchar(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `fname`, `lname`, `email`, `password`, `address`, `phone`, `deposit`, `role`, `status`) VALUES
(1, 'Indika', 'Indika', 'sanda@gmail.com', '$2a$10$dSYPu9ntsFJ40IPBId.qouzMwaMVJurVL6jIPdSAD4u6JpcGVLZ/y', 'Mologoda', '0', 1000, 'CUSTOMER', 0),
(6, 'mohamed', 'mushfique', 'mmushfique12@gmail.com', '$2a$12$3dMfFX6HDmIxcZyUHVMG/O1aR5dJHHEDbGFMmIXWvt4lpTAjIz1E6', 'kegalle', '74837393', 1000, 'CUSTOMER', 0),
(9, 'qq', 'ww', 'sa@gmail.com', '$2a$10$xzcDImDzfAgbeBNuGmpYAexa/L3dDFVpRWiWs41Iqo0EkwsmPdE7W', 'Colombo', '12333983', 2000, 'CUSTOMER', 0);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` int(11) NOT NULL,
  `foodcode` varchar(64) DEFAULT NULL,
  `foodname` varchar(64) NOT NULL,
  `shopusername` varchar(20) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `foodcode`, `foodname`, `shopusername`, `price`, `image`) VALUES
(10, 'pizza01', 'cheese pizza', 'pizzaHut_mw', 520, 'chpizza.jpg'),
(11, 'pizza02', 'chicken pizza', 'pizzaHut_mw', 700, 'chcknpizza.jpg'),
(12, 'pizza03', 'BBQ Lover\'s Pizza.', 'pizzaHut_mw', 400, 'bbqpizza.jpg'),
(15, 'crispychicken', 'Crispy Chicken', 'KFC_mw', 800, 'grillChkn.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL,
  `shopname` varchar(20) NOT NULL,
  `shopusername` varchar(20) NOT NULL,
  `email` varchar(64) NOT NULL,
  `phone` int(11) NOT NULL,
  `location` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `deposit` double NOT NULL,
  `onlinestatus` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'SHOP'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shop`
--

INSERT INTO `shop` (`id`, `shopname`, `shopusername`, `email`, `phone`, `location`, `password`, `deposit`, `onlinestatus`, `status`, `role`) VALUES
(1, 'KFC', 'KFC_mw', 'kfcMW@gmail.com', 192834756, 'Mawanella', '$2a$10$uR18hnrHc8DySvm1v3KKvOwtqxDymnTPMzKANgMJ/8PnqVDgGDOaq', 0, 0, 0, 'SHOP'),
(11, 'pizza Hut', 'pizzaHut_mw', 'pizzaHut_mw@gmail.com', 1020020, 'kegalle', '$2a$12$Mro/3pZs3dlOi9zHwbHpOuuarl8ZKhxDifF7.HCG6wXoiTHa93FOi', 0, 1, 0, 'SHOP');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`username`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bill_ibfk_1` (`foodcode`),
  ADD KEY `ordereduser` (`ordereduser`),
  ADD KEY `bill_ibfk_3` (`shopusername`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `foodcode` (`foodcode`),
  ADD KEY `food_ibfk_1` (`shopusername`);

--
-- Indexes for table `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `shopusername` (`shopusername`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `shop`
--
ALTER TABLE `shop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`foodcode`) REFERENCES `food` (`foodcode`) ON UPDATE CASCADE,
  ADD CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`ordereduser`) REFERENCES `customer` (`email`) ON UPDATE CASCADE,
  ADD CONSTRAINT `bill_ibfk_3` FOREIGN KEY (`shopusername`) REFERENCES `shop` (`shopusername`) ON UPDATE CASCADE;

--
-- Constraints for table `food`
--
ALTER TABLE `food`
  ADD CONSTRAINT `food_ibfk_1` FOREIGN KEY (`shopusername`) REFERENCES `shop` (`shopusername`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
