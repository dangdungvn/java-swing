-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 22, 2024 lúc 02:02 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `benhviennoitru`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bacsi`
--

CREATE TABLE `bacsi` (
  `MaBS` varchar(30) NOT NULL,
  `TenBS` varchar(50) NOT NULL,
  `KinhNghiem` varchar(30) NOT NULL,
  `ChuyenKhoa` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `bacsi`
--

INSERT INTO `bacsi` (`MaBS`, `TenBS`, `KinhNghiem`, `ChuyenKhoa`) VALUES
('BS001', 'Nguyen Van K', '10', 'Nội khoa'),
('BS002', 'Tran Thi L', '8', 'Ngoại khoa'),
('BS003', 'Le Van M', '12', 'Sản phụ khoa'),
('BS004', 'Pham Thi N', '5', 'Nhi khoa'),
('BS005', 'Hoang Van Oạch', '15', 'Tim mạch'),
('BS006', 'Nguyen Van P', '7', 'Thần kinh'),
('BS007', 'Tran Thi Q', '6', 'Tai mũi họng'),
('BS008', 'Le Van R', '9', 'Da liễu'),
('BS009', 'Pham Thi S', '11', 'Hô hấp'),
('BS010', 'Hoang Van T', '14', 'Chấn thương chỉnh hình'),
('BS011', 'Hoang Van T', '14', 'Hô hấp'),
('BS077', 'Bùi Hiền Lợn', '23', 'Sản phụ khoa');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `benhnhan`
--

CREATE TABLE `benhnhan` (
  `MaBN` varchar(30) NOT NULL,
  `TenBN` varchar(50) NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` varchar(10) NOT NULL,
  `CCCD` varchar(20) NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `BHYT` varchar(100) NOT NULL,
  `DienThoai` varchar(15) NOT NULL,
  `TinhTrang` varchar(50) NOT NULL,
  `NgayDKKham` date DEFAULT NULL,
  `NgayRaVien` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `benhnhan`
--

INSERT INTO `benhnhan` (`MaBN`, `TenBN`, `NgaySinh`, `GioiTinh`, `CCCD`, `DiaChi`, `BHYT`, `DienThoai`, `TinhTrang`, `NgayDKKham`, `NgayRaVien`) VALUES
('BN001', 'Nguyen Van A', '0005-11-20', 'Nam', '123456789', 'Hà Nội', 'BHYT001', '0123456789', 'Đã Khám', '2024-02-01', NULL),
('BN002', 'Tran Thi B', '1990-02-20', 'Nu', '987654321', 'Hà Nội', 'BHYT002', '0987654321', 'Đã Khám', '2020-12-20', NULL),
('BN003', 'Le Van C', '1978-04-10', 'Nam', '234567890', 'Hải Phòng', 'BHYT003', '0234567890', 'Đã Xuất Viện', '2018-04-13', NULL),
('BN004', 'Pham Thi D', '1992-09-30', 'Nu', '345678901', 'Đà Nẵng', 'BHYT004', '0345678901', 'Đã Khám', '2016-02-13', NULL),
('BN005', 'Hoang Van E', '1980-12-25', 'Nam', '456789012', 'TP HCM', 'BHYT005', '0456789012', '', '2024-01-02', NULL),
('BN006', 'Nguyen Van F', '1995-03-15', 'Nam', '567890123', 'Hà Nội', 'BHYT006', '0567890123', '', '2024-01-23', NULL),
('BN007', 'Tran Thi G', '1988-07-05', 'Nu', '678901234', 'Hải Phòng', 'BHYT007', '0678901234', 'Đã Nhập Viện', '2024-01-24', NULL),
('BN008', 'Le Van H', '1991-11-11', 'Nam', '789012345', 'Đà Nẵng', 'BHYT008', '0789012345', '', '2024-02-05', NULL),
('BN009', 'Pham Thi I', '1983-08-21', 'Nu', '890123456', 'TP HCM', 'BHYT009', '0890123456', '', '2024-02-11', NULL),
('BN010', 'Hoang Van J', '1975-01-01', 'Nam', '901234567', 'Hà Nội', 'BHYT010', '0901234567', 'Đã Nhập Viện', '2024-10-09', NULL),
('BN011', 'Nguyen Van A', '0020-11-05', 'Nam', '123456789', 'Hà Nội', 'BHYT001', '0123456789', '', '2022-12-11', NULL),
('BN012', 'Nguyen Van A', '0005-11-20', 'Nam', '123456789', 'Hà Nội', 'BHYT001', '0123456789', '', '2021-03-08', NULL),
('BN013', 'Nguyen Van A', '0020-11-05', 'Nam', '123456789', 'Hà Nội', 'BHYT001', '0123456789', 'Đã Khám', '2021-08-21', NULL),
('BN014', 'Pham Thi I', '0027-01-04', 'Nu', '890123456', 'TP HCM', 'BHYT009', '0890123456', 'Đã Khám', '2020-09-16', NULL),
('BN015', 'Pham Thi D', '0036-02-13', 'Nu', '345678901', 'Đà Nẵng', 'BHYT004', '0345678901', 'Chờ Xét Nghiệm', '2023-04-22', NULL),
('BN016', 'Pham Thi D', '1990-01-02', 'Nu', '345678901', 'Đà Nẵng', 'BHYT004', '0345678901', 'Chờ Xét Nghiệm', '2021-12-11', NULL),
('BN017', 'Nguyen Van A', '2024-10-09', 'Nam', '123456789', 'Hà Nội', 'BHYT001', '0123456789', 'Chờ Xét Nghiệm', '2014-05-23', NULL),
('BN031', 'Nguyen Van A', '2000-10-10', 'Nam', '123456789', 'Hà Nội', 'BHYT001', '0123456789', 'Đã Khám', '2024-10-21', NULL),
('BN032', 'Nguyen Van A', '2000-02-02', 'Nam', '123456789', 'Hà Nội', 'BHYT001', '0123456789', 'Đã Xuất Viện', '2024-10-19', '2024-10-22');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chuyenkhoa`
--

CREATE TABLE `chuyenkhoa` (
  `ChuyenKhoa` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chuyenkhoa`
