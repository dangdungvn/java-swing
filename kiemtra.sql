-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 25, 2024 at 01:06 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kiemtra`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryid` varchar(10) NOT NULL,
  `categoryname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryid`, `categoryname`) VALUES
('MS001', 'Lập Trình'),
('MS002', 'Toán');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `mancc` varchar(10) NOT NULL,
  `tenncc` varchar(50) NOT NULL,
  `diachi` varchar(50) NOT NULL,
  `dienthoai` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productcode` varchar(10) NOT NULL,
  `description` varchar(50) NOT NULL,
  `unitprice` int(11) NOT NULL,
  `onhandquality` int(11) NOT NULL,
  `categoryid` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productcode`, `description`, `unitprice`, `onhandquality`, `categoryid`) VALUES
('123', 'C++', 54000, 20, 'MS001'),
('234', 'Java nâng cao cơ bản', 54000, 20, 'MS001'),
('1233', 'Java nâng cao', 54000, 20, 'MS001'),
('zxc', 'Toán 1', 20000, 20, 'MS002'),
('abc', 'Toán 1', 20000, 20, 'MS002'),
('abccd', 'Toán 1', 20000, 20, 'MS002');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` varchar(10) NOT NULL,
  `tensp` varchar(50) NOT NULL,
  `gia` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `mancc` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryid`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`mancc`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD KEY `fk_student` (`categoryid`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD KEY `mancc` (`mancc`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_student` FOREIGN KEY (`categoryid`) REFERENCES `category` (`categoryid`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `mancc` FOREIGN KEY (`mancc`) REFERENCES `nhacungcap` (`mancc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
