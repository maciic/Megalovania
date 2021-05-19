-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2021 at 09:24 PM
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
-- Database: `megalovania`
--

-- --------------------------------------------------------

--
-- Table structure for table `invoices`
--

CREATE TABLE `invoices` (
  `id` int(11) NOT NULL,
  `fk_order` int(11) NOT NULL,
  `city_name` varchar(100) NOT NULL,
  `postcode` varchar(4) NOT NULL,
  `address` varchar(100) NOT NULL,
  `house_door_num` varchar(5) NOT NULL,
  `name` int(5) NOT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoices`
--

INSERT INTO `invoices` (`id`, `fk_order`, `city_name`, `postcode`, `address`, `house_door_num`, `name`, `total`) VALUES
(7, 1, 'Mosonmagyaróvár', '9200', 'Törköly utca', '21', 5, NULL),
(8, 2, 'Pécs', '7600', 'Ady Endre utca', '5', 4, NULL),
(14, 17, 'Gyöngyös', '3200', 'asdasdasd', '20', 30, 15000),
(15, 18, 'dfggd', '3000', 'gfgd', '20', 20, 20);

-- --------------------------------------------------------

--
-- Table structure for table `ordered_products`
--

CREATE TABLE `ordered_products` (
  `id` int(11) NOT NULL,
  `db` int(11) NOT NULL,
  `fk_product` int(11) NOT NULL,
  `fk_order` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ordered_products`
--

INSERT INTO `ordered_products` (`id`, `db`, `fk_product`, `fk_order`) VALUES
(1, 2, 1, 1),
(2, 1, 3, 1),
(3, 2, 4, 1),
(4, 3, 1, 2),
(5, 4, 2, 2),
(6, 1, 1, 17),
(7, 1, 2, 17),
(8, 1, 3, 17),
(9, 1, 2, 18);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` varchar(300) NOT NULL,
  `in_stock_qty` int(11) NOT NULL,
  `image` varbinary(8000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `price`, `name`, `description`, `in_stock_qty`, `image`) VALUES
(1, 30000, 'Szék', 'Egy szépen elkészített szék.', 20, ''),
(2, 300000, 'Play Station 9', 'A Legújabb Next-Gen konzol Dual-Shock XII Star-Sense XL Prémium kontrollerrel.', 1, ''),
(3, 20000, 'Razer egér', 'Egy különösen díszes Razer egér.', 6, ''),
(4, 100000, 'Xbox Two', 'Egy nagyon szép konzol.', 4, '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'joska21', 'joskavagyok'),
(2, 'pistike999', 'pistavagyok'),
(3, 'username2', 'password23'),
(4, 'username554', 'password10'),
(5, 'teszt2', 'teszt2pass'),
(7, 'tesztjani', 'janijani'),
(8, 'asdasdasd', 'asdasd'),
(9, 'tesztjános', 'ttt'),
(10, 'a', 'a');

-- --------------------------------------------------------

--
-- Table structure for table `user_orders`
--

CREATE TABLE `user_orders` (
  `id` int(11) NOT NULL,
  `fk_user` int(11) NOT NULL,
  `city_name` varchar(100) NOT NULL,
  `postcode` varchar(4) NOT NULL,
  `address` varchar(100) NOT NULL,
  `house_door_num` varchar(5) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_orders`
--

INSERT INTO `user_orders` (`id`, `fk_user`, `city_name`, `postcode`, `address`, `house_door_num`, `name`) VALUES
(1, 1, 'Mosonmagyaróvár', '9200', 'Törköly utca', '21', '5'),
(2, 3, 'Pécs', '7600', 'Ady Endre utca', '5', '4'),
(17, 9, 'Gyöngyös', '3200', 'HHHHH', '34', '6'),
(18, 9, 'asdasd', '3230', 'fsdfsd', '30', '30');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ordered_products`
--
ALTER TABLE `ordered_products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_orders`
--
ALTER TABLE `user_orders`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `invoices`
--
ALTER TABLE `invoices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `ordered_products`
--
ALTER TABLE `ordered_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user_orders`
--
ALTER TABLE `user_orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
