-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 02 Feb 2020 pada 13.29
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scc`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_prove`
--

CREATE TABLE `detail_prove` (
  `id_detail_prove` int(5) NOT NULL,
  `id_prove` int(5) NOT NULL,
  `id_eksternal` char(5) NOT NULL,
  `rating` int(1) NOT NULL,
  `deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_prove`
--

INSERT INTO `detail_prove` (`id_detail_prove`, `id_prove`, `id_eksternal`, `rating`, `deskripsi`) VALUES
(1, 1, '2', 0, ''),
(2, 1, '3', 0, '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `eksternal`
--

CREATE TABLE `eksternal` (
  `id_eksternal` char(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `akun_line` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `angkatan` varchar(4) NOT NULL,
  `foto` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `eksternal`
--

INSERT INTO `eksternal` (`id_eksternal`, `nama`, `no_hp`, `akun_line`, `username`, `password`, `angkatan`, `foto`) VALUES
('1', 'Ali Akbar', '89121212', 'ali_akbar', 'ali_akbar', 'aliakbar123', '2018', 'DEFEK'),
('2', 'Misbakhul', '877571232', 'misbakhul', 'misbah', 'misbah', '2019', '2'),
('EK003', 'rizal', '08224371298', 'rizals', 'rizalx', 'asd123', '2017', 'DEFEK');

-- --------------------------------------------------------

--
-- Struktur dari tabel `internal`
--

CREATE TABLE `internal` (
  `id_internal` char(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `akun_line` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `hak_akses` enum('Kadiv','Sekdiv','Bendahara','Staff Ahli','Manager','Staff') NOT NULL,
  `jabatan_managerial` enum('TD','HRD','PR','Kosong') NOT NULL,
  `status_sj` enum('Senior','Junior') NOT NULL,
  `foto` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `internal`
--

INSERT INTO `internal` (`id_internal`, `nama`, `no_hp`, `akun_line`, `username`, `password`, `hak_akses`, `jabatan_managerial`, `status_sj`, `foto`) VALUES
('1', 'Hasri Wiji Aqsari', '082234641698', 'hasriwiji08', 'hasri_wiji', 'Hasri121198', 'Kadiv', 'TD', 'Senior', 'DEFIN'),
('10', 'Vanda Fitriyanah', '082234755001', 'kosong', 'vanda_fitriyanah@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('12', 'Lidya Cahya Aurellia', '081332280585', 'kosong', 'lidya_cahya@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('13', 'Putri Hidayati Rohmah', '089504631309', 'kosong', 'putri_hidayati@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('14', 'Syafniya Zilfah Aniesiy', '089510777046', 'kosong', 'syafniya_zilfah@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('15', 'Kevin Agung Fernanda Rifki', '083830818118', 'kosong', 'kevin_agung@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior', 'DEFIN'),
('16', 'Ichwanul Kahfi Prasetya', '082335791531', 'kosong', 'ichwanul_kahfi@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior', 'DEFIN'),
('17', 'Shecilia Nur Salsalbila', '081217529139', 'kosong', 'shecilia_salsabila@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior', 'DEFIN'),
('18', 'Seza Dwiwulan Ramadini', '082336964693', 'kosong', 'seza_dwiwulan@scc-himastaits.com', 'scc123', 'Bendahara', 'Kosong', 'Junior', 'DEFIN'),
('2', 'Tiza a', '082111231232', 'tizaaja', 'tizaa', 'aa', 'Sekdiv', 'HRD', 'Senior', '2'),
('4', 'Aubert Oktaviantono', '081331913541', 'kosong', 'aubert_oktaviantono@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('5', 'Yuniar Mega Kartikasari', '081905217438', 'kosong', 'yuniar_mega@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('6', 'Nauli Mazaya Siregar', '085726898883', 'kosong', 'nauli_mazaya@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('7', 'Inka Nurul Alfiana', '082336050265', 'kosong', 'inka_nurul@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('8', 'Eva Marella', '087860473988', 'kosong', 'eva_marella@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('9', 'Sarnita Sadya', '081288551389', 'kosong', 'sarnita_sadya@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN010', 'ww', '123213', '123123', 'ww', 'ww', 'Bendahara', 'Kosong', 'Junior', 'DEFIN');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal_bank_software`
--

CREATE TABLE `jadwal_bank_software` (
  `id_jadwal_bs` int(3) NOT NULL,
  `id_internal` char(5) NOT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat') NOT NULL,
  `jam_mulai` varchar(10) NOT NULL,
  `jam_selesai` varchar(10) NOT NULL,
  `status_booking` enum('Free','Unfree') NOT NULL,
  `status_aktif` enum('Aktif','Tidak Aktif') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jadwal_bank_software`
--

INSERT INTO `jadwal_bank_software` (`id_jadwal_bs`, `id_internal`, `hari`, `jam_mulai`, `jam_selesai`, `status_booking`, `status_aktif`) VALUES
(1, '1', 'Senin', '07:00', '09:00', 'Free', 'Aktif'),
(2, '1', 'Selasa', '08:30', '09:30', 'Free', 'Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal_prove`
--

CREATE TABLE `jadwal_prove` (
  `id_jadwal_prove` int(3) NOT NULL,
  `id_internal` char(5) NOT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat') NOT NULL,
  `jam_mulai` varchar(10) NOT NULL,
  `jam_selesai` varchar(10) NOT NULL,
  `status_booking` enum('Free','Unfree') NOT NULL,
  `status_aktif` enum('Aktif','Tidak Aktif') NOT NULL,
  `terakhir_dibooking` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jadwal_prove`
--

INSERT INTO `jadwal_prove` (`id_jadwal_prove`, `id_internal`, `hari`, `jam_mulai`, `jam_selesai`, `status_booking`, `status_aktif`, `terakhir_dibooking`) VALUES
(1, '1', 'Senin', '07:00', '09:00', 'Unfree', 'Aktif', '0000-00-00'),
(2, '1', 'Selasa', '11:00', '12:00', 'Free', 'Aktif', '0000-00-00'),
(3, '1', 'Rabu', '13:00', '15:00', 'Free', 'Aktif', '0000-00-00'),
(5, '1', 'Kamis', '08:30', '09:30', 'Free', 'Aktif', '0000-00-00'),
(6, '1', 'Jumat', '14:30', '15:30', 'Free', 'Aktif', '0000-00-00'),
(7, '1', 'Senin', '12:00', '13:00', 'Free', 'Aktif', '0000-00-00'),
(8, '1', 'Selasa', '07:00', '09:00', 'Free', 'Aktif', '0000-00-00'),
(9, '1', 'Rabu', '07:00', '09:00', 'Free', 'Aktif', '0000-00-00');

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_jadwal`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_jadwal` (
`id_jadwal_prove` int(3)
,`id_internal` char(5)
,`hari` enum('Senin','Selasa','Rabu','Kamis','Jumat')
,`jam_mulai` varchar(10)
,`jam_selesai` varchar(10)
,`status_booking` enum('Free','Unfree')
,`status_aktif` enum('Aktif','Tidak Aktif')
,`terakhir_dibooking` date
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `materi_prove`
--

CREATE TABLE `materi_prove` (
  `id_materi_prove` int(3) NOT NULL,
  `nama` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `materi_prove`
--

INSERT INTO `materi_prove` (`id_materi_prove`, `nama`) VALUES
(3, 'Microsoft Excel'),
(4, 'Microsoft Access'),
(5, 'C++'),
(6, 'RStudio'),
(7, 'MATLAB');

-- --------------------------------------------------------

--
-- Struktur dari tabel `prove`
--

CREATE TABLE `prove` (
  `id_prove` int(5) NOT NULL,
  `id_eksternal` char(5) NOT NULL,
  `id_internal` char(5) NOT NULL,
  `id_materi_prove` int(3) NOT NULL,
  `id_jadwal_prove` int(3) NOT NULL,
  `deskripsi_materi` tinytext NOT NULL,
  `tanggal_booking` date NOT NULL,
  `tanggal_prove` date NOT NULL,
  `kode_prove` varchar(10) NOT NULL,
  `kata_sandi` varchar(10) NOT NULL,
  `status_prove` enum('Selesai','Belum Selesai') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prove`
--

INSERT INTO `prove` (`id_prove`, `id_eksternal`, `id_internal`, `id_materi_prove`, `id_jadwal_prove`, `deskripsi_materi`, `tanggal_booking`, `tanggal_prove`, `kode_prove`, `kata_sandi`, `status_prove`) VALUES
(1, '1', '1', 3, 0, 'Bab rumus micro', '0000-00-00', '0000-00-00', '', '', 'Belum Selesai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `software`
--

CREATE TABLE `software` (
  `id_software` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `software`
--

INSERT INTO `software` (`id_software`, `nama`) VALUES
(1, 'Accessx');

-- --------------------------------------------------------

--
-- Struktur untuk view `list_jadwal`
--
DROP TABLE IF EXISTS `list_jadwal`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_jadwal`  AS  select `jadwal_prove`.`id_jadwal_prove` AS `id_jadwal_prove`,`jadwal_prove`.`id_internal` AS `id_internal`,`jadwal_prove`.`hari` AS `hari`,`jadwal_prove`.`jam_mulai` AS `jam_mulai`,`jadwal_prove`.`jam_selesai` AS `jam_selesai`,`jadwal_prove`.`status_booking` AS `status_booking`,`jadwal_prove`.`status_aktif` AS `status_aktif`,`jadwal_prove`.`terakhir_dibooking` AS `terakhir_dibooking` from `jadwal_prove` order by `jadwal_prove`.`hari` ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_prove`
--
ALTER TABLE `detail_prove`
  ADD PRIMARY KEY (`id_detail_prove`);

--
-- Indeks untuk tabel `eksternal`
--
ALTER TABLE `eksternal`
  ADD PRIMARY KEY (`id_eksternal`);

--
-- Indeks untuk tabel `internal`
--
ALTER TABLE `internal`
  ADD PRIMARY KEY (`id_internal`);

--
-- Indeks untuk tabel `jadwal_bank_software`
--
ALTER TABLE `jadwal_bank_software`
  ADD PRIMARY KEY (`id_jadwal_bs`);

--
-- Indeks untuk tabel `jadwal_prove`
--
ALTER TABLE `jadwal_prove`
  ADD PRIMARY KEY (`id_jadwal_prove`);

--
-- Indeks untuk tabel `materi_prove`
--
ALTER TABLE `materi_prove`
  ADD PRIMARY KEY (`id_materi_prove`);

--
-- Indeks untuk tabel `prove`
--
ALTER TABLE `prove`
  ADD PRIMARY KEY (`id_prove`);

--
-- Indeks untuk tabel `software`
--
ALTER TABLE `software`
  ADD PRIMARY KEY (`id_software`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_prove`
--
ALTER TABLE `detail_prove`
  MODIFY `id_detail_prove` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `jadwal_bank_software`
--
ALTER TABLE `jadwal_bank_software`
  MODIFY `id_jadwal_bs` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `jadwal_prove`
--
ALTER TABLE `jadwal_prove`
  MODIFY `id_jadwal_prove` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `materi_prove`
--
ALTER TABLE `materi_prove`
  MODIFY `id_materi_prove` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `prove`
--
ALTER TABLE `prove`
  MODIFY `id_prove` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `software`
--
ALTER TABLE `software`
  MODIFY `id_software` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
