-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 02 Feb 2020 pada 20.22
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
  `id_prove` char(10) NOT NULL,
  `id_eksternal` char(5) NOT NULL,
  `rating` int(1) NOT NULL,
  `deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
('IN001', 'Hasri Wiji Aqsari', '082234641698', 'hasriwiji08', 'hasri_wiji', 'Hasri121198', 'Kadiv', 'TD', 'Senior', 'DEFIN'),
('IN002', 'Vanda Fitriyanah', '082234755001', 'kosong', 'vanda_fitriyanah@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('IN003', 'Lidya Cahya Aurellia', '081332280585', 'kosong', 'lidya_cahya@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('IN004', 'Putri Hidayati Rohmah', '089504631309', 'kosong', 'putri_hidayati@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('IN005', 'Syafniya Zilfah Aniesiy', '089510777046', 'kosong', 'syafniya_zilfah@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('IN006', 'Kevin Agung Fernanda Rifki', '083830818118', 'kosong', 'kevin_agung@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior', 'DEFIN'),
('IN007', 'Ichwanul Kahfi Prasetya', '082335791531', 'kosong', 'ichwanul_kahfi@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior', 'DEFIN'),
('IN008', 'Shecilia Nur Salsalbila', '081217529139', 'kosong', 'shecilia_salsabila@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior', 'DEFIN'),
('IN009', 'Seza Dwiwulan Ramadini', '082336964693', 'kosong', 'seza_dwiwulan@scc-himastaits.com', 'scc123', 'Bendahara', 'Kosong', 'Junior', 'DEFIN'),
('IN010', 'Tiza a', '082111231232', 'tizaaja', 'tizaa', 'tizaa', 'Sekdiv', 'HRD', 'Senior', 'DEFIN'),
('IN011', 'Aubert Oktaviantono', '081331913541', 'kosong', 'aubert_oktaviantono@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN012', 'Yuniar Mega Kartikasari', '081905217438', 'kosong', 'yuniar_mega@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN013', 'Nauli Mazaya Siregar', '085726898883', 'kosong', 'nauli_mazaya@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN014', 'Inka Nurul Alfiana', '082336050265', 'kosong', 'inka_nurul@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN015', 'Eva Marella', '087860473988', 'kosong', 'eva_marella@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN016', 'Sarnita Sadya', '081288551389', 'kosong', 'sarnita_sadya@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN');

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
(1, '1', 'Senin', '07:00', '09:00', 'Free', 'Aktif', '2020-02-03'),
(2, '1', 'Selasa', '11:00', '12:00', 'Free', 'Aktif', '2020-02-03'),
(3, '1', 'Rabu', '13:00', '15:00', 'Free', 'Aktif', '2020-02-03'),
(5, '1', 'Kamis', '08:30', '09:30', 'Free', 'Aktif', '2020-02-03'),
(6, '1', 'Jumat', '14:30', '15:30', 'Free', 'Aktif', '0000-00-00'),
(7, '1', 'Senin', '12:00', '13:00', 'Free', 'Aktif', '2020-02-03'),
(8, '1', 'Selasa', '07:00', '09:00', 'Free', 'Aktif', '2020-02-02'),
(9, '1', 'Rabu', '07:00', '09:00', 'Free', 'Aktif', '2020-02-03');

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
-- Stand-in struktur untuk tampilan `list_prove`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_prove` (
`id_prove` char(10)
,`id_eksternal` char(5)
,`nama_eksternal` varchar(50)
,`id_internal` char(5)
,`nama_internal` varchar(50)
,`id_materi_prove` int(3)
,`nama_materi_prove` varchar(30)
,`id_jadwal_prove` int(3)
,`hari` enum('Senin','Selasa','Rabu','Kamis','Jumat')
,`jam_mulai` varchar(10)
,`jam_selesai` varchar(10)
,`deskripsi_materi` tinytext
,`tanggal_booking` date
,`tanggal_prove` date
,`kode_prove` char(10)
,`kata_sandi` char(5)
,`status_prove` enum('Selesai','Belum Selesai','Batal')
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
  `id_prove` char(10) NOT NULL,
  `id_eksternal` char(5) NOT NULL,
  `id_materi_prove` int(3) NOT NULL,
  `id_jadwal_prove` int(3) NOT NULL,
  `deskripsi_materi` tinytext NOT NULL,
  `tanggal_booking` date NOT NULL,
  `tanggal_prove` date NOT NULL,
  `kode_prove` char(10) NOT NULL,
  `kata_sandi` char(5) NOT NULL,
  `status_prove` enum('Selesai','Belum Selesai','Batal') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

-- --------------------------------------------------------

--
-- Struktur untuk view `list_prove`
--
DROP TABLE IF EXISTS `list_prove`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_prove`  AS  select `p`.`id_prove` AS `id_prove`,`e`.`id_eksternal` AS `id_eksternal`,`e`.`nama` AS `nama_eksternal`,`i`.`id_internal` AS `id_internal`,`i`.`nama` AS `nama_internal`,`mp`.`id_materi_prove` AS `id_materi_prove`,`mp`.`nama` AS `nama_materi_prove`,`jp`.`id_jadwal_prove` AS `id_jadwal_prove`,`jp`.`hari` AS `hari`,`jp`.`jam_mulai` AS `jam_mulai`,`jp`.`jam_selesai` AS `jam_selesai`,`p`.`deskripsi_materi` AS `deskripsi_materi`,`p`.`tanggal_booking` AS `tanggal_booking`,`p`.`tanggal_prove` AS `tanggal_prove`,`p`.`kode_prove` AS `kode_prove`,`p`.`kata_sandi` AS `kata_sandi`,`p`.`status_prove` AS `status_prove` from (((((`prove` `p` join `eksternal` `e` on((`p`.`id_eksternal` = `e`.`id_eksternal`))) join `jadwal_prove` `jp` on((`p`.`id_jadwal_prove` = `jp`.`id_jadwal_prove`))) join `internal` `i` on((`jp`.`id_internal` = `i`.`id_internal`))) join `materi_prove` `mp` on((`p`.`id_materi_prove` = `mp`.`id_materi_prove`))) join `detail_prove` `dp` on((`p`.`id_prove` = `dp`.`id_prove`))) order by `p`.`id_prove` desc ;

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
  MODIFY `id_detail_prove` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

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
-- AUTO_INCREMENT untuk tabel `software`
--
ALTER TABLE `software`
  MODIFY `id_software` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