--

INSERT INTO `chuyenkhoa` (`ChuyenKhoa`) VALUES
('Nội khoa'),
('Ngoại khoa'),
('Sản phụ khoa'),
('Nhi khoa'),
('Tim mạch'),
('Thần kinh'),
('Tai mũi họng'),
('Da liễu'),
('Hô hấp'),
('Chấn thương chỉnh hình');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dieutri`
--

CREATE TABLE `dieutri` (
  `MaBN` varchar(30) NOT NULL,
  `SoNgayNhapVien` int(11) NOT NULL,
  `Ngay` date NOT NULL,
  `Thuoc` varchar(50) NOT NULL,
  `SoLuongThuoc` int(11) NOT NULL CHECK (`SoLuongThuoc` > 0),
  `ThanhTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dieutri`
--

INSERT INTO `dieutri` (`MaBN`, `SoNgayNhapVien`, `Ngay`, `Thuoc`, `SoLuongThuoc`, `ThanhTien`) VALUES
('BN001', 3, '2023-09-01', 'Metoprolol', 2, 20000),
('BN002', 5, '2023-09-02', 'Atenolol', 1, 20000),
('BN003', 1, '2023-09-03', 'Nebivolol', 3, 45000),
('BN004', 4, '2023-09-04', 'Amlodipine', 2, 50000),
('BN005', 2, '2023-09-05', 'Divalproex', 5, 5000),
('BN006', 3, '2023-09-06', 'Olanzapine', 4, 52000),
('BN007', 2, '2023-09-07', 'Fluconazole', 3, 36000),
('BN008', 1, '2023-09-08', 'Zolpidem', 1, 10000),
('BN009', 4, '2023-09-09', 'Eszopiclone', 1, 11000),
('BN010', 5, '2023-09-10', 'Ticagrelor', 1, 17000),
('BN017', 3804, '0027-04-16', 'Metoprolol', 2, 20000),
('BN017', 3804, '0027-04-16', 'Nebivolol', 2, 100000),
('BN032', 2, '2024-10-21', 'Metoprolol', 2, 10000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giathuoc`
--

