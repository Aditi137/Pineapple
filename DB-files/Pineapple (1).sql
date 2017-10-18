-- phpMyAdmin SQL Dump
-- version 4.7.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Oct 18, 2017 at 08:47 AM
-- Server version: 5.6.35
-- PHP Version: 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Pineapple`
--

-- --------------------------------------------------------

--
-- Table structure for table `Accounts`
--

CREATE TABLE `Accounts` (
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `id` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `account_type` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Accounts`
--

INSERT INTO `Accounts` (`name`, `email`, `id`, `password`, `account_type`) VALUES
('', '', '', '', '0'),
('Madeleine', 'mgartz@kth.se', '1234', 'ntu', '0'),
('Filip', 'filipjad@kth.se', '1337', 'hejhej', '0'),
('Filip', 'abc@gmail.com', 'fille123', '123456', '0'),
('Helge Bengtsson', 'hej@123.se', 'HelgeZZ', 'komohjaelpmig', '1'),
('made', 'mad@d.e', 'madekung', 'hejhej', '0');

-- --------------------------------------------------------

--
-- Table structure for table `Relations`
--

CREATE TABLE `Relations` (
  `s_id` varchar(20) NOT NULL,
  `u_id` varchar(20) NOT NULL,
  `confirmed` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Relations`
--

INSERT INTO `Relations` (`s_id`, `u_id`, `confirmed`) VALUES
('HelgeZZ', '1337', 'YES'),
('HelgeZZ', 'fille123', 'YES'),
('HelgeZZ', 'madekung', 'NO'),
('madekung', '1337', 'YES');

-- --------------------------------------------------------

--
-- Table structure for table `Reminders`
--

CREATE TABLE `Reminders` (
  `u_id` varchar(20) NOT NULL,
  `s_id` varchar(20) NOT NULL,
  `date` varchar(20) NOT NULL,
  `time` varchar(20) NOT NULL,
  `details` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Accounts`
--
ALTER TABLE `Accounts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `Relations`
--
ALTER TABLE `Relations`
  ADD UNIQUE KEY `s_id` (`s_id`,`u_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