CREATE TABLE `giathuoc` (
  `Thuoc` varchar(50) NOT NULL,
  `GiaTien` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `giathuoc`
--

INSERT INTO `giathuoc` (`Thuoc`, `GiaTien`) VALUES
('Metoprolol', 10000.00),
('Atenolol', 20000.00),
('Vastarel', 15000.00),
('Amlodipine', 25000.00),
('Nebivolol', 50000.00),
('Divalproex', 1000.00),
('Olanzapine', 13000.00),
('Fluconazole', 12000.00),
('Zolpidem', 10000.00),
('Eszopiclone', 11000.00),
('Ticagrelor', 17000.00),
('Vitamin', 20000.00),
('Meclizine', 10000.00),
('Tolterodine', 15000.00),
('Trazodone', 20000.00),
('Duloxetine', 30000.00),
('Losartan', 35000.00),
('Ramipril', 45000.00),
('Estrogen', 43000.00),
('Niacin', 15000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giuongbenh`
--

CREATE TABLE `giuongbenh` (
  `ChuyenKhoa` varchar(50) NOT NULL,
  `LoaiPhong` varchar(50) NOT NULL,
  `SoPhong` varchar(50) NOT NULL,
  `SoGiuong` varchar(50) NOT NULL,
  `MaBN` varchar(30) NOT NULL,
  `MaBS` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `giuongbenh`
--

INSERT INTO `giuongbenh` (`ChuyenKhoa`, `LoaiPhong`, `SoPhong`, `SoGiuong`, `MaBN`, `MaBS`) VALUES
('Nội khoa', 'Giường bệnh thường', '101', '1', 'BN001', 'BS001'),
('Ngoại khoa', 'Giường bệnh VIP', '202', '2', 'BN002', 'BS002'),
('Sản phụ khoa', 'Giường bệnh thường', '301', '1', 'BN003', 'BS003'),
('Nhi khoa', 'Giường bệnh thường', '403', '3', 'BN004', 'BS004'),
('Tim mạch', 'Giường bệnh đặc biệt', '502', '1', 'BN005', 'BS005'),
('Thần kinh', 'Giường bệnh thường', '602', '1', 'BN006', 'BS006'),
('Tai mũi họng', 'Giường bệnh thường', '701', '1', 'BN007', 'BS007'),
('Da liễu', 'Giường bệnh thường', '801', '2', 'BN008', 'BS008'),
('Hô hấp', 'Giường bệnh thường', '902', '3', 'BN009', 'BS009'),
('Chấn thương chỉnh hình', 'Giường bệnh VIP', '1003', '1', 'BN010', 'BS010');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phongkham`
--

CREATE TABLE `phongkham` (
  `PhongKham` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phongkham`
--

INSERT INTO `phongkham` (`PhongKham`) VALUES
('A1'),
('A2'),
('B1'),
('B2'),
('C1'),
('C2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `TaiKhoan` varchar(30) NOT NULL,
  `MatKhau` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`TaiKhoan`, `MatKhau`, `Email`) VALUES
('a', 'a', 'a'),
('dangdungvn', '123', 'tung@gmail.com'),
('tung', '123', 'tung@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtinkhambenh`
--

CREATE TABLE `thongtinkhambenh` (
  `MaBS` varchar(30) NOT NULL,
  `NgayKham` date NOT NULL,
  `PhongKham` varchar(10) NOT NULL,
  `MaBN` varchar(30) NOT NULL,
  `ChuyenKhoa` varchar(50) NOT NULL,
  `CanNang` varchar(50) NOT NULL,
  `NhomMau` varchar(5) NOT NULL,
  `NhietDo` varchar(30) NOT NULL,
  `Mach` varchar(30) NOT NULL,
  `HuyetAp` varchar(20) NOT NULL,
  `NhipTho` varchar(30) NOT NULL,
  `LyDoKham` varchar(500) NOT NULL,
  `TinhTrangHienTai` varchar(500) NOT NULL,
  `ChuanDoanSoBo` varchar(500) NOT NULL,
  `HuongDieuTri` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thongtinkhambenh`
--

INSERT INTO `thongtinkhambenh` (`MaBS`, `NgayKham`, `PhongKham`, `MaBN`, `ChuyenKhoa`, `CanNang`, `NhomMau`, `NhietDo`, `Mach`, `HuyetAp`, `NhipTho`, `LyDoKham`, `TinhTrangHienTai`, `ChuanDoanSoBo`, `HuongDieuTri`) VALUES
('BS001', '2023-11-22', 'A1', 'BN001', 'Nội khoa', '65', 'A', '37.5', '80', '120/80', '20', 'Đau đầu', 'Mệt mỏi', 'Cảm cúm', 'Uống thuốc hạ sốt'),
('BS002', '2023-11-23', 'B2', 'BN002', 'Ngoại khoa', '70', 'B', '36.8', '75', '110/70', '18', 'Đau bụng', 'Buồn nôn', 'Viêm dạ dày', 'Điều chỉnh chế độ ăn'),
('BS003', '2023-11-24', 'C1', 'BN003', 'Sản phụ khoa', '55', 'O', '37.2', '90', '130/90', '22', 'Đau ngực', 'Khó thở', 'Cao huyết áp', 'Uống thuốc điều trị'),
('BS004', '2023-11-25', 'A2', 'BN004', 'Nhi khoa', '62', 'AB', '36.5', '85', '125/85', '21', 'Ho', 'Sốt', 'Viêm phế quản', 'Uống kháng sinh'),
('BS005', '2023-11-26', 'B1', 'BN005', 'Tim mạch', '75', 'A', '37.0', '78', '115/75', '19', 'Đau khớp', 'Sưng khớp', 'Viêm khớp', 'Vật lý trị liệu'),
('BS006', '2023-11-27', 'C2', 'BN006', 'Thần kinh', '58', 'B', '36.9', '82', '120/80', '20', 'Ngứa', 'Mẩn đỏ', 'Viêm da tiếp xúc', 'Bôi thuốc kháng viêm'),
('BS007', '2023-11-28', 'A1', 'BN007', 'Tai mũi họng', '68', 'O', '37.1', '80', '125/85', '21', 'Đau đầu', 'Chóng mặt', 'Migraine', 'Uống thuốc giảm đau'),
('BS008', '2023-11-29', 'B2', 'BN008', 'Da liễu', '60', 'AB', '36.6', '85', '110/70', '18', 'Đau bụng dưới', 'Tiểu buốt', 'Viêm đường tiết niệu', 'Uống thuốc kháng sinh'),
('BS009', '2023-11-30', 'C1', 'BN009', 'Hô hấp', '52', 'A', '37.3', '90', '130/90', '22', 'Mờ mắt', 'Đau mắt', 'Viêm kết mạc', 'Nhỏ mắt'),
('BS010', '2023-12-01', 'A2', 'BN010', 'Chấn thương chỉnh hình', '65', 'B', '36.7', '85', '125/85', '21', 'Ngạt mũi', 'Sổ mũi', 'Viêm mũi dị ứng', 'Uống thuốc kháng histamin'),
('BS010', '2024-10-20', 'A1', 'BN013', 'Chấn thương chỉnh hình', '20', 'B', '40', '70', '110/80', '20', 'Đau Đầu', 'Đau Đầu', 'Đau Đầu', 'Nhập Viện'),
('BS001', '0026-04-16', 'A2', 'BN031', 'Chấn thương chỉnh hình', '50', 'B', '40', '80', '110/70', '30', 'Đau đầu', 'Đau đầu', 'Đau đầu', 'Nhập Viện'),
('BS008', '2024-10-22', 'A2', 'BN032', 'Da liễu', '50', 'B', '36', '70', '110/70', '20', 'Đau Đầu', 'Đau đầu', 'Đau Đầu', 'Nhập Viện');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bacsi`
--
ALTER TABLE `bacsi`
  ADD PRIMARY KEY (`MaBS`);

--
-- Chỉ mục cho bảng `benhnhan`
--
ALTER TABLE `benhnhan`
  ADD PRIMARY KEY (`MaBN`);

--
-- Chỉ mục cho bảng `dieutri`
--
ALTER TABLE `dieutri`
  ADD PRIMARY KEY (`MaBN`,`Thuoc`);

--
-- Chỉ mục cho bảng `giuongbenh`
--
ALTER TABLE `giuongbenh`
  ADD KEY `MaBN` (`MaBN`),
  ADD KEY `MaBS` (`MaBS`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`TaiKhoan`,`Email`);

--
-- Chỉ mục cho bảng `thongtinkhambenh`
--
ALTER TABLE `thongtinkhambenh`
  ADD PRIMARY KEY (`MaBN`,`MaBS`),
  ADD KEY `MaBS` (`MaBS`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `dieutri`
--
ALTER TABLE `dieutri`
  ADD CONSTRAINT `dieutri_ibfk_1` FOREIGN KEY (`MaBN`) REFERENCES `benhnhan` (`MaBN`);

--
-- Các ràng buộc cho bảng `giuongbenh`
--
ALTER TABLE `giuongbenh`
  ADD CONSTRAINT `giuongbenh_ibfk_1` FOREIGN KEY (`MaBN`) REFERENCES `benhnhan` (`MaBN`),
  ADD CONSTRAINT `giuongbenh_ibfk_2` FOREIGN KEY (`MaBS`) REFERENCES `bacsi` (`MaBS`);

--
-- Các ràng buộc cho bảng `thongtinkhambenh`
--
ALTER TABLE `thongtinkhambenh`
  ADD CONSTRAINT `thongtinkhambenh_ibfk_1` FOREIGN KEY (`MaBN`) REFERENCES `benhnhan` (`MaBN`),
  ADD CONSTRAINT `thongtinkhambenh_ibfk_2` FOREIGN KEY (`MaBS`) REFERENCES `bacsi` (`MaBS`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
